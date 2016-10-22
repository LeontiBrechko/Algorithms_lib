package math;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;

/**
 * Created by Leonti on 2016-10-12.
 */
public class NumberTheory {
    private static ArrayList<Integer> sieve(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] isNotPrime = new boolean[n + 1];
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i < n + 1; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j < n + 1; j += i) {
                    isNotPrime[j] = true;
                }
                primes.add(i);
            }
        }
        return primes;
    }

    private int gcd(int a, int b) {return b == 0 ? a : gcd(b, a % b);}
    private int lcm(int a, int b) {return a * (b / gcd(a, b));}

    private ArrayList<Integer> primeFactors(int n) {
        ArrayList<Integer> factors = new ArrayList<>();
        ArrayList<Integer> primes = sieve(n);
        int primeFactorIndex = 0;
        int primeFactor = primes.get(primeFactorIndex);
        while (primeFactor * primeFactor <= n) {
            while (n % primeFactor == 0) {
                n /= primeFactor;
                factors.add(primeFactor);
            }
            primeFactor = primes.get(++primeFactorIndex);
        }
        if (n != 1) factors.add(n);
        return factors;
    }

    // if gdc(a,b) % c == 0
    int x, y, d;
    private void extendedEuclid(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            d = a;
            return;
        }
        extendedEuclid(b, a % b);
        int x1 = y;
        int y1 = x - (x)
    }
}
