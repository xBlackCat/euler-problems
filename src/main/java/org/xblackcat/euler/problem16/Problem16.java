package org.xblackcat.euler.problem16;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

import java.math.BigInteger;

/**
 * * https://projecteuler.net/problem=16
 * <p>
 * 27.11.2017 16:15
 *
 * @author xBlackCat
 */
@InputData("1000")
@ResultDescription("The sum of the digits of the number 2^{1,number,#} is {0,number,#}")
public class Problem16 {
    @EntryPoint
    public int sumOfDigits(int pow) {
        String s = BigInteger.ONE.shiftLeft(pow).toString();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i) - '0';
        }
        return sum;
    }
}
