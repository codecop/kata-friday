import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RosParser {

    // req 1) but created while splitting for req 2)

    public RosParseResult parseRecords(Path rosFile) {
        try {

            String rosLines = Files.readString(rosFile); // only IO in this class
            Records records = parseRecords(rosLines);
            return RosParseResult.of(rosFile, records);

        } catch (IOException ex) { // only IO in this class
            throw new UncheckedIOException(ex);

        } catch (BadRecordOfSale ex) {
            return RosParseResult.of(rosFile, ex);
        }
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
