function count_words(text: string): number {
    const delimiter = ' ';
    const text_parts = text.split(delimiter);
    const words = text_parts.filter((word) => word.length > 0);
    return words.length;
}

describe('Word Count', () => {

    // Doublecheck that words with chars like -, ', ` are counted right.
    // Non-whitespace (ex. breakspace, unicode chars) should be treated as a delimiter

    // Empty string has no words.
    it('counts empty string as no word', () => {
        expect(count_words("")).toEqual(0);
    });

    describe('counts words and not spaces', () => {

        it('counts single word', () => {
            expect(count_words("dog")).toEqual(1);
        });
      
        it('counts two words delimited by space', () => {
            expect(count_words("brown fox")).toEqual(2);
        });
      
        // String with spaces around should be trimmed.
        it('counts single word prefixed by space', () => {
            expect(count_words(" dog")).toEqual(1);
        });

        test('counts words ignoring multiple spaces', () => {
            // expect green, this is a regression test
            expect(count_words(" dog  barks again   ")).toEqual(3);
        });
      
    });

});


// cyber-dojo hotkeys: Alt-O Outout Alt-T Run Test