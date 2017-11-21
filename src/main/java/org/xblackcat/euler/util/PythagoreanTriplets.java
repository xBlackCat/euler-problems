package org.xblackcat.euler.util;

import java.util.Iterator;

/**
 * 21.11.2017 9:15
 *
 * @author xBlackCat
 */
public class PythagoreanTriplets implements Iterable<Triplet> {
    private final SquareCache squareCache = new SquareCache();
    private final PrimalCache primalCache = new PrimalCache();

    @Override
    public Iterator<Triplet> iterator() {
        return new TripletIterator();
    }

    private class TripletIterator implements Iterator<Triplet> {
        private int c = 3;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Triplet next() {
            return searchNext();
        }

        private Triplet searchNext() {
            while (true) {
                c += 2;
                for (int b = c - 1; b > 1; b--) {
                    for (int a = b - 1; a > 0; a--) {
                        long sqA = squareCache.squareOf(a);
                        long sqB = squareCache.squareOf(b);
                        long sqC = squareCache.squareOf(c);
                        if (sqA + sqB == sqC) {
                            if (primalCache.isPrimal(a) || primalCache.isPrimal(b) || primalCache.isPrimal(c)) {
                                return new Triplet(a, b, c);
                            }
                        }
                    }
                }
            }
        }
    }
}
