public class BadRecordOfSale extends RuntimeException {

    public BadRecordOfSale(int lineNumber, String line, Exception cause) {
        super(cause.toString() + ", in line " + lineNumber + ": \"" + line + "\"", cause);
    }
}
