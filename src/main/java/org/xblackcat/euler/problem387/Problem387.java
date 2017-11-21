package org.xblackcat.euler.problem387;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

/**
 * Solving problem https://projecteuler.net/problem=387
 * <p>
 * 15.11.2017 16:39
 *
 * @author xBlackCat
 */
@InputData("100000000000000")
@ResultDescription("Sum of the strong, right truncatable Harshad primes less than {1,number,#} is {0,number,#}")
public class Problem387 {
    private PrimalCache primalCache = new PrimalCache();

    @EntryPoint
    public long sumRecursiveStart(long limit) {
        long sum = 0;

        for (int i = 1; i < 10; i++) {
            sum += sumRecursive(new Num(i), limit);
        }

        return sum;
    }

    private long sumRecursive(Num prefix, long limit) {
        if (prefix.getValue() > limit) {
            return 0;
        }
        if (!prefix.isHarshad()) {
            return 0;
        }
        long sum = 0;
        if (primalCache.isPrimal(prefix.getQuotient())) {
            // Could be required number
            long pr = prefix.getValue() * 10;
            for (int i = 1; i < 10; i += 2) {
                final long possibleSRTHP = pr + i;
                if (possibleSRTHP > limit) {
                    continue;
                }
                if (primalCache.isPrimal(possibleSRTHP)) {
                    sum += possibleSRTHP;
//                    System.out.println("[*] " + possibleSRTHP);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            sum += sumRecursive(prefix.append(i), limit);
        }
        return sum;
    }

}
