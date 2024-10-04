import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RosParseResult {

    // created while splitting for req 2)

    private final Path rosFile;
    private final Records records;
    private final BadRecordOfSale badRecord;

    public static RosParseResult of(Path rosFile, Records records) {
        return new RosParseResult(rosFile, records, null);
    }

    public static RosParseResult of(Path rosFile, BadRecordOfSale badRecord) {
        return new RosParseResult(rosFile, null, badRecord);
    }

    private RosParseResult(Path rosFile, Records records, BadRecordOfSale badRecord) {
        this.rosFile = rosFile;
        this.records = records;
        this.badRecord = badRecord;
    }

    public <T> T fold(BiFunction<Path, Records, T> mapRecords, BiFunction<Path, BadRecordOfSale, T> mapBadRecord) {
        if (records != null) {
            return mapRecords.apply(rosFile, records);
        }
        return mapBadRecord.apply(rosFile, badRecord);
    }

    public Records getRecords() {
        if (records == null) {
            throw new IllegalStateException("Records empty");
        }
        return records;
    }

    public BadRecordOfSale getBadRecord() {
        if (badRecord == null) {
            throw new IllegalStateException("BadRecord empty");
        }
        return badRecord;
    }
}
