import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
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

    private static final class Either {
        private final Records records;
        private final BadRecordOfSale badRecord;

        private Either(Records records, BadRecordOfSale badRecord) {
            this.records = records;
            this.badRecord = badRecord;
        }

        public static Either of(Records records) {
            return new Either(records, null);
        }

        public static Either of(BadRecordOfSale badRecord) {
            return new Either(null, badRecord);
        }

        public String fold(Function<Records, String> ifValid, Function<BadRecordOfSale, String> ifInvalid) {
            if (records != null) {
                return ifValid.apply(records);
            }
            return ifInvalid.apply(badRecord);
        }
    }

    private String reportForFile(Path rosFile) {
        Either either;
        try {
            Records records = rosParser.parseRecords(rosFile);
            either = Either.of(records);
        } catch (BadRecordOfSale ex) {
            either = Either.of(ex);
        }
        return format(rosFile, either);
    }

    private String format(Path rosFile, Either either) {
        return either.fold((records) -> format(rosFile, records), (ex) -> formatBadRecord(rosFile, ex));
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
