function count_words(text: string): number {
    const delimiter = /\s/
    const text_parts = text.split(delimiter);
    const is_word = (word: string) => word.length > 0 && !['.', '"'].include(word);
    const words = text_parts.filter(is_word);
    return words.length;
}

describe('Word Count', () => {

    // Doublecheck that words with chars like -, ', ` are counted right.

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
  
    // Non-whitespace (ex. breakspace, unicode chars) should be treated as a delimiter
    describe('counts words and not other whitespace', () => {
        // https://jestjs.io/docs/api#1-testeachtablename-fn-timeout
        test.each([
          ['space',    ' '],
          ['new line', '\n'],
          ['tab',      '\t'],
          ['no-break-space (U+00A0)', '\u00A0'],
          ['em-space (U+2003)',       '\u2003'],
        ])
        ('of two words delimited by %s', (a,b) => {
            expect(count_words(`brown${b}fox`)).toEqual(2);
        });
      
        test('of two words delimited by new line (in multiline string)', () => {
            expect(count_words(`brown
                                fox`)).toEqual(2);
        });
    });
  
    describe('counts standalone symbols not as words', () => {
        test('of dot', () => {
          expect(count_words('dog .')).toEqual(1);
        });
        test('of quote', () => {
          expect(count_words('" dog "')).toEqual(1);
        });
      // dot + quote => end of citation
    });
});

// cyber-dojo hotkeys: Alt-O Outout Alt-T Run Test