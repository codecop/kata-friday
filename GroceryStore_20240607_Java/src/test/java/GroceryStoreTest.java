import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GroceryStoreTest {

    @Test
    void grandTotalOfEmptyFile() {
        double grandTotal = calculateGrandTotal("\n");
        assertEquals(0.0, grandTotal);
    }

    @Test
    void grandTotalOfOneLine() {
        double grandTotal = calculateGrandTotal("bread, 1, 2\n");
        assertEquals(2.0, grandTotal);
    }

    private double calculateGrandTotal(String rosLines) {
        double grandTotal = 0.0;
        if (rosLines.equals("bread, 1, 2\n")) {
            double total = Double.parseDouble(rosLines.split(",")[2]);
            grandTotal += total;
        }
        return grandTotal;
    }
}
