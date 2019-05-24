package org.xblackcat.euler.util;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;

import java.util.*;

/**
 * 21.11.2017 9:15
 *
 * @author xBlackCat
 */
public class PythagoreanTriplets implements Iterable<Triplet> {
    private final SquareCache squareCache = new SquareCache();
    private final PrimalCache primalCache = new PrimalCache();

    private final TLongObjectMap<Set<Triplet>> tripletsByCathetusCache = new TLongObjectHashMap<>();

    @Override
    public Iterator<Triplet> iterator() {
        return new TripletIterator();
    }

    public Collection<Triplet> generateTripletsTill(long limitC) {
        Collection<Triplet> result = new HashSet<>();

        for (int m = 2; m < limitC; m++) {
            final long sM = squareCache.squareOf(m);
            if (sM > limitC) {
                break;
            }
            for (int n = 1; n < m; n++) {
                final long sN = squareCache.squareOf(n);
                if (sM + sN > limitC) {
                    break;
                }

                for (int k = 1; k < limitC; k++) {
                    long c = (sM + sN) * k;
                    if (c > limitC) {
                        break;
                    }

                    result.add(new Triplet((sM - sN) * k, (k * m * n) << 1, c));
                }
            }
        }

        return result;
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

    public Collection<Triplet> searchByCathetus(int targetB, int maxC) {
        Set<Triplet> results = new HashSet<>();

        final TLongSet factorsList = primalCache.allFactors(targetB);
        factorsList.forEach(factor -> {
            collectByCathetus(targetB / factor, factor, maxC / factor, results);

            return true;
        });

        return results;
    }

    private void collectByCathetus(long target, long factor, long maxC, Collection<Triplet> results) {
        if (target < 3) {
            return;
        }
        final Set<Triplet> cachedTriples = tripletsByCathetusCache.get(target);
        if (cachedTriples != null) {
            for (Triplet triplet : cachedTriples) {
                if (triplet.getC() <= maxC) {
                    results.add(triplet.multiply(factor));
                }
            }
            return;
        }

        Set<Triplet> triplets = new HashSet<>();
        int upperBoundM = (int) Math.ceil(Math.sqrt(maxC - 1));

        for (int m = 1; m <= upperBoundM; m++) {
            long squareM = squareCache.squareOf(m);
            for (int n = 1; n < m; n++) {
                long squareN = squareCache.squareOf(n);
                final long c = squareM + squareN;

                if (c > maxC || c < target) {
                    continue;
                }

                final Triplet t;
                if (((m * n) << 1) == target) {
                    t = new Triplet(squareM - squareN, target, c);
                } else if (squareM - squareN == target) {
                    t = new Triplet(target, 2 * m * n, c);
                } else {
                    continue;
                }

                results.add(t.multiply(factor));
                triplets.add(t);
            }
        }
        tripletsByCathetusCache.put(target, triplets);
    }

    public List<Triplet> searchByHypo(int hypo) {
        Set<Triplet> results = new HashSet<>();

        final TLongSet factorList = primalCache.allFactors(hypo);
        factorList.forEach(factor -> {
            collectByHypo(hypo / factor, factor, results);

            return true;
        });

        return new ArrayList<>(results);
    }

    private void collectByHypo(long hypo, long factor, Collection<Triplet> results) {
        int lowerBoundM = (int) Math.floor(Math.sqrt(hypo >> 1));
        int upperBoundM = (int) Math.ceil(Math.sqrt(hypo - 1));

        for (int m = lowerBoundM; m <= upperBoundM; m++) {
            long squareM = squareCache.squareOf(m);
            long squareN = hypo - squareM;

            int n = (int) Math.floor(Math.sqrt(squareN));
            if (squareCache.squareOf(n) != squareN) {
                continue;
            }

            results.add(new Triplet((squareM - squareN) * factor, 2 * m * n * factor, hypo * factor));
        }
    }

    public SquareCache getSquareCache() {
        return squareCache;
    }

    public PrimalCache getPrimalCache() {
        return primalCache;
    }
}
