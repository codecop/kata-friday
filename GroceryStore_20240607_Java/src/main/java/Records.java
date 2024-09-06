import java.util.List;

record Records(List<RecordOfSale> entries) {

    // created while splitting for req 2)

    public int grandTotal() {
        int grandTotal = 0;
        for (RecordOfSale ros : entries()) {
            grandTotal += ros.total();
        }
        return grandTotal;
    }
}
