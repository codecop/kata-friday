public class BadRecordOfSale extends RuntimeException {

    public final int lineNumber;
    public final String line;

    public BadRecordOfSale(int lineNumber, String line, Exception cause) {
        super(cause);
        this.lineNumber = lineNumber;
        this.line = line;
    }

}
