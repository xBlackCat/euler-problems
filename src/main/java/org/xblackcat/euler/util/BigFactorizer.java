package org.xblackcat.euler.util;

import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BigFactorizer extends AFactorizer<BigInteger, TObjectIntMap<BigInteger>> {
    private final Map<BigInteger, TObjectIntMap<BigInteger>> primalFactorsMapCache = new HashMap<>();

    public BigFactorizer() {
        super();
    }

    public BigFactorizer(PrimalCache primalCache) {
        super(primalCache);
    }

    /**
     * Returns primal factors as map: key = factor, value = exponent
     *
     * @param number number to factorize
     * @return factors of the number
     */
    @Override
    public TObjectIntMap<BigInteger> factorize(BigInteger number) {
        final TObjectIntMap<BigInteger> cachedMap = primalFactorsMapCache.get(number);
        if (cachedMap != null) {
            return cachedMap;
        }

        TObjectIntMap<BigInteger> result = new TObjectIntHashMap<>();
        doFactorizeMap(result, number, true);

        primalFactorsMapCache.put(number, result);
        return result;
    }

    private boolean isPrimal(BigInteger number) {
        try {
            long n = number.longValueExact();
            return primalCache.isPrimal(n);
        } catch (Exception e) {
            return number.isProbablePrime(1024);
        }
    }

    private void doFactorizeMap(TObjectIntMap<BigInteger> result, BigInteger number, boolean firstElement) {
        if (number.compareTo(BigInteger.ONE) < 0) {
            return;
        }

        if (isPrimal(number)) {
            if (!firstElement) {
                result.adjustOrPutValue(number, 1, 1);
            }
            return;
        }

        final TObjectIntMap<BigInteger> cachedMap = primalFactorsMapCache.get(number);
        if (cachedMap != null) {
            cachedMap.forEachEntry((a, b) -> {
                result.adjustOrPutValue(a, b, b);
                return true;
            });
            return;
        }

        BigInteger limit = number.sqrt();
        for (long f : primalCache.firstPrimals) {
            final BigInteger factor = BigInteger.valueOf(f);
            if (factor.compareTo(limit) > 0) {
                return;
            }
            final BigInteger[] divideAndRemainder = number.divideAndRemainder(factor);
            if (divideAndRemainder[1].equals(BigInteger.ZERO)) {
                result.adjustOrPutValue(factor, 1, 1);
                doFactorizeMap(result, divideAndRemainder[0], false);
                return;
            }
        }

        BigInteger largestPrimal = BigInteger.valueOf(21);
        while (largestPrimal.compareTo(limit) <= 0) {
            if (isPrimal(largestPrimal)) {
                final BigInteger[] divideAndRemainder = number.divideAndRemainder(largestPrimal);
                if (divideAndRemainder[1].equals(BigInteger.ZERO)) {
                    result.adjustOrPutValue(largestPrimal, 1, 1);
                    doFactorizeMap(result, divideAndRemainder[0], false);
                    return;
                }
            }
            largestPrimal = largestPrimal.add(BigInteger.TWO);
        }
    }
}
