package org.xblackcat.euler.problem6;

import org.xblackcat.euler.util.NumberUtils;

/**
 * https://projecteuler.net/problem=5
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem6 {
    public static void main(String[] args) {
        long till = 100;
        long start = System.currentTimeMillis();
        final Problem6 p = new Problem6();
        System.out.println(
                "Difference between the sum of the squares and the square of the sum " + till + ": " + p.sumDifferences(till) +
                        " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long sumDifferences(long till) {
        long sumNumber = NumberUtils.sumNumber(till);
        return sumNumber * sumNumber - NumberUtils.sumSquares(till);
    }

}
