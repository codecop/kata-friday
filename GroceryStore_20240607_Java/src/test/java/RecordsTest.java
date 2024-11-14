import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.Collections.emptyList;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    // req 3)

    @Test
    void twoRecordsAreSimilarOneAdditional() {
        Records records1 = new Records(List.of(new RecordOfSale("banana", 3), new RecordOfSale("tomatoes", 2)));
        Records records2 = new Records(List.of(new RecordOfSale("banana", 3)));

        double similarity = records1.similarity(records2);

        assertEquals(0.5, similarity, 0.01);
    }

    @Test
    void twoRecordsAreSimilarOneExtra() {
        Records records1 = new Records(List.of(new RecordOfSale("banana", 3)));
        Records records2 = new Records(List.of(new RecordOfSale("banana", 3), new RecordOfSale("tomatoes", 2)));

        double similarity = records1.similarity(records2);

        assertEquals(0.5, similarity, 0.01);
    }
}
