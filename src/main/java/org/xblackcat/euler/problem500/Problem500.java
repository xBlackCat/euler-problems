package org.xblackcat.euler.problem500;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.MathUtils;
import org.xblackcat.euler.util.PrimalCache;
import org.xblackcat.euler.util.SparseFactorsMap;

import java.util.Iterator;

/**
 * Solving problem https://projecteuler.net/problem=500
 * <p>
 * 15.11.2017 16:39
 *
 * @author xBlackCat
 */
@InputData("500500")
@ResultDescription("The smallest number with 2^({1,number,#}) divisors mod 500500507 is {0,number,#}")
public class Problem500 {
    private final static long MOD = 500500507;

    @EntryPoint
    public long smallestNumber(int n) {
        final var primalCache = new PrimalCache();

//        final var factors = new SparseFactorsMap();
        final var factorsPow = new SparseFactorsMap();

        int pows = 0;

        long r = 1;

        long limit = 4;
        long limitBase = 2;

        Iterator<Long> iterator = primalCache.iterator();
        long p;
        while (pows < n) {
            p = iterator.next();
            if (p > limit) {
                r = (r * limit) % MOD;
                final int newPow = factorsPow.adjustOrPutValue(limitBase, 1, 1);

//                factors.put(limitBase, (1 << newPow) - 1);
                pows++;
                if (pows == n) {
                    break;
                }

                long minimalExp = Long.MAX_VALUE;
                long minimalBase = 0;
                for (long key : factorsPow.keys()) {
                    int nextExp = 1 << factorsPow.get(key);
                    long newLimit = MathUtils.pow(key, nextExp);
                    if (newLimit > p && minimalExp > newLimit) {
                        minimalExp = newLimit;
                        minimalBase = key;
                    }
                }
                limit = minimalExp;
                limitBase = minimalBase;
            }
            r = (r * p) % MOD;
//            factors.put(p, 1);
            factorsPow.put(p, 1);
            pows++;
        }

//        System.out.println(factors.value(MOD) + ": " + factors);
//        System.out.println(factorsPow);
        return r;
    }
}
