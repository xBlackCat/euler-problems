package org.xblackcat.euler.util;

import gnu.trove.map.TLongIntMap;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.map.hash.TLongObjectHashMap;

/**
 *
 */
public class TFactorizer extends AFactorizer<Long, TLongIntMap> {
    private final TLongObjectMap<TLongIntMap> primalFactorsMapCache = new TLongObjectHashMap<>();

    public TFactorizer() {
        super();
    }

    public TFactorizer(PrimalCache primalCache) {
        super(primalCache);
    }

    /**
     * Returns primal factors as map: key = factor, value = exponent
     *
     * @param number number to factorize
     * @return factors of the number
     */
    @Override
    public TLongIntMap factorize(Long number) {
        final TLongIntMap cachedMap = primalFactorsMapCache.get(number);
        if (cachedMap != null) {
            return cachedMap;
        }

        TLongIntMap result = new TLongIntHashMap();
        doFactorizeMap(result, number, true);

        primalFactorsMapCache.put(number, result);
        return result;
    }

    private void doFactorizeMap(TLongIntMap result, long number, boolean firstElement) {
        if (number <= 0) {
            return;
        }
        if (primalCache.isPrimal(number)) {
            if (!firstElement) {
                result.adjustOrPutValue(number, 1, 1);
            }
            return;
        }

        final TLongIntMap cachedMap = primalFactorsMapCache.get(number);
        if (cachedMap != null) {
            cachedMap.forEachEntry((a, b) -> {
                result.adjustOrPutValue(a, b, b);
                return true;
            });
            return;
        }

        long limit = (long) Math.sqrt(number);
        for (long factor : primalCache.firstPrimals) {
            if (factor > limit) {
                return;
            }
            if (number % factor == 0) {
                result.adjustOrPutValue(factor, 1, 1);
                doFactorizeMap(result, number / factor, false);
                return;
            }
        }

        long largestPrimal = 23;
        while (largestPrimal <= limit) {
            if (primalCache.isPrimal(largestPrimal)) {
                if (number % largestPrimal == 0) {
                    result.adjustOrPutValue(largestPrimal, 1, 1);
                    doFactorizeMap(result, number / largestPrimal, false);
                    return;
                }
            }
            largestPrimal += 2;
        }
    }
}
