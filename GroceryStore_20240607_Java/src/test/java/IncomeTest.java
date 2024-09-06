import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

class IncomeTest {

    // req 1)

    Income income = new Income();

    @ParameterizedTest(name = "{0}")
    @MethodSource("grandTotalOfOneLineSource")
    void grandTotalOfOneLine(String name, String rosLines, int expectedGrandTotal) {
        int grandTotal = income.calculateGrandTotal(rosLines);
        assertEquals(expectedGrandTotal, grandTotal);
    }

    public static Stream<Arguments> grandTotalOfOneLineSource() {
        return Stream.of(
                of("empty file", "\n", 0),
                of("basic case", "bread, 1, 2\n", 2),
                of("coma in description", "apples (red, 1Kg bag), 1, 2\n", 2),
                of("multiple comas", "twixies (1 whole box, 3 rows, 5 per row), 1, 20\n", 20)
        );
    }

    // temporary test: grandTotalOfOneLineDifferentEntries

    @Test
    void grandTotalOfMultipleLines() {
        int grandTotal = income.calculateGrandTotal("bread, 1, 2\n12-pack of eggs, 1, 3\n");
        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfSingleFile(@TempDir Path tmpDir) throws IOException {
        Path rosFile = createTempRosFile(tmpDir, "grandTotalOfSingleFile.txt", "bread, 1, 2\n12-pack of eggs, 1, 3\n");

        int grandTotal = income.calculateGrandTotal(rosFile);

        assertEquals(5, grandTotal, "grandTotal");
    }

    private Path createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
        return rosFile;
    }

}
