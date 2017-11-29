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

    public static int[] parseLine(String line) {
        return parseLine(line, " ");
    }

    public static int[] parseLine(String line, String splitBy) {
        String[] parts = line.split(splitBy);
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

    public static String getPeriod(long num) {
        return getPeriod(num, 10);
    }

    public static String getPeriod(long num, int base) {
        long n = base;

        StringBuilder fraction = new StringBuilder();
        SearchLoop:
        while (true) {
            while (n < num) {
                n *= base;
                fraction.append('0');
            }
            long d = n / num;
            fraction.append((char) ('0' + d));
            n %= num;
            if (n == 0) {
                return "";
            }
            n *= base;
            if ((fraction.length() & 1) == 0) {
                // Check if we reach period
                int length = fraction.length() >> 1;
                for (int i = 0; i < length; i++) {
                    if (fraction.charAt(i) != fraction.charAt(i + length)) {
                        continue SearchLoop;
                    }
                }
                break;
            }
        }

        return fraction.substring(0, fraction.length() >> 1);
    }
}
