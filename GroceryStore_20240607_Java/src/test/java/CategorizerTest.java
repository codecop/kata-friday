import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CategorizerTest {

    // req 2)

    Categorizer categorizer = new Categorizer();

    @Test
    void extractsCategoryFromItem() {
        var result = categorizer.categoryOf("bread");
        assertEquals("wheat and pasta", result);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            bread, wheat and pasta
            eggs, animalic
            milk, dairy
            coca cola, sodas
            chicken, meat
            beef, meat
            carrots, greens
            apples, fruit
            butter, dairy
            cheese, dairy
            bacon, meat
            juice, drinks
            water, drinks
            twixies, candy
            tomatoes, greens
            bananas, fruit
            """)
    void integrationTest(String item, String expectedCategory) {
        var result = categorizer.categoryOf(item);
        assertEquals(expectedCategory, result);
    }
}
