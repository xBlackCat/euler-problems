package org.xblackcat.euler.problem9;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PythagoreanTriplets;
import org.xblackcat.euler.util.Triplet;

/**
 * https://projecteuler.net/problem=9
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("1000")
@ResultDescription("Product of Pythagorean triplet with sum {1,number,#} is {0,number,#}" )
public class Problem9 {
    @EntryPoint
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
