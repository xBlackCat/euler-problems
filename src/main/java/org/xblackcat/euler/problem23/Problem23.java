package org.xblackcat.euler.problem23;

import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

import java.util.Arrays;

/**
 * https://projecteuler.net/problem=23
 * <p>
 * 29.11.2017 10:20
 *
 * @author xBlackCat
 */
@InputData({})
@ResultDescription("The sum of all the positive integers which cannot be written as the sum of two abundant numbers is {0,number,#}")
public class Problem23 {

    public static final int LARGEST_NUMBER_TO_CHECK = 28123;

    @EntryPoint
    public long sum() {
        PrimalCache primalCache = new PrimalCache();
        final TLongSet abundantNumbers = new TLongHashSet();

        for (int i = 1; i < LARGEST_NUMBER_TO_CHECK; i++) {
            TLongSet factors = primalCache.allFactors(i);
            long sumOfFactors = Arrays.stream(factors.toArray()).sum();

            if (sumOfFactors > i) {
//                System.out.println(
//                        i + ":\t" + sumOfFactors + " = " +
//                                Arrays.stream(factors.toArray()).sorted().mapToObj(String::valueOf).collect(Collectors.joining(" + ")));
                abundantNumbers.add(i);
            }
        }

        long sum = 0;
        long[] longs = abundantNumbers.toArray();
        Arrays.sort(longs);
        System.out.println(Arrays.toString(longs));
        NextNumber:
        for (int i = 1; i < LARGEST_NUMBER_TO_CHECK; i++) {
            for (long a : longs) {
                if (abundantNumbers.contains(i - a)) {
//                    System.out.println(i + " = " + a + " + " + (i - a));
                    continue NextNumber;
                }
            }
//            System.out.println(" --> " + i);
            sum += i;
        }

        return sum;
    }
}
