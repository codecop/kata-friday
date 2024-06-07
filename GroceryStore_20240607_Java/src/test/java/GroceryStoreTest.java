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
    void grandTotalOfOneLineOfEggs() {
        int grandTotal = calculateGrandTotal("12-pack of eggs, 1, 3\n");
        assertEquals(3, grandTotal);
    }

    private int calculateGrandTotal(String rosLines) {
        int grandTotal = 0;
        if (!rosLines.equals("\n")) {
            int total = Integer.parseInt(rosLines.split(",")[2].trim());
            grandTotal += total;
        }
        return grandTotal;
    }
}
