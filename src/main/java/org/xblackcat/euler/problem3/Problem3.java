package org.xblackcat.euler.problem3;

import gnu.trove.list.TLongList;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

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
        final PrimalCache primals = new PrimalCache();

        final TLongList set = primals.factorize(number);

        return set.get(set.size() - 1);
    }
}
