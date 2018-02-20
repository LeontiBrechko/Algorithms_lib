package math;

import java.util.*;

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

//    private int gcd(int a, int b) {
//        if (a < b) {
//            int temp = a;
//            a = b;
//            b = temp;
//        }
//        while (b != 0) {
//            int temp = a % b;
//            a = b;
//            b = temp;
//        }
//        return Math.abs(a);
//    }

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
        int y1 = x - (x);
    }

    private long powRightToLeft(long x, long pow, long mod) {
        if (x == 0) return pow == 0 ? 1 : 0;

        long res = 1;
        long e = x;
        while (pow > 0) {
            if ((pow & 1) > 0) res = (res * e) % mod;
            e = (e * e) % mod;
            pow >>= 1;
        }

        return res;
    }
}
