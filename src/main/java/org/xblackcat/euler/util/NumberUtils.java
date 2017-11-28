package org.xblackcat.euler.util;

import java.math.BigInteger;

/**
 * 16.11.2017 15:40
 *
 * @author xBlackCat
 */
public class NumberUtils {
    public static int digitSum(BigInteger num) {
        int sum = 0;
        String s = num.toString(10);
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i) - '0';
        }
        return sum;
    }

    public static int digitSum(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static long sumNumber(long till) {
        if ((till & 1) == 0) {
            return (till >> 1) * (till + 1);
        } else {
            return ((till + 1) >> 1) * till;
        }
    }

    public static long sumSquares(long till) {
        if ((till & 1) == 0) {
            return (till >> 1) * (till + 1) * (2 * till + 1) / 3;
        } else {
            return ((till + 1) >> 1) * till * (2 * till + 1) / 3;
        }
    }
}
