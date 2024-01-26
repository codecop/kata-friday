// 1. generates all two-word anagrams of the string "documenting".
// 2. try to improve the performance of your solution

// see https://stackoverflow.com/a/61129812
const letters = ['a', 'b'] as const;
type Letters = typeof letters[number];

function isLetter(c: any): c is Letters {
    // needs to be any because c is not of Letters yet
    return letters.includes(c);
}

function histogram(s: string): { [L in Letters]?: number } {
    let result: { [L in Letters]?: number } = {};

    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        if (isLetter(c)) {
            result[c] = (result[c] || 0) + 1;
        }
    }

    return result;
}

describe('Anagram', () => {

    describe('Histogram', () => {

        it('of single letter', () => {
            expect(histogram('a')).toEqual({ a: 1 });
        });

        it('of double letter', () => {
            expect(histogram('aa')).toEqual({ a: 2 });
        });

        it('of two distinct letters', () => {
            expect(histogram('ab')).toEqual({ a: 1, b: 1 });
        });
    });

});


