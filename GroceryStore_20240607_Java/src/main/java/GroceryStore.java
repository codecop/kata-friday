import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class GroceryStore {

    private final RosParser rosParser;

    GroceryStore() {
        rosParser = new RosParser();
    }


    // req 1)
    public String report(Path rosFileDir) throws IOException {
        List<RosParseResult> results = rosParser.parseFiles(rosFileDir);
        return format(results, this::format);
    }

    // req 2)
    public String reportWithCategory(Path rosFileDir) throws IOException {
        List<RosParseResult> results = rosParser.parseFiles(rosFileDir);
        return format(results, this::formatWithCategory);
    }

    private String format(List<RosParseResult> results, BiFunction<Path, Records, String> format) {
        return results.stream(). //
                map(rosParseResult -> format(rosParseResult, format)). //
                collect(Collectors.joining());
    }

    private String format(RosParseResult rosParseResult, BiFunction<Path, Records, String> format) {
        return rosParseResult.fold(format, this::formatBadRecord);
    }

    private String format(Path rosFile, Records records) {
        int grandTotal = records.grandTotal();
        Path fileName = rosFile.getFileName();

        String reportTemplate = "%s, %d\n";
        return reportTemplate.formatted(fileName, grandTotal);
    }

    private String formatWithCategory(Path rosFile, Records records) {
        Categorizer categorizer = new Categorizer();
        int grandTotal = records.grandTotal();
        Path fileName = rosFile.getFileName();
        String reportTemplate = "%s, %d\n";

        String categoryTotals = records.totalsPerCategory(categorizer).entrySet().stream()
                .map(entry -> reportTemplate.formatted(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());
        return fileName + ":\n"
               + categoryTotals
               + reportTemplate.formatted("totals", grandTotal)
        ;
    }

    private String formatBadRecord(Path rosFile, BadRecordOfSale ex) {
        Path fileName = rosFile.getFileName();

        String reportTemplate = "%s, %s, in line %d: \"%s\"\n";
        return reportTemplate.formatted(fileName, ex.getCause(), ex.lineNumber, ex.line);
    }
}
