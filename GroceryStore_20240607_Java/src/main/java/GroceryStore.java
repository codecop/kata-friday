import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GroceryStore {

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
        return reportTemplate.formatted(rosFile.getFileName(), calculateGrandTotal(rosFile));
    }

    /* for test */ int calculateGrandTotal(Path rosFile) {
        try {
            // TODO refactor to until function using functional interface with IOException

            String rosLines = Files.readString(rosFile);
            return calculateGrandTotal(rosLines);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /* for test */ int calculateGrandTotal(String rosLines) {
        int grandTotal = 0;
        String[] lines = rosLines.split("\n");
        for (String line : lines) {
            // req 1) 1. we are a bit more generic by removing all white space
            // req 1) 2. we do not want to add a record now, feels over-engineered.
            String[] recordOfSale = line.split("\\s*,\\s*");
            int total = Integer.parseInt(recordOfSale[2]);
            grandTotal += total;
        }
        return grandTotal;
    }
}
