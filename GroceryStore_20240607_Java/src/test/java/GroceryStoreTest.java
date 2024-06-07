import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

class GroceryStoreTest {

    // req 1)
    @Test
    void grandTotalOfEmptyFile() {
        int grandTotal = calculateGrandTotal("\n");
        assertEquals(0, grandTotal);
    }

    @Test
    void grandTotalOfOneLine() {
        int grandTotal = calculateGrandTotal("bread, 1, 2\n");
        assertEquals(2, grandTotal);
    }

    @Test
    void grandTotalOfMultipleLines() {
        int grandTotal = calculateGrandTotal("bread, 1, 2\n12-pack of eggs, 1, 3\n");
        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfSingleFile(@TempDir Path tmpDir) throws IOException {
        Path rosFile = createTempRosFile(tmpDir, "grandTotalOfSingleFile.txt", "bread, 1, 2\n12-pack of eggs, 1, 3\n");

        int grandTotal = calculateGrandTotal(rosFile);

        assertEquals(5, grandTotal, "grandTotal");
    }

    private static Path createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
        return rosFile;
    }

    @Test
    void reportOfMultipleFiles(@TempDir Path tmpDir) throws IOException {
        createTempRosFile(tmpDir, "rosFile1.txt", "milk (1L), 4, 8\n");
        createTempRosFile(tmpDir, "rosFile2.txt", "coca cola (33cl), 10, 10\n");
        var report = report(tmpDir);

        assertEquals(
                """
                        rosFile1.txt, 8
                        rosFile2.txt, 10
                        """,
                report
        );
    }

    private String report(Path rosFileDir) throws IOException {
        return Files.list(rosFileDir).map(this::reportForFile).collect(Collectors.joining());
    }

    private String reportForFile(Path rosFile) {
        return rosFile.getFileName() + ", " + calculateGrandTotal(rosFile) + "\n";
    }

    // TODO integration test with whole file from req 1

    private int calculateGrandTotal(Path rosFile) {
        try {
            String rosLines = Files.readString(rosFile);
            return calculateGrandTotal(rosLines);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private int calculateGrandTotal(String rosLines) {
        int grandTotal = 0;
        String[] lines = rosLines.split("\n");
        for (String line : lines) {
            // req 1) 1. we are a bit more generic by removing all white space
            // req 1) 2. we do not want to add a record now, feels over-engineered.
            String[] recordOfSale = line.split("\\s*,\\s*");
            int total = Integer.parseInt(recordOfSale[2]);
            grandTotal += total;
        }
        return grandTotal;
    }
}
