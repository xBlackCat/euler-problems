package org.xblackcat.euler.problem6;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.NumberUtils;

/**
 * https://projecteuler.net/problem=6
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("100")
@ResultDescription("Difference between the sum of the squares and the square of the sum till {1} is {0,number,#}")
public class Problem6 {
    @EntryPoint
    public long sumDifferences(long till) {
        long sumNumber = NumberUtils.sumNumber(till);
        return sumNumber * sumNumber - NumberUtils.sumSquares(till);
    }

}
