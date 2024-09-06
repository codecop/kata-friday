import java.util.HashMap;
import java.util.Map;

public class Category {

    private final Map<String, String> categoryByProduct = new HashMap<>();

    public Category() {
        categoryByProduct.put("bread", "wheat and pasta");
    }

    String categoryOf(String productName) {
        return categoryByProduct.get(productName);
    }
}
