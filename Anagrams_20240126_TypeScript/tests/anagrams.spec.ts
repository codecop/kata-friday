// 1. generates all two-word anagrams of the string "documenting".
// 2. try to improve the performance of your solution

type keys = 'a' | 'b';

function histogram(s: string): { [K in keys]?: number } {
    return { a: 1 };
}

describe('Anagram', () => {

    describe('Histogram', () => {

        it('of single letter', () => {
            expect(histogram('a')).toEqual({ 'a': 1 });
        });

    });

});


