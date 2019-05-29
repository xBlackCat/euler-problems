package org.xblackcat.euler.problem3;

import gnu.trove.list.TLongList;
import gnu.trove.map.TLongIntMap;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;
import org.xblackcat.euler.util.TFactorizer;

import java.util.Arrays;

/**
 * https://projecteuler.net/problem=3
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@ResultDescription("Largest prime factor for {1,number,#}: {0,number,#}")
@InputData("600851475143")
public class Problem3 {
    @EntryPoint
    public long largestPrimeFactor(long number) {
        final TFactorizer primals = new TFactorizer();

        final TLongIntMap set = primals.factorize(number);

        final long[] keys = set.keys();
        Arrays.sort(keys);

        return keys[keys.length - 1];
    }
}
