package strings.search;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by leonti on 2017-10-14.
 */
public class RabinKarp {
    private final int PRIME;
    private final int X;
    private final int XP;

    private final String PATTERN;
    private final int PATTERN_HASH;
    private final int PATTERN_LENGTH;


    public RabinKarp(String pattern) {
        PRIME = BigInteger.probablePrime(31, new Random()).intValue();
        X = new Random().nextInt(PRIME);
        PATTERN = pattern;
        PATTERN_HASH = polynomialHash(PATTERN);
        PATTERN_LENGTH = PATTERN.length();

        int xp = 1;
        for (int i = 0; i < PATTERN_LENGTH; i++) xp = (int) (((long) xp * X) % PRIME);
        XP = xp;
    }

    public ArrayList<Integer> search(String text) {
        int n = text.length();
        ArrayList<Integer> res = new ArrayList<>();
        int[] hashes = precomputeHashes(text);

        for (int i = 0; i <= n - PATTERN_LENGTH; i++)
            if (PATTERN_HASH == hashes[i] && PATTERN.equals(text.substring(i, i + PATTERN_LENGTH)))
                res.add(i);

        return res;
    }

    private int polynomialHash(String s) {
        long hash = 0;
        int x = 1;
        for (int i = 0; i < s.length(); i++, x = (int) (((long) x * X) % PRIME)) {
            hash = (hash + ((long) s.charAt(i) * x) % PRIME) % PRIME;
        }
        return (int) hash;
    }

    private int[] precomputeHashes(String text) {
        int n = text.length();
        int m = PATTERN.length();
        int[] hashes = new int[n - m + 1];

        hashes[n - m] = polynomialHash(text.substring(n - m, n));
        for (int i = n - m - 1; i >= 0; i--)
            hashes[i] = (int) (((long) hashes[i + 1] * X - (long) XP * text.charAt(i + m) + text.charAt(i)) % PRIME);

        return hashes;
    }
}
