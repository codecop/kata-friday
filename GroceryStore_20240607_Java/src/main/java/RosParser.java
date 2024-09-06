import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RosParser {

    public Records parseRecords(Path rosFile) {
        return Uncheck.ioException(() -> {

            String rosLines = Files.readString(rosFile);
            return parseRecords(rosLines);

        });
    }

    public Records parseRecords(String rosLines) {
        List<RecordOfSale> entries = new ArrayList<>();

        String[] lines = rosLines.split("[\n\r]+");
        for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
            String line = lines[lineNumber];
            entries.add(parseLine(lineNumber, line));
        }

        return new Records(entries);
    }

    private RecordOfSale parseLine(int lineNumber, String line) {
        try {

            // req 1) 1. we are a bit more generic by removing all white space
            String[] columns = line.split("\\s*,\\s*");
            int total = Integer.parseInt(columns[columns.length - 1]);
            return new RecordOfSale(total);

        } catch (Exception ex) {
            throw new BadRecordOfSale(lineNumber + 1, line, ex);
        }
    }
}

