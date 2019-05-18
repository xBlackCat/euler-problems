package org.xblackcat.euler.util;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.list.TLongList;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.map.TLongByteMap;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongByteHashMap;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;

import java.util.Iterator;
import java.util.stream.LongStream;

/**
 * 15.11.2017 16:41
 *
 * @author xBlackCat
 */
public class PrimalCache implements Iterable<Long> {
    private final long[] firstPrimals = new long[]{2, 3, 5, 7, 11, 13, 17, 19};

    private final TLongByteMap foundPrimals = new TLongByteHashMap(
            new long[]{1, 2, 3, 5, 7, 9, 11, 13, 15, 17, 19},
            new byte[]{0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1}
    );

    private final TLongObjectMap<TLongList> primalFactorsCache = new TLongObjectHashMap<>();
    private final TLongObjectMap<TLongIntMap> primalFactorsMapCache = new TLongObjectHashMap<>();
    private final TLongObjectMap<SparseFactorsMap> primalFactorsSparseMapCache = new TLongObjectHashMap<>();

    public boolean isPrimal(long num) {
//        long start = System.currentTimeMillis();
        final boolean primal = checkPrimal(num);
//        System.out.println(
//                "[-] Number " + num + " has been checked for primal [" + primal + "] in " + (System.currentTimeMillis() - start)
//        );
        return primal;
    }

    public TLongIterator longIterator() {
        return new PrimalTIterator();
    }

    @Override
    public Iterator<Long> iterator() {
        return new PrimalIterator();
    }

    public LongStream primalStream() {
        PrimalGenerator generator = new PrimalGenerator();
        return LongStream.generate(generator::nextPrimal);
    }

    /**
     * Returns primal factors of the number in natural order. If the number is primal - return it
     *
     * @param number number to factorize
     * @return factors of the number in natural order
     */
    public TLongList factorize(long number) {
        final TLongList cachedMap = primalFactorsCache.get(number);
        if (cachedMap != null) {
            return cachedMap;
        }

        TLongList result = new TLongArrayList();
        doFactorize(result, number, true);
        result.sort();

        if (result.size() > 1) {
            primalFactorsCache.put(number, result);
        }
        return result;
    }

    /**
     * Returns primal factors as map: key = factor, value = exponent
     *
     * @param number number to factorize
     * @return factors of the number
     */
    public TLongIntMap factorizeMap(long number) {
        final TLongIntMap cachedMap = primalFactorsMapCache.get(number);
        if (cachedMap != null) {
            return cachedMap;
        }

        TLongIntMap result = new TLongIntHashMap();
        doFactorizeMap(result, number, true);

        primalFactorsMapCache.put(number, result);
        return result;
    }

    /**
     * Returns primal factors as map: key = factor, value = exponent
     *
     * @param number number to factorize
     * @return factors of the number
     */
    public SparseFactorsMap factorizeSparseMap(long number) {
        final SparseFactorsMap cachedMap = primalFactorsSparseMapCache.get(number);
        if (cachedMap != null) {
            return cachedMap;
        }

        SparseFactorsMap result = new SparseFactorsMap();
        doFactorizeSparseMap(result, number, true);

        primalFactorsSparseMapCache.put(number, result);
        return result;
    }

    /**
     * Returns primal factors of the number in natural order. If the number is primal - return it
     *
     * @param number number to factorize
     * @return factors of the number in natural order
     */
    public TLongSet allFactors(long number) {
        TLongList primalFactors = new TLongArrayList();
        doFactorize(primalFactors, number, true);
        TLongIntMap factorsMap = new TLongIntHashMap();
        primalFactors.forEach(f -> {
            factorsMap.adjustOrPutValue(f, 1, 1);
            return true;
        });

        TLongSet allFactors = collectAllFactors(factorsMap);
        allFactors.remove(number);
        return allFactors;
    }

    private TLongSet collectAllFactors(TLongIntMap factorsMap) {
        long[] keys = factorsMap.keys();
        if (keys.length == 1) {
            long primalFactor = keys[0];
            int amount = factorsMap.get(primalFactor);
            long f = 1;
            TLongHashSet result = new TLongHashSet();
            result.add(1);
            while (amount-- > 0) {
                f *= primalFactor;
                result.add(f);
            }
            return result;
        } else {
            TLongHashSet result = new TLongHashSet();
            for (long primalFactor : keys) {
                int amount = factorsMap.remove(primalFactor);
                TLongSet factors = collectAllFactors(factorsMap);
                factorsMap.put(primalFactor, amount);
                result.addAll(factors);
                long f = 1;
                while (amount-- > 0) {
                    f *= primalFactor;
                    TLongIterator it = factors.iterator();
                    while (it.hasNext()) {
                        result.add(f * it.next());
                    }
                }
            }
            return result;
        }
    }

