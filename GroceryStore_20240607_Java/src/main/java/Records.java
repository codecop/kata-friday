import java.util.List;

record Records(List<RecordOfSale> entries) {

    public int grandTotal() {
        int grandTotal = 0;
        for (RecordOfSale ros : entries()) {
            grandTotal += ros.total();
        }
        return grandTotal;
    }
}
