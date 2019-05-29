package org.xblackcat.euler.problem12;

import gnu.trove.map.TLongIntMap;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.MathUtils;
import org.xblackcat.euler.util.TFactorizer;

/**
 * https://projecteuler.net/problem=10
 * <p>
 * 24.11.2017 8:29
 *
 * @author xBlackCat
 */
@InputData("500")
@ResultDescription("The first triangle number to have over {1} divisors is {0,number,#}")
public class Problem12 {
    @EntryPoint
    public long triangleNumberWithDivisors(int over) {
        long largestAmount = 1;
        TFactorizer primals = new TFactorizer();
        long n = 1;
        long i = 1;
        while (true) {
            TLongIntMap factorsMap = primals.factorize(n);

            long allFactorsAmount = MathUtils.allFactorsCount(factorsMap);
            if (largestAmount < allFactorsAmount) {
                largestAmount = allFactorsAmount;
                if (largestAmount >= over) {
                    return n;
                }
            }

            i++;
            n += i;
        }
    }
}
