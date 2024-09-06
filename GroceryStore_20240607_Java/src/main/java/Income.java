import java.nio.file.Files;
import java.nio.file.Path;

public class Income {

    public int calculateGrandTotal(String rosLines) {
        int grandTotal = 0;
        String[] lines = rosLines.split("\n");
        for (String line : lines) {
            // req 1) 1. we are a bit more generic by removing all white space
            // req 1) 2. we do not want to add a record now, feels over-engineered.
            String[] recordOfSale = line.split("\\s*,\\s*");
            int total = Integer.parseInt(recordOfSale[recordOfSale.length - 1]);
            grandTotal += total;
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

