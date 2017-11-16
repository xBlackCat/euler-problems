package org.xblackcat.euler.util;

/**
 * 16.11.2017 15:40
 *
 * @author xBlackCat
 */
public class NumberUtils {
    public static int digitSum(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
