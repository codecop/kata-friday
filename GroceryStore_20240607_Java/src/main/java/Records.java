import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Records(List<RecordOfSale> entries) {

    // created while splitting for req 2)

    public int grandTotal() {
        int grandTotal = 0;
        for (RecordOfSale ros : entries()) {
            grandTotal += ros.total();
        }
        return grandTotal;
    }

    // req 2)

    public Map<String, Integer> totalsPerCategory(Categorizer categorizer) {
        Map<String, Integer> categoryTotals = new HashMap<>();
        for (RecordOfSale ros : entries()) {
            String category = categorizer.categoryOf(ros.item());
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0) + ros.total());
        }
        return categoryTotals;
    }


    // req 3)

    public double similarity(Records other) {
        // TODO test empty records!
        long countPosInBothEntries = entries.stream().filter(other::contains).count();

        long countExtraInOtherEntries = other.entries.size() - countPosInBothEntries;
        long totalCountOfUniqueEntries = entries.size() + countExtraInOtherEntries;

        return 1.0 * countPosInBothEntries / totalCountOfUniqueEntries;
    }

    private boolean contains(RecordOfSale record) {
        return entries.stream().filter(record::equalItem).count() == 1;
    }
}
