package org.xblackcat.euler.util;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.list.TLongList;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.map.TLongByteMap;
import gnu.trove.map.hash.TLongByteHashMap;
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
        TLongList result = new TLongArrayList();
        doFactorize(result, number);
        result.sort();
        return result;
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

    private void doFactorize(TLongList result, long number) {
        if (number <= 0) {
            return;
        }
        if (isPrimal(number)) {
            result.add(number);
            return;
        }

        long limit = (long) Math.sqrt(number);
        for (long factor : firstPrimals) {
            if (factor > limit) {
                return;
            }
            if (number % factor == 0) {
                result.add(factor);
                doFactorize(result, number / factor);
                return;
            }
        }

        long largestPrimal = 23;
        while (largestPrimal < limit) {
            if (checkPrimal(largestPrimal)) {
                if (number % largestPrimal == 0) {
                    result.add(largestPrimal);
                    doFactorize(result, number / largestPrimal);
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
