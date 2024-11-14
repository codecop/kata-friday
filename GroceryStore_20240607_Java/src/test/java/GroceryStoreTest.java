import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class GroceryStoreTest {

    // req 1)

    GroceryStore store = new GroceryStore();

    // deleted test: reportOfMultipleFiles

    // missing test: reportOfSingleFile

    @Test
    void integrationTest() throws IOException {
        Path path = Path.of("src/test/resources/step1");

        var report = store.reportWithCategory(path);

        assertEquals("""
                integration-ros.txt:
                sodas, 10
                candy, 20
                fruit, 3
                wheat and pasta, 2
                drinks, 8
                meat, 41
                greens, 4
                animalic, 2
                dairy, 26
                totals, 116
                """, report);
    }

    @Test
    void showOffendingLineOnBadInput(@TempDir Path tmpDir) throws IOException {
        createTempRosFile(tmpDir, "badRosFile.txt", "milk (1L), 4, ?\n");

        var report = store.reportWithCategory(tmpDir);

        assertEquals("badRosFile.txt, " +
                "java.lang.NumberFormatException: For input string: \"?\", " +
                "in line 1: \"milk (1L), 4, ?\"\n", report);
    }

    private Path createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
        return rosFile;
    }

    // req 2)

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
    
    // req 3)


    @Test
    void twoFilesWithSameItemHaveSimilarity1(@TempDir Path tmpDir) throws IOException {
        var file1 = createTempRosFile(tmpDir, "rosFile1.txt", "banana, 3, 1");
        var file2 = createTempRosFile(tmpDir, "rosFile2.txt", "banana, 6, 2");

        var similarity = store.similarity(file1, file2);

        assertEquals(1.0, similarity);
    }

    @Test
    void twoFilesWithDifferentItemsHaveSimilarity0(@TempDir Path tmpDir) throws IOException {
        var file1 = createTempRosFile(tmpDir, "rosFile1.txt", "tomatoes, 3, 1");
        var file2 = createTempRosFile(tmpDir, "rosFile2.txt", "banana, 3, 1");

        var similarity = store.similarity(file1, file2);

        assertEquals(0, similarity);
    }
}
