package org.xblackcat.euler.problem10;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=10
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("2000000")
@ResultDescription("Sum of all the primes below {1,number,#} is {0,number,#}")
public class Problem10 {
    @EntryPoint
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
