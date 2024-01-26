// 1. generates all two-word anagrams of the string "documenting".
// 2. try to improve the performance of your solution

type keys = 'a' | 'b';

function histogram(s: string): { [K in keys]?: number } {
    let result: { [K in keys]?: number } = {};

    for (let i = 0; i < s.length; i++) {
        let c = s[i];
        if (c === 'a') {
            result.a = (result.a || 0) + 1;
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

    });

});


