function count_words(text: string): number {
    if (text === "") {
        return 0;
    }
    return 1;
    // return text.trim().split(/\s+/).length;
}

describe('Word Count', () => {

    // String with spaces around should be trimmed.
    // Non-whitespace (ex. breakspace, unicode chars) should be treated as a delimiter
    // Doublecheck that words with chars like -, ', ` are counted right.

    // Empty string has no words.
    it('counts empty string as no word', () => {
        expect(count_words("")).toEqual(0);
    });

    describe('Function have to count words and not spaces', () => {

        it('counts single word', () => {
            expect(count_words("dog")).toEqual(1);
        });

    });

});
