package org.xblackcat.euler.problem12;

import gnu.trove.list.TLongList;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.hash.TLongIntHashMap;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;

/**
 * https://projecteuler.net/problem=10
 * <p>
 * 24.11.2017 8:29
 *
 * @author xBlackCat
 */
@InputData("500")
@ResultDescription("The first triangle number to have over {1} divisors is {0,number,#}")
public class Problem12 {
    @EntryPoint
    public long triangleNumberWithDivisors(int over) {
        long largestAmount = 1;
        PrimalCache primals = new PrimalCache();
        long n = 1;
        long i = 1;
        while (true) {
            TLongList factors = primals.factorize(n);
            TLongIntMap factorsMap = new TLongIntHashMap();
            factors.forEach(
                    f -> {
                        factorsMap.adjustOrPutValue(f, 1, 1);
                        return true;
                    }
            );

            int allFactorsAmount = 1;
            for (int amount : factorsMap.values()) {
                allFactorsAmount *= amount + 1;
            }
            if (largestAmount < allFactorsAmount) {
                largestAmount = allFactorsAmount;
                if (largestAmount >= over) {
                    return n;
                }
            }

            i++;
            n += i;
        }
    }
}
