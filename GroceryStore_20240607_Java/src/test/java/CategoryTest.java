import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    Category category = new Category();

    @Test
    void extractsCategoryFromRosLine() {
        var result = category.categoryOf("bread, 1, 2");

        assertEquals("wheat and pasta", result);
    }
}
