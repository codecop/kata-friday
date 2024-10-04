import java.util.HashMap;
import java.util.Map;

public class Categorizer {

    // req 2)

    private final Map<String, String> categoryByProduct = new HashMap<>();

    public Categorizer() {
        categoryByProduct.put("bread", "wheat and pasta");
        categoryByProduct.put("eggs", "animalic");
        categoryByProduct.put("milk", "dairy");
        categoryByProduct.put("coca cola", "sodas");
        categoryByProduct.put("chicken", "meat");
        categoryByProduct.put("beef", "meat");
        categoryByProduct.put("carrots", "greens");
        categoryByProduct.put("apples", "fruit");
        categoryByProduct.put("butter", "dairy");
        categoryByProduct.put("cheese", "dairy");
        categoryByProduct.put("bacon", "meat");
        categoryByProduct.put("juice", "drinks");
        categoryByProduct.put("water", "drinks");
        categoryByProduct.put("twixies", "candy");
        categoryByProduct.put("tomatoes", "greens");
        categoryByProduct.put("bananas", "fruit");
        // added for sample file
        categoryByProduct.put("sirloin", "meat");
    }

    public String categoryOf(String item) {
        String product = removeDetails(item);

        String exactMatch = categoryByProduct.get(product);
        if (exactMatch != null) {
            return exactMatch;
        }

        return partialMatchOf(product);
    }

    private String removeDetails(String item) {
        return item.replaceAll("\\s*\\(.*\\)", "");
    }

    private String partialMatchOf(String product) {
        String[] words = product.split(" ");
        for (String word : words) {
            String category = categoryByProduct.get(word);
            if (category != null) {
                return category;
            }
        }

        return null;
    }
}
