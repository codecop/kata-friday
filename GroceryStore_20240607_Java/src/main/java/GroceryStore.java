import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GroceryStore {

    private final Income income;

    GroceryStore() {
        income = new Income();
    }

    // req 1)

    public String report(Path rosFileDir) throws IOException {
        try (Stream<Path> rosFiles = Files.list(rosFileDir)) {
            return rosFiles. //
                    map(this::reportForFile). //
                    collect(Collectors.joining());
        }
    }

    private String reportForFile(Path rosFile) {
        String reportTemplate = "%s, %d\n";
        return reportTemplate.formatted(rosFile.getFileName(), income.calculateGrandTotal(rosFile));
    }
}
