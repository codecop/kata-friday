import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordsTest {

    @Test
    void grandTotalOfMultipleRecords() {
        Records records = new Records(Arrays.asList(new RecordOfSale("bread", 2), new RecordOfSale("bread", 3)));

        int grandTotal = records.grandTotal();

        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfEmptyRecords() {
        Records records = new Records(emptyList());

        int grandTotal = records.grandTotal();

        assertEquals(0, grandTotal);
    }

    // req 2)

    @Test
    void totalPerCategory() {
        Records records = new Records(Arrays.asList(
                new RecordOfSale("bread", 2), //
                new RecordOfSale("bread", 3) //
        ));

        Map<String, Integer> totals = records.totalsPerCategory(new Categorizer());

        assertEquals(5, totals.get("wheat and pasta"));
    }
}