    public TLongSet getPrimalsTill(long number) {
        TLongSet primals = new TLongHashSet();
        if (number > 2) {
            primals.add(2);
            long n = 3;
            while (n < number) {
                if (checkPrimal(n)) {
                    primals.add(n);
                }
                n += 2;
            }
        }
        return primals;
    }

    private void doFactorize(TLongList result, long number, boolean firstElement) {
        if (number <= 0) {
            return;
        }
        if (isPrimal(number)) {
            if (!firstElement) {
                result.add(number);
            }
            return;
        }

        final TLongList cachedFactors = primalFactorsCache.get(number);
        if (cachedFactors != null) {
            result.addAll(cachedFactors);
            return;
        }

        long limit = (long) Math.sqrt(number);
        for (long factor : firstPrimals) {
            if (factor > limit) {
                return;
            }
            if (number % factor == 0) {
                result.add(factor);
                doFactorize(result, number / factor, false);
                return;
            }
        }

        long largestPrimal = 23;
        while (largestPrimal <= limit) {
            if (checkPrimal(largestPrimal)) {
                if (number % largestPrimal == 0) {
                    result.add(largestPrimal);
                    doFactorize(result, number / largestPrimal, false);
                    return;
                }
            }
            largestPrimal += 2;
        }
    }

    private void doFactorizeMap(TLongIntMap result, long number, boolean firstElement) {
        if (number <= 0) {
            return;
        }
        if (isPrimal(number)) {
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
        for (long factor : firstPrimals) {
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
            if (checkPrimal(largestPrimal)) {
                if (number % largestPrimal == 0) {
                    result.adjustOrPutValue(largestPrimal, 1, 1);
                    doFactorizeMap(result, number / largestPrimal, false);
                    return;
                }
            }
            largestPrimal += 2;
        }
    }

    private void doFactorizeSparseMap(SparseFactorsMap result, long number, boolean firstElement) {
        if (number <= 0) {
            return;
        }
        if (isPrimal(number)) {
            if (!firstElement) {
                result.adjustOrPutValue(number, 1, 1);
            }
            return;
        }

        final SparseFactorsMap cachedMap = primalFactorsSparseMapCache.get(number);
        if (cachedMap != null) {
            result.addMap(cachedMap);
            return;
        }

        long limit = (long) Math.sqrt(number);
        for (long factor : firstPrimals) {
            if (factor > limit) {
                return;
            }
            if (number % factor == 0) {
                result.adjustOrPutValue(factor, 1, 1);
                doFactorizeSparseMap(result, number / factor, false);
                return;
            }
        }

        long largestPrimal = 23;
        while (largestPrimal <= limit) {
            if (checkPrimal(largestPrimal)) {
                if (number % largestPrimal == 0) {
                    result.adjustOrPutValue(largestPrimal, 1, 1);
                    doFactorizeSparseMap(result, number / largestPrimal, false);
                    return;
                }
            }
            largestPrimal += 2;
        }
    }

    private boolean checkPrimal(long num) {
        if (foundPrimals.containsKey(num)) {
            return foundPrimals.get(num) == 1;
        }

        if (num % 2 == 0) {
            // Even numbers not primal
            return false;
        }

        // Long way
        long limit = (long) Math.sqrt(num);
        for (long i = 3; i <= limit; i += 2) {
            if (checkPrimal(i)) {
                if (num % i == 0) {
                    foundPrimals.put(num, (byte) 0);
                    return false;
                }
            }
        }

        foundPrimals.put(num, (byte) 1);
        return true;
    }

    private class PrimalTIterator extends PrimalGenerator implements TLongIterator {
        @Override
        public long next() {
            return nextPrimal();
        }
    }

    private class PrimalIterator extends PrimalGenerator implements Iterator<Long> {
        @Override
        public Long next() {
            return nextPrimal();
        }

    }

    private class PrimalGenerator {
        private long lastPrimal = 1;

        protected long nextPrimal() {
            do {
                if (lastPrimal > 2) {
                    lastPrimal += 2;
                } else {
                    lastPrimal++;
                }
            } while (!checkPrimal(lastPrimal));

            return lastPrimal;
        }

        public boolean hasNext() {
            return true;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
