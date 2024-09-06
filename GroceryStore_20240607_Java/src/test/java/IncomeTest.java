import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void grandTotalOfEmptyFile() {
        int grandTotal = income.calculateGrandTotal("\n");
        assertEquals(0, grandTotal);
    }

    @ParameterizedTest
    @MethodSource("grandTotalOfOneLineSource")
    void grandTotalOfOneLine(String rosLines, int expectedGrandTotal) {
        int grandTotal = income.calculateGrandTotal(rosLines);
        assertEquals(expectedGrandTotal, grandTotal);
    }

    public static Stream<Arguments> grandTotalOfOneLineSource() {
        return Stream.of(
                Arguments.of("bread, 1, 2\n", 2),
                Arguments.of("apples (red, 1Kg bag), 1, 2\n", 2)
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

    // would add integration test with whole file from req 1
}
