import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Income {

    public int calculateGrandTotal(String rosLines) {
        List<RecordOfSale> records = parseRecords(rosLines);
        return sumGrandTotal(records);
    }

    private List<RecordOfSale> parseRecords(String rosLines) {
        List<RecordOfSale> records = new ArrayList<>();

        String[] lines = rosLines.split("[\n\r]+");
        for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
            String line = lines[lineNumber];
            records.add(RecordOfSale.parse(lineNumber, line));
        }
        return records;
    }

    private int sumGrandTotal(List<RecordOfSale> records) {
        int grandTotal = 0;
        for (RecordOfSale ros : records) {
            grandTotal += ros.total();
        }
        return grandTotal;
    }

    public int calculateGrandTotal(Path rosFile) {
        return Uncheck.ioException(() -> {

            String rosLines = Files.readString(rosFile);
            return calculateGrandTotal(rosLines);

        });
    }
}

