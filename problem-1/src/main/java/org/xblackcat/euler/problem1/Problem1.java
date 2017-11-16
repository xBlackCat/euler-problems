package org.xblackcat.euler.problem1;

import org.xblackcat.euler.util.NumberUtils;

/**
 * https://projecteuler.net/problem=1
 *
 * @author xBlackCat
 */
public class Problem1 {
    public static void main(String[] args) {
        long limit = 1000;
        long start = System.currentTimeMillis();
        final Problem1 p = new Problem1();
        System.out.println("Sum till " + limit + ": " + p.sumOfNumbers(limit) + " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long sumOfNumbers(long num) {
        long triples = (num - 1) / 3;
        long fives = (num - 1) / 5;
        long fifteens = (num - 1) / 15;

        return NumberUtils.sumNumber(triples) * 3 + NumberUtils.sumNumber(fives) * 5 - NumberUtils.sumNumber(fifteens) * 15;
    }
}
