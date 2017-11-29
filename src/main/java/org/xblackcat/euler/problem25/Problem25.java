package org.xblackcat.euler.problem25;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=25
 * <p>
 * 29.11.2017 17:28
 *
 * @author xBlackCat
 */
@InputData("1000")
@ResultDescription("The index of the first term in the Fibonacci sequence to contain {1,number,#} digits is {0,number,#}")
public class Problem25 {
    @EntryPoint
    public long indexOfFibonacciOfNDigits(int n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        BigInteger c = a.add(b);

        if (n == 1) {
            return 1;
        }

        int i = 3;
        do {
            a = b;
            b = c;
            c = a.add(b);
            i++;
        } while (c.toString().length() < n);

        return i;
    }
}
