import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        Path rosFile = tmpDir.resolve("grandTotalOfSingleFile.txt");
        Files.write(rosFile, "bread, 1, 2\n12-pack of eggs, 1, 3\n".getBytes());

        int grandTotal = calculateGrandTotal(rosFile);

        assertEquals(5, grandTotal, "grandTotal");
    }

    private int calculateGrandTotal(Path rosFile) throws IOException {
        String rosLines = Files.readString(rosFile);
        return calculateGrandTotal(rosLines);
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
