package org.xblackcat.euler.util;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;

import java.math.BigInteger;

/**
 * 28.11.2017 12:38
 *
 * @author xBlackCat
 */
public class FactorialCache {
    private final TLongObjectMap<BigInteger> factorials = new TLongObjectHashMap<>();

    public BigInteger factorial(long n) {
        if (n <= 1) {
            return BigInteger.ONE;
        }
        if (factorials.containsKey(n)) {
            return factorials.get(n);
        }

        BigInteger v = BigInteger.valueOf(n).multiply(factorial(n - 1));
        factorials.put(n, v);
        return v;
    }
}
