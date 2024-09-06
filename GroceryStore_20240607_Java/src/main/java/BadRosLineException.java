public class BadRosLineException extends RuntimeException {

    public BadRosLineException(int lineNumber, String line, Exception cause) {
        super(cause.toString() + ", in line " + lineNumber + ": \"" + line + "\"", cause);
    }
}
