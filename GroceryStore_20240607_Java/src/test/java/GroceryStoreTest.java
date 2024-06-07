import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GroceryStoreTest {

    // req 1)
    GroceryStore store = new GroceryStore();

    @Test
    void grandTotalOfEmptyFile() {
        int grandTotal = store.calculateGrandTotal("\n");
        assertEquals(0, grandTotal);
    }

    @Test
    void grandTotalOfOneLine() {
        int grandTotal = store.calculateGrandTotal("bread, 1, 2\n");
        assertEquals(2, grandTotal);
    }

    // temporary test: grandTotalOfOneLineDifferentEntries

    // TODO missing test for "apples (red, 1Kg bag), 1, 2"

    @Test
    void grandTotalOfMultipleLines() {
        int grandTotal = store.calculateGrandTotal("bread, 1, 2\n12-pack of eggs, 1, 3\n");
        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfSingleFile(@TempDir Path tmpDir) throws IOException {
        Path rosFile = createTempRosFile(tmpDir, "grandTotalOfSingleFile.txt", "bread, 1, 2\n12-pack of eggs, 1, 3\n");

        int grandTotal = store.calculateGrandTotal(rosFile);

        assertEquals(5, grandTotal, "grandTotal");
    }

    private Path createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
        return rosFile;
    }

    // would add integration test with whole file from req 1

    // missing test: reportOfSingleFile

    @Test
    void reportOfMultipleFiles(@TempDir Path tmpDir) throws IOException {
        createTempRosFile(tmpDir, "rosFile1.txt", "milk (1L), 4, 8\n");
        createTempRosFile(tmpDir, "rosFile2.txt", "coca cola (33cl), 10, 10\n");

        var report = store.report(tmpDir);

        assertEquals(
                """
                        rosFile1.txt, 8
                        rosFile2.txt, 10
                        """,
                report
        );
    }

}
