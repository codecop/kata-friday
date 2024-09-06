import java.io.IOException;
import java.io.UncheckedIOException;

public class Uncheck {

    @FunctionalInterface
    public interface SupplierWithIoException<T> {
        T get() throws IOException;

    }

    public static <T> T ioException(SupplierWithIoException<T> supplier) {
        try {
            return supplier.get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}