package org.xblackcat.euler.problem9;

import org.xblackcat.euler.util.PythagoreanTriplets;
import org.xblackcat.euler.util.Triplet;

/**
 * https://projecteuler.net/problem=5
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem9 {
    public static void main(String[] args) {
        int till = 1_000;
        long start = System.currentTimeMillis();
        final Problem9 p = new Problem9();
        System.out.println(
                "Product of Pythagorean triplet with sum " + till + " is " + p.tripletWithSum(till) +
                        " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long tripletWithSum(long sum) {
        PythagoreanTriplets triplets = new PythagoreanTriplets();
        for (Triplet t : triplets) {
            long tSum = t.sum();
            if (tSum > sum) {
                throw new IllegalStateException("Not found");
            }
            if (sum % tSum == 0) {
                Triplet triplet = t.multiply(sum / tSum);
                System.out.println(triplet);
                return triplet.product();
            }
        }
        throw new RuntimeException("Never should be");
    }

}
