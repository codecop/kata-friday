import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordsTest {

    @Test
    void grandTotalOfMultipleRecords() {
        Records records = new Records(Arrays.asList(new RecordOfSale(2), new RecordOfSale(3)));

        int grandTotal = records.grandTotal();

        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfEmptyRecords() {
        Records records = new Records(emptyList());

        int grandTotal = records.grandTotal();

        assertEquals(0, grandTotal);
    }
}
