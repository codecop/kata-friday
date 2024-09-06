import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void parseOneLine(String name, String rosLines, int expectedTotal) {
        var records = rosParser.parseRecords(rosLines);
        assertEquals(1, records.entries().size());
        assertEquals(expectedTotal, records.entries().get(0).total());
    }

    @Test
    void parseEmptyLine() {
        var records = rosParser.parseRecords("\n");
        assertTrue(records.entries().isEmpty());
    }

    public static Stream<Arguments> grandTotalOfOneLineSource() {
        return Stream.of(
                of("basic case", "bread, 1, 2\n", 2),
                of("coma in description", "apples (red, 1Kg bag), 1, 2\n", 2),
                of("multiple comas", "twixies (1 whole box, 3 rows, 5 per row), 1, 20\n", 20)
        );
    }

    // temporary test: grandTotalOfOneLineDifferentEntries

    @Test
    void parseMultipleLines() {
        var records = rosParser.parseRecords("bread, 1, 2\n12-pack of eggs, 1, 3\n");
        assertEquals(2, records.entries().size());
        assertEquals(2, records.entries().get(0).total());
    }

    @Test
    void parseSingleFile(@TempDir Path tmpDir) throws IOException {
        Path rosFile = createTempRosFile(tmpDir, "parseSingleFile.txt", "bread, 1, 2\n12-pack of eggs, 1, 3\n");

        var records = rosParser.parseRecords(rosFile);

        assertEquals(2, records.entries().size());
    }

    @Test
    void showOffendingLineOnBadInput() {
        var exception = assertThrows(BadRecordOfSale.class, () -> rosParser.parseRecords("milk (1L), 4, ?\n"));
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
