import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GroceryStoreTest {

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

    private int calculateGrandTotal(String rosLines) {
        int grandTotal = 0;
        String[] lines = rosLines.split("\n");
        for (String line : lines) {
            String[] recordOfSale = line.split("\\s*,\\s*");
            int total = Integer.parseInt(recordOfSale[2]);
            grandTotal += total;
        }
        return grandTotal;
    }
}
