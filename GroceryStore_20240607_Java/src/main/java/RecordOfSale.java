record RecordOfSale(int total) {

    static RecordOfSale parse(int lineNumber, String line) {
        try {

            // req 1) 1. we are a bit more generic by removing all white space
            String[] columns = line.split("\\s*,\\s*");
            int total = Integer.parseInt(columns[columns.length - 1]);
            return new RecordOfSale(total);

        } catch (Exception ex) {
            throw new BadRecordOfSale(lineNumber + 1, line, ex);
        }
    }
}
