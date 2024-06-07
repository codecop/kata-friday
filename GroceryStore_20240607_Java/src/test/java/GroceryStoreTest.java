import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GroceryStoreTest {

    @Test
    void grandTotalOfEmptyFile() {
        double grandTotal = calculateGrandTotal("\n");
        assertEquals(0.0, grandTotal);
    }

    private double calculateGrandTotal(String rosLines) {
        double grandTotal = 0.0;
        return grandTotal;
    }

}
