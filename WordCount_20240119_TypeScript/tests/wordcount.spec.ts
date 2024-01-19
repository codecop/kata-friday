function count_words(text: string): number {
    // return text.trim().split(/\s+/).length;
    return 0;
}

describe('Word Count', () => {

    // Function have to count words and not spaces. You have to be sure that you doing it right
    // Empty string has no words.
    // String with spaces around should be trimmed.
    // Non-whitespace (ex. breakspace, unicode chars) should be treated as a delimiter
    // Doublecheck that words with chars like -, ', ` are counted right.

    it('counts empty string as no word', () => {
        expect(count_words("")).toEqual(0);
    });

});
