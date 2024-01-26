// 1. generates all two-word anagrams of the string "documenting".
// 2. try to improve the performance of your solution

// see https://stackoverflow.com/a/61129812
const letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'] as const;
type Letters = typeof letters[number];

function isLetter(c: any): c is Letters {
    // needs to be any because c is not of Letters yet
    return letters.includes(c);
}

type Histogram = { [L in Letters]?: number }

function histogram(s: string): Histogram {
    let result: Histogram = {};

    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        if (isLetter(c)) {
            result[c] = (result[c] || 0) + 1;
        }
    }

    return result;
}

function histogram_equals(a: Histogram, b: Histogram): boolean {
    return true;
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

        it('of two distinct letters with various counts', () => {
            expect(histogram('ababb')).toEqual({ a: 2, b: 3 });
        });

        it('of all letters', () => {
            expect(histogram('abcdefghijklmnopqrstuvwxyz')).
                toEqual({
                    a: 1, b: 1, c: 1, d: 1, e: 1, f: 1, g: 1, h: 1, i: 1,
                    j: 1, k: 1, l: 1, m: 1, n: 1, o: 1, p: 1, q: 1, r: 1,
                    s: 1, t: 1, u: 1, v: 1, w: 1, x: 1, y: 1, z: 1
                });
        });

        it('of "documenting"', () => {
            expect(histogram('documenting')).
                toEqual({ d: 1, o: 1, c: 1, u: 1, m: 1, e: 1, n: 2, t: 1, i: 1, g: 1 })
        });

        it('comparing', () => {
            // This condition will always return 'false' since JavaScript compares objects by reference, not value.ts(2839)
            // expect({ d: 1, o: 2 } === { o: 2, d: 1 }).toEqual(true);
            expect(histogram_equals({ d: 1, o: 2 }, { o: 2, d: 1 })).toEqual(true);
        });

    });

});


