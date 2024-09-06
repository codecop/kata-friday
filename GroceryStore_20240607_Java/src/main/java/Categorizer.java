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
    }

    String categoryOf(String productName) {
        return categoryByProduct.get(productName);
    }
}
