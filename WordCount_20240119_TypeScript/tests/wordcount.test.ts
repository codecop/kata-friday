function count_words(text: string): number {
    const delimiter = /\s/
    const text_parts = text.split(delimiter);
    const words = text_parts.filter((word) => word.length > 0);
    return words.length;
}

describe('Word Count', () => {

    // Doublecheck that words with chars like -, ', ` are counted right.
    // Non-whitespace (ex. breakspace, unicode chars) should be treated as a delimiter

    // Empty string has no words.
    test('counts empty string as no word', () => {
        expect(count_words("")).toEqual(0);
    });

    describe('counts words and not spaces', () => {

        test('of single word', () => {
            expect(count_words("dog")).toEqual(1);
        });
      
        test('of two words delimited by space', () => {
            expect(count_words("brown fox")).toEqual(2);
        });
      
        // String with spaces around should be trimmed.
        test('of single word prefixed by space', () => {
            expect(count_words(" dog")).toEqual(1);
        });

        test('of words ignoring multiple spaces', () => {
            // expect green, this is a regression test
            expect(count_words(" dog  barks again   ")).toEqual(3);
        });
      
    });
  
    describe('counts words and not other whitespace', () => {
        each([['space', ' ']], 'of two words delimited by space %1', (a, d) => {
            expect(count_words(`brown$dfox`)).toEqual(2);
        });
      
        test('of two words delimited by space', () => {
            expect(count_words("brown fox")).toEqual(2);
        });

        test('of two words delimited by new line', () => {
            expect(count_words("brown\nfox")).toEqual(2);

            expect(count_words(`brown
                                fox`)).toEqual(2);
        });

        test('of two words delimited by tab', () => {
            expect(count_words("brown\tfox")).toEqual(2);
        });

        test('of two words delimited by no-break-space (U+00A0)', () => {
            expect(count_words("brown\u00A0fox")).toEqual(2);
        });

        test('of two words delimited by em-space (U+2003)', () => {
            expect(count_words("brown\u2003fox")).toEqual(2);
        });
    });
});

// cyber-dojo hotkeys: Alt-O Outout Alt-T Run Test