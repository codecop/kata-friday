import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

class RosParserTest {

    // req 1)

    RosParser rosParser = new RosParser();

    @ParameterizedTest(name = "{0}")
    @MethodSource("grandTotalOfOneLineSource")
    void grandTotalOfOneLine(String name, String rosLines, int expectedGrandTotal) {
        int grandTotal = rosParser.parseRecords(rosLines).grandTotal();
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
        int grandTotal = rosParser.parseRecords("bread, 1, 2\n12-pack of eggs, 1, 3\n").grandTotal();
        assertEquals(5, grandTotal);
    }

    @Test
    void grandTotalOfSingleFile(@TempDir Path tmpDir) throws IOException {
        Path rosFile = createTempRosFile(tmpDir, "grandTotalOfSingleFile.txt", "bread, 1, 2\n12-pack of eggs, 1, 3\n");

        int grandTotal = rosParser.parseRecords(rosFile).grandTotal();

        assertEquals(5, grandTotal, "grandTotal");
    }

    @Test
    void showOffendingLineOnBadInput() {
        var exception = assertThrows(BadRecordOfSale.class, () -> rosParser.parseRecords("milk (1L), 4, ?\n").grandTotal());
        assertEquals("java.lang.NumberFormatException: For input string: \"?\", " +
                     "in line 1: \"milk (1L), 4, ?\"",
                exception.getMessage());
    }

    private Path createTempRosFile(Path tmpDir, String fileName, String fileBody) throws IOException {
        Path rosFile = tmpDir.resolve(fileName);
        Files.write(rosFile, fileBody.getBytes());
        return rosFile;
    }

}
