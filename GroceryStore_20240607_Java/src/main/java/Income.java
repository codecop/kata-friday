import java.nio.file.Files;
import java.nio.file.Path;

public class Income {

    public int calculateGrandTotal(String rosLines) {
        int grandTotal = 0;
        String[] lines = rosLines.split("[\n\r]+");
        for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
            String line = lines[lineNumber];
            grandTotal += calculateTotalOf(lineNumber, line);
        }
        return grandTotal;
    }

    private static int calculateTotalOf(int lineNumber, String line) {
        RecordOfSale recordOfSale = RecordOfSale.parse(lineNumber, line);
        return recordOfSale.total();
    }

    public int calculateGrandTotal(Path rosFile) {
        return Uncheck.ioException(() -> {

            String rosLines = Files.readString(rosFile);
            return calculateGrandTotal(rosLines);

        });
    }
}

