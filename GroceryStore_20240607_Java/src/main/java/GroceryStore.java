import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

class GroceryStore {

    private final RosParser rosParser;

    GroceryStore() {
        rosParser = new RosParser();
    }

    // req 1)

    public String report(Path rosFileDir) throws IOException {
        List<RosParseResult> results = rosParser.parseFiles(rosFileDir);
        return format(results);
    }

    private String format(List<RosParseResult> results) {
        return results.stream(). //
                map(this::format). //
                collect(Collectors.joining());
    }

    private String format(RosParseResult rosParseResult) {
        return rosParseResult.fold(this::format, this::formatBadRecord);
    }

    private String format(Path rosFile, Records records) {
        int grandTotal = records.grandTotal();
        Path fileName = rosFile.getFileName();

        String reportTemplate = "%s, %d\n";
        return reportTemplate.formatted(fileName, grandTotal);
    }

    private String formatBadRecord(Path rosFile, BadRecordOfSale ex) {
        Path fileName = rosFile.getFileName();

        String reportTemplate = "%s, %s, in line %d: \"%s\"\n";
        return reportTemplate.formatted(fileName, ex.getCause(), ex.lineNumber, ex.line);
    }
}
