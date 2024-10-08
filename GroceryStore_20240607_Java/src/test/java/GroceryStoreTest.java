import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GroceryStoreTest {

    // req 1)

    GroceryStore store = new GroceryStore();

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

    // TODO req 2: change all tests to use new method
    @Test
    void reportIncludingCategoryOfMultipleFiles(@TempDir Path tmpDir) throws IOException {
        createTempRosFile(tmpDir, "rosFile1.txt", "milk (1L), 4, 8\n");
        createTempRosFile(tmpDir, "rosFile2.txt", "coca cola (33cl), 10, 10\n");

        var report = store.reportWithCategory(tmpDir);

        assertEquals(
                """
                        rosFile1.txt:
                        dairy, 8
                        totals, 8
                        rosFile2.txt:
                        sodas, 10
                        totals, 10
                        """,
                report
        );
    }

    @Test
    void integrationTest() throws IOException {
        Path path = Path.of("src/test/resources/step1");

        var report = store.report(path);

        assertEquals("integration-ros.txt, 116\n", report);
    }

    @Test
    void showOffendingLineOnBadInput(@TempDir Path tmpDir) throws IOException {
        createTempRosFile(tmpDir, "badRosFile.txt", "milk (1L), 4, ?\n");

        var report = store.report(tmpDir);

        assertEquals("badRosFile.txt, " +
                "java.lang.NumberFormatException: For input string: \"?\", " +
                "in line 1: \"milk (1L), 4, ?\"\n", report);
    }

    private void createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
    }
}
