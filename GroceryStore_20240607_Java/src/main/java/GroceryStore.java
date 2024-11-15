import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class GroceryStore {

    private final RosParser rosParser;
    private final Categorizer categorizer;

    GroceryStore() {
        rosParser = new RosParser();
        categorizer = new Categorizer();
    }


    // req 1)
    // deleted public String report(Path rosFileDir) throws IOException

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

    // deleted private String format(Path rosFile, Records records)

    private String formatWithCategory(Path rosFile, Records records) {
        String reportTemplate = "%s, %d\n";

        return formatFileName(rosFile)
                + formatCategories(reportTemplate, records)
                + formatGrandTotal(reportTemplate, records);
    }

    private String formatFileName(Path rosFile) {
        String template = "%s:\n";
        Path fileName = rosFile.getFileName();
        return template.formatted(fileName);
    }

    private String formatCategories(String reportTemplate, Records records) {
        Map<String, Integer> categories = records.totalsPerCategory(categorizer);
        return categories.entrySet().stream()
                .map(categoryTotal -> formatCategoryTotal(reportTemplate, categoryTotal))
                .collect(Collectors.joining());
    }

    private String formatCategoryTotal(String reportTemplate, Map.Entry<String, Integer> categoryTotal) {
        String category = categoryTotal.getKey();
        Integer total = categoryTotal.getValue();
        return reportTemplate.formatted(category, total);
    }

    private String formatGrandTotal(String reportTemplate, Records records) {
        String description = "totals";
        int grandTotal = records.grandTotal();
        return reportTemplate.formatted(description, grandTotal);
    }

    private String formatBadRecord(Path rosFile, BadRecordOfSale ex) {
        Path fileName = rosFile.getFileName();

        String reportTemplate = "%s, %s, in line %d: \"%s\"\n";
        return reportTemplate.formatted(fileName, ex.getCause(), ex.lineNumber, ex.line);
    }

    public double similarity(Path rosFile1, Path rosFile2) {
        RosParseResult parseResult1 = rosParser.parseRecords(rosFile1);
        RosParseResult parseResult2 = rosParser.parseRecords(rosFile2);

        return parseResult1.fold((path1, records1) -> {
            return parseResult2.fold((path2, records2) -> {
                return records1.similarity(records2);

            }, (path2, bad) -> {
                throw bad;
            });

        }, (path, bad) -> {
            throw bad;
        });

    }

}
