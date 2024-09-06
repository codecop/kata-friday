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
        try {

            int grandTotal = rosParser.parseRecords(rosFile).grandTotal();
            String reportTemplate = "%s, %d\n";
            return reportTemplate.formatted(rosFile.getFileName(), grandTotal);

        } catch (BadRecordOfSale ex) {
            String reportTemplate = "%s, %s\n";
            return reportTemplate.formatted(rosFile.getFileName(), ex.getMessage());
        }
    }
}
