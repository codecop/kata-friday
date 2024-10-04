import java.util.function.Function;

public class RosParseResult {

    // created while splitting for req 2)

    private final Records records;
    private final BadRecordOfSale badRecord;

    public static RosParseResult of(Records records) {
        return new RosParseResult(records, null);
    }

    public static RosParseResult of(BadRecordOfSale badRecord) {
        return new RosParseResult(null, badRecord);
    }

    private RosParseResult(Records records, BadRecordOfSale badRecord) {
        this.records = records;
        this.badRecord = badRecord;
    }

    public <T> T fold(Function<Records, T> mapRecords, Function<BadRecordOfSale, T> mapBadRecord) {
        if (records != null) {
            return mapRecords.apply(records);
        }
        return mapBadRecord.apply(badRecord);
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
