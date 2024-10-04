import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GroceryStore {

    private final RosParser rosParser;

    GroceryStore() {
        rosParser = new RosParser();
    }

    // req 1)

    // TODO split formatting from file processing like for parsing and calculating

    public String report(Path rosFileDir) throws IOException {
        try (Stream<Path> rosFiles = Files.list(rosFileDir)) {
            return rosFiles. //
                    map(this::reportForFile). //
                    collect(Collectors.joining());
        }
    }

    private String reportForFile(Path rosFile) {
        RosParseResult rosParseResult = rosParser.parseRecords(rosFile);
        return format(rosFile, rosParseResult);
    }

    private String format(Path rosFile, RosParseResult rosParseResult) {
        return rosParseResult.fold((records) -> format(rosFile, records), //
                (ex) -> formatBadRecord(rosFile, ex));
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
