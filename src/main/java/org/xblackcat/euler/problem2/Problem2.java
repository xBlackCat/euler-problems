package org.xblackcat.euler.problem2;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

/**
 * https://projecteuler.net/problem=2
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("4000000")
@ResultDescription("Sum of Fibonacci numbers till {1,number,#}: {0,number,#}")
public class Problem2 {
    @EntryPoint
    public long sumOfFNumbers(long limit) {
        long a;
        long b = 1;
        long c = 2;
        long sum = 0;

        while (c < limit) {
            if ((c & 1) == 0) {
                sum += c;
            }
            a = b;
            b = c;
            c = a + b;
        }
        return sum;
    }
}
