// 1. generates all two-word anagrams of the string "documenting".
// 2. try to improve the performance of your solution
import * as fs from 'fs';

// see https://stackoverflow.com/a/61129812
const letters = [
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'] as const;
type Letters = typeof letters[number];

function isLetter(c: any): c is Letters {
    // needs to be any because c is not of Letters yet
    return letters.includes(c);
}

type Histogram = { [L in Letters]?: number }

function histogram_for(s: string): Histogram {
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
    for (const letter of letters) {
        if (a[letter] !== b[letter]) {
            return false;
        }
    }
    return true;
}

function histogram_add(a: Histogram, b: Histogram): Histogram {
    let result: Histogram = {};

    for (const letter of letters) {
        const sum = (a[letter] || 0) + (b[letter] || 0)
        if (sum > 0) {
            result[letter] = sum
        }
    }

    return result;
}

describe('Anagram', () => {

    describe('Histogram', () => {

        it('of single letter', () => {
            expect(histogram_for('a')).toEqual({ a: 1 });
        });

        it('of double letter', () => {
            expect(histogram_for('aa')).toEqual({ a: 2 });
        });

        it('of two distinct letters', () => {
            expect(histogram_for('ab')).toEqual({ a: 1, b: 1 });
        });

        it('of two distinct letters with various counts', () => {
            expect(histogram_for('ababb')).toEqual({ a: 2, b: 3 });
        });

        it('of all letters', () => {
            expect(histogram_for('abcdefghijklmnopqrstuvwxyz')).
                toEqual({
                    a: 1, b: 1, c: 1, d: 1, e: 1, f: 1, g: 1, h: 1, i: 1,
                    j: 1, k: 1, l: 1, m: 1, n: 1, o: 1, p: 1, q: 1, r: 1,
                    s: 1, t: 1, u: 1, v: 1, w: 1, x: 1, y: 1, z: 1
                });
        });

        it('of "documenting"', () => {
            expect(histogram_for('documenting')).
                toEqual({ d: 1, o: 1, c: 1, u: 1, m: 1, e: 1, n: 2, t: 1, i: 1, g: 1 })
        });

        it('equals', () => {
            // This condition will always return 'false' since JavaScript compares objects by reference, not value.ts(2839)
            // expect({ d: 1, o: 2 } === { o: 2, d: 1 }).toEqual(true);
            expect(histogram_equals({ d: 1, o: 2 }, { o: 2, d: 1 })).toEqual(true);
        });

        it('not equals different values same key', () => {
            expect(histogram_equals({ a: 1 }, { a: 2 })).toEqual(false);
            expect(histogram_equals({ a: 1 }, {})).toEqual(false);
        });

        it('add two histograms', () => {
            expect(histogram_add({ a: 1 }, { a: 1 })).toEqual({ a: 2 })
            expect(histogram_add({ a: 1 }, { b: 1 })).toEqual({ a: 1, b: 1 });
        });

    });

    describe('Word list', () => {

        it('lists all words', () => {
            expect(load_words()[0]).toEqual('acrobat')
            expect(load_words()[7]).toEqual('gin')
        });

        it('lists all two word sequences', () => {
            const actual = all_two_words_from(['a', 'b', 'c']);
            expect(actual.length).toEqual(9);
            expect(actual[0]).toEqual('a a');
            expect(actual[1]).toEqual('a b');
            expect(actual[2]).toEqual('a c');
            expect(actual[3]).toEqual('b a');
            expect(actual[4]).toEqual('b b');
            expect(actual[5]).toEqual('b c');
            expect(actual[6]).toEqual('c a');
            expect(actual[7]).toEqual('c b');
            expect(actual[8]).toEqual('c c');
        });

    });

    describe('Anagrams of all individual words', () => {

        it('find histograms of all words with different histograms', () => {
            const actual = all_anagrams(['abc'])
            expect(actual.length).toEqual(1)
            expect(actual[0].word).toEqual('abc')
            expect(actual[0].histogram).toEqual({ a: 1, b: 1, c: 1 })
        });

        it('find histograms of all words with same histogram', () => {
            const actual = all_anagrams(['abc', 'cba'])
            expect(actual.length).toEqual(2)
            expect(actual[0].word).toEqual('abc')
            expect(actual[0].histogram).toEqual({ a: 1, b: 1, c: 1 })
            expect(actual[1].word).toEqual('cba')
            expect(actual[1].histogram).toEqual({ a: 1, b: 1, c: 1 })
        });

    });

    describe('Solution', () => {
        it('all anagrams of documenting', () => {
            const actual = two_word_anagrams_of('documenting');
            expect(actual.length).toEqual(2)
            expect(actual[0]).toEqual('document gin')
            expect(actual[1]).toEqual('gin document')
        });
    });

});

function load_words(): string[] {
    return fs.readFileSync('./tests/wordlist.txt', 'utf8').
        trim().
        split(/\s+/g);
}

type Word = {
    histogram: Histogram,
    word: string
};

function all_anagrams(words: string[]): Word[] {
    const result: Word[] = [];

    for (const word of words) {
        const histogram = histogram_for(word);

        const newAnagram = { histogram, word };
        result.push(newAnagram);
    }

    return result;
}

function all_two_words_from(words: string[]): string[] {
    const result: string[] = [];
    for (let outer = 0; outer < words.length; outer++) {
        const word = words[outer];
        for (let inner = 0; inner < words.length; inner++) {
            const second = words[inner];
            result.push(`${word} ${second}`)
        }
    }
    return result;
}

function two_word_anagrams_of(word: string): string[] {
    const histogram = histogram_for(word);
    const allTwoWords = all_two_words_from(load_words()); // half second
    const reduced = allTwoWords.filter(w => w.length === word.length + 1); // reduces from 2500K -> 600K
    const allAnagrams = all_anagrams(reduced);
    const matches = allAnagrams.filter(anagram => histogram_equals(anagram.histogram, histogram));
    return matches.map((match) => match.word);
}
