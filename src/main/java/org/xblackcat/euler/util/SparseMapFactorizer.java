package org.xblackcat.euler.util;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;

/**
 *
 */
public class SparseMapFactorizer extends AFactorizer<Long, SparseFactorsMap> {
    private final TLongObjectMap<SparseFactorsMap> primalFactorsMapCache = new TLongObjectHashMap<>();

    public SparseMapFactorizer() {
        super();
    }

    public SparseMapFactorizer(PrimalCache primalCache) {
        super(primalCache);
    }

    /**
     * Returns primal factors as map: key = factor, value = exponent
     *
     * @param number number to factorize
     * @return factors of the number
     */
    @Override
    public SparseFactorsMap factorize(Long number) {
        final SparseFactorsMap cachedMap = primalFactorsMapCache.get(number);
        if (cachedMap != null) {
            return cachedMap;
        }

        SparseFactorsMap result = new SparseFactorsMap();
        doFactorizeMap(result, number, true);

        primalFactorsMapCache.put(number, result);
        return result;
    }

    private void doFactorizeMap(SparseFactorsMap result, long number, boolean firstElement) {
        if (number <= 0) {
            return;
        }
        if (primalCache.isPrimal(number)) {
            if (!firstElement) {
                result.adjustOrPutValue(number, 1, 1);
            }
            return;
        }

        final SparseFactorsMap cachedMap = primalFactorsMapCache.get(number);
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
