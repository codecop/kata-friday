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

    public String categoryOf(String productName) {
        String key = normalize(productName);
        String exactMatch = categoryByProduct.get(key);
        if (exactMatch != null) {
            return exactMatch;
        }
        for (String k : key.split(" ")) {
            String x = categoryByProduct.get(k);
            if (x != null) {
                return x;
            }
        }
        // TODO exception?
        return null;
    }

    private String normalize(String productName) {
        return productName.replaceAll("\\s*\\(.*\\)", "");
    }
}
