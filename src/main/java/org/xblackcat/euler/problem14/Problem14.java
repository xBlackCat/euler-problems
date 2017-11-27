package org.xblackcat.euler.problem14;

import gnu.trove.map.TLongIntMap;
import gnu.trove.map.hash.TLongIntHashMap;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

/**
 * https://projecteuler.net/problem=14
 * <p>
 * 25.11.2017 12:33
 *
 * @author xBlackCat
 */
@InputData("1000000")
@ResultDescription("Starting number {0,number,#}, under {1,number,#}, produces the longest chain")
public class Problem14 {
    private final TLongIntMap chainLengthCache = new TLongIntHashMap();

    @EntryPoint
    public long findNuberWithLongestChain(long under) {
        long largest = 0;
        int longest = 0;
        for (long i = 1; i < under; i++) {
            final int length = chainLength(i);
            if (length == longest) {
                largest = i;
            }
            if (length > longest) {
                longest = length;
                largest = i;
            }
        }

        return largest;
    }

    protected int chainLength(long n) {
        if (n == 1) {
            return 1;
        }
        if (chainLengthCache.containsKey(n)) {
            return chainLengthCache.get(n);
        }
        final int length;
        if ((n & 1) == 0) {
            // even
            length = chainLength(n >> 1);
        } else {
            length = chainLength(n * 3 + 1);
        }
        chainLengthCache.put(n, length + 1);
        return length + 1;
    }
}
