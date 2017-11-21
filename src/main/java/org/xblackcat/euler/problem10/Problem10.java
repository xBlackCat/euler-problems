package org.xblackcat.euler.problem10;

import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=5
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem10 {
    public static void main(String[] args) {
        int till = 2_000_000;
        long start = System.currentTimeMillis();
        final Problem10 p = new Problem10();
        System.out.println(
                "Sum of all the primes below  " + till + " is " + p.primalSum(till) +
                        " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long primalSum(long till) {
        PrimalCache primalCache = new PrimalCache();

        long sum = 0;
        for (Long primal : primalCache) {
            if (primal > till) {
                break;
            }
            sum += primal;
        }
        return sum;
    }
}
