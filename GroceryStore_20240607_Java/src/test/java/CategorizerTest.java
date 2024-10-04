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

    @ParameterizedTest
    @CsvSource(textBlock = """
            12-pack of eggs; animalic
            milk (1L); dairy
            coca cola (33cl); sodas
            chicken clubs (frozen); meat
            apples (red, 1Kg bag); fruit
            butter (500 g); dairy
            cheese (1Kg); dairy
            bacon ("tasty" brand, 3 pack); meat
            orange juice (1L); drinks
            cheese (gouda, 1Kg); dairy
            bottled water (1.5L); drinks
            twixies (1 whole box, 3 rows, 5 per row); candy
            sirloin (100g); meat
            """, delimiter = ';')
    void complexProductNames(String item, String expectedCategory) {
        var result = categorizer.categoryOf(item);
        assertEquals(expectedCategory, result);
    }
}
