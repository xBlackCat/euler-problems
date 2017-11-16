package org.xblackcat.euler.problem2;

/**
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem2 {
    public static void main(String[] args) {
        long limit = 4_000_000;
        long start = System.currentTimeMillis();
        final Problem2 p = new Problem2();
        System.out.println("Sum till " + limit + ": " + p.sumOfFNumbers(limit) + " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long sumOfFNumbers(long limit) {
        long a = 1;
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
