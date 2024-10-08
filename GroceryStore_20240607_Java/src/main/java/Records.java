import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Records(List<RecordOfSale> entries) {

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
}
