package org.xblackcat.euler.problem1;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.NumberUtils;

/**
 * https://projecteuler.net/problem=1
 *
 * @author xBlackCat
 */
@InputData("1000")
@ResultDescription("Sum triples and fives till {1,number,#}: {0,number,#}")
public class Problem1 {
    @EntryPoint
    public long sumOfNumbers(long num) {
        long triples = (num - 1) / 3;
        long fives = (num - 1) / 5;
        long fifteens = (num - 1) / 15;

        return NumberUtils.sumNumber(triples) * 3 + NumberUtils.sumNumber(fives) * 5 - NumberUtils.sumNumber(fifteens) * 15;
    }
}
