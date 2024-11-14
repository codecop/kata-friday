record RecordOfSale(String item, int total) {

    // created while preparing for req 2)

    public boolean equalItem(RecordOfSale other) {
        return item().equals(other.item());
    }
}
