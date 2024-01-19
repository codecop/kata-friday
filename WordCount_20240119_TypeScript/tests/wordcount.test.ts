function count_words(text: string): number {
    return text.split(" ").filter((word) => word.length > 0).length
}

describe('Word Count', () => {

    // String with spaces around should be trimmed.
    // Doublecheck that words with chars like -, ', ` are counted right.
    // Non-whitespace (ex. breakspace, unicode chars) should be treated as a delimiter

    // Empty string has no words.
    it('counts empty string as no word', () => {
        expect(count_words("")).toEqual(0);
    });

    describe('Function have to count words and not spaces', () => {

        it('counts single word', () => {
            expect(count_words("dog")).toEqual(1);
        });
      
        it('count two words delimited by space', () => {
            expect(count_words("brown fox")).toEqual(2);
        });
      
        it('count single word prefixed by space', () => {
            expect(count_words(" dog")).toEqual(1);
        });

    });

});


// cyber-dojo hotkeys: Alt-O Outout Alt-T Run Test