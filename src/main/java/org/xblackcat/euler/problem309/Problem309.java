package org.xblackcat.euler.problem309;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.*;

import java.util.*;

/**
 * https://projecteuler.net/problem=309
 * <p>
 * 29.11.2017 17:45
 *
 * @author xBlackCat
 */
@InputData("1000000")
@ResultDescription("{0,number,#} triplets produce integer solutions for w, for integer values x, y, h and 0 < x < y < {1,number}")
public class Problem309 {
    private final PythagoreanTriplets tripletHelper = new PythagoreanTriplets();
    private final PrimalCache primalCache = tripletHelper.getPrimalCache();
    private final SquareCache squareCache = tripletHelper.getSquareCache();

    @EntryPoint
    public int ladders(int n) {
        int foundTriplets = 0;

        long start = System.currentTimeMillis();

        final Collection<Triplet> collection = tripletHelper.generateTripletsTill(n - 1);

        long lastCheckPoint = System.currentTimeMillis();
        System.out.println(collection.size() + " pythagorean triples are generated in " + (lastCheckPoint - start) + " ms");

        Map<Long, Set<Triplet>> tripletsByCathetus = new HashMap<>();

        for (Triplet t : collection) {
            tripletsByCathetus.computeIfAbsent(t.getA(), k -> new HashSet<>()).add(t);
            tripletsByCathetus.computeIfAbsent(t.getB(), k -> new HashSet<>()).add(t);
        }


        for (long w = 4; w < n; w++) {
            final Collection<Triplet> triplets = tripletsByCathetus.getOrDefault(w, Collections.emptySet());

            foundTriplets += checkTriplets(w, triplets);
        }


        return foundTriplets;
    }

    private int checkTriplets(long w, Collection<Triplet> triplets) {
        if (triplets.size() < 2) {
            return 0;
        }

        int foundTriplets = 0;

        final Triplet[] toCheck = triplets.stream().sorted(Comparator.comparingLong(Triplet::getC).reversed()).toArray(Triplet[]::new);

        for (int i = 0; i < toCheck.length; i++) {
            final Triplet leftTriangle = toCheck[i];
            long b;
            if (leftTriangle.getA() == w) {
                b = leftTriangle.getB();
            } else {
                b = leftTriangle.getA();
            }
            for (int j = i + 1; j < toCheck.length; j++) {
                final Triplet rightTriangle = toCheck[j];
                long a;
                if (rightTriangle.getA() == w) {
                    a = rightTriangle.getB();
                } else {
                    a = rightTriangle.getA();
                }
                if ((a * b) % (a + b) == 0) {
                    foundTriplets++;
                }
            }

        }
        return foundTriplets;
    }

    public int bruteForce(long limit) {
        int limitW = (int) Math.ceil(limit / Math.sqrt(2));

        int found = 0;
        for (int w = 50; w < limitW; w++) {
            final long sW = squareCache.squareOf(w);
            for (int y = (int) (limit - 1); y > w; y--) {
                final long sY = squareCache.squareOf(y);
                for (int x = w + 1; x < y; x++) {
                    final long sX = squareCache.squareOf(x);

                    long sB = sY - sW;
                    long sA = sX - sW;

                    final SparseFactorsMap fA = primalCache.factorizeSparseMap(sA);
                    final SparseFactorsMap fB = primalCache.factorizeSparseMap(sB);

                    long a = fA.sqrtInt();
                    long b = fB.sqrtInt();
                    if (a != -1 && b != -1) {
                        if ((a * b) % (a + b) == 0) {
                            System.out.println(w + ": <a = " + ((a * b) / (a + b)) + ", b = " + x + ", c = " + y + ">");
                            found++;
                        }
                    }
                }
            }
        }
        return found;
    }
}
