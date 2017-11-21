package org.xblackcat.euler.problem7;

import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=5
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem7 {
    public static void main(String[] args) {
        int till = 10_001;
        long start = System.currentTimeMillis();
        final Problem7 p = new Problem7();
        System.out.println(
                till + "th prime number is " + p.nthPrime(till) +
                        " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long nthPrime(int nthPrime) {
        PrimalCache primalCache = new PrimalCache();
        return primalCache.primalStream().skip(nthPrime - 1).findFirst().orElse(0);
    }
}
