package org.xblackcat.euler.problem5;

import gnu.trove.iterator.TLongIntIterator;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.set.TLongSet;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=5
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("20")
@ResultDescription("Smallest number that can be divided by each of the numbers from 1 till {1} is {0,number,#}")
public class Problem5 {
    @EntryPoint
    public long smallestNumber(long till) {
        TLongIntMap factors = new TLongIntHashMap();
        PrimalCache primalCache = new PrimalCache();

        final double tillLog = Math.log(till);
        TLongSet primalsTill = primalCache.getPrimalsTill(till);
        primalsTill.forEach(
                f -> {
                    int amount = (int) Math.floor(tillLog / Math.log(f));
                    if (amount > 0) {
                        factors.put(f, amount);
                    }
                    return true;
                }
        );

        long result = 1;
        TLongIntIterator it = factors.iterator();
        while (it.hasNext()) {
            it.advance();
            result *= (long) Math.pow(it.key(), it.value());
        }
        return result;
    }

}
