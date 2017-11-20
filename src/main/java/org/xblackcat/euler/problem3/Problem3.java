package org.xblackcat.euler.problem3;

import gnu.trove.set.TLongSet;
import org.xblackcat.euler.util.PrimalCache;
import org.xblackcat.euler.util.TUtils;

/**
 * https://projecteuler.net/problem=3
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem3 {
    public static void main(String[] args) {
        long limit = 600851475143L;
        long start = System.currentTimeMillis();
        final Problem3 p = new Problem3();
        System.out.println("Largest prime factor for  " + limit + ": " + p.largestPrimeFactor(limit) + " in " +
                                   (System.currentTimeMillis() - start) + " ms");
    }

    public long largestPrimeFactor(long number) {
        final PrimalCache primals = new PrimalCache();

        final TLongSet set = primals.factorize(number);

        return TUtils.max(set);
    }
}
