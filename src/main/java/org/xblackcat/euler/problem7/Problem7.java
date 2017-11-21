package org.xblackcat.euler.problem7;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=7
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("10001")
@ResultDescription("{1}th prime number is {0,number,#}")
public class Problem7 {
    @EntryPoint
    public long nthPrime(int nthPrime) {
        PrimalCache primalCache = new PrimalCache();
        return primalCache.primalStream().skip(nthPrime - 1).findFirst().orElse(0);
    }
}
