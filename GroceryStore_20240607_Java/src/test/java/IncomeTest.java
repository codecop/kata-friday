import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class IncomeTest {

    // req 1)

    Income income = new Income();

    @Test
    void grandTotalOfEmptyFile() {
        int grandTotal = income.calculateGrandTotal("\n");
        assertEquals(0, grandTotal);
    }

    @Test
    void grandTotalOfOneLine() {
        int grandTotal = income.calculateGrandTotal("bread, 1, 2\n");
        assertEquals(2, grandTotal);
    }

    // temporary test: grandTotalOfOneLineDifferentEntries

    // TODO missing test for "apples (red, 1Kg bag), 1, 2"

    @Test
    void grandTotalOfMultipleLines() {
        int grandTotal = income.calculateGrandTotal("bread, 1, 2\n12-pack of eggs, 1, 3\n");
        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfSingleFile(@TempDir Path tmpDir) throws IOException {
        Path rosFile = createTempRosFile(tmpDir, "grandTotalOfSingleFile.txt", "bread, 1, 2\n12-pack of eggs, 1, 3\n");

        int grandTotal = income.calculateGrandTotal(rosFile);

        assertEquals(5, grandTotal, "grandTotal");
    }

    private Path createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
        return rosFile;
    }

    // would add integration test with whole file from req 1
}
