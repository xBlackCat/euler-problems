package org.xblackcat.euler.problem26;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.NumberUtils;
import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=26
 * <p>
 * 29.11.2017 17:45
 *
 * @author xBlackCat
 */
@InputData("1000")
@ResultDescription("The value of d < {1,number,#} for which 1/d contains the longest recurring cycle in its decimal fraction part is {0,number,#}")
public class Problem26 {
    @EntryPoint
    public long searchPeriodicNumbersTill(int n) {
        PrimalCache primalCache = new PrimalCache();

        /*
         * As only primal numbers (except 2 and 5 for decimal base) could produce repeating decimals in 1/p we will check only the primal numbers
         */
        long v = 0;
        int maxLen = 0;
        for (long primal : primalCache) {
            if (primal > n) {
                break;
            }
            String period = NumberUtils.getPeriod(primal);
            if (period.length() > maxLen) {
                maxLen = period.length();
                v = primal;
            }
        }
        return v;
    }

}
