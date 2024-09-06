import java.util.HashMap;
import java.util.Map;

public class Categorizer {

    private final Map<String, String> categoryByProduct = new HashMap<>();

    public Categorizer() {
        categoryByProduct.put("bread", "wheat and pasta");
    }

    String categoryOf(String productName) {
        return categoryByProduct.get(productName);
    }
}
