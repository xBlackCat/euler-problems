package org.xblackcat.euler.problem309;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.PrimalCache;
import org.xblackcat.euler.util.PythagoreanTriplets;
import org.xblackcat.euler.util.Triplet;

import java.util.ArrayList;
import java.util.Collection;

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

    @EntryPoint
    public int ladders(int n) {
//        Set<Triplet> found = new HashSet<>();
        int foundTriplets = 0;

        long start = System.currentTimeMillis();
        long lastCheckPoint = start;

        for (int w = 4; w < n; w++) {
            if ((w & 0x7FF) == 0) {
                final long now = System.currentTimeMillis();
                System.out.println(w + " width reached after " + (now - start) + " ms (+" + (now - lastCheckPoint) + " ms) since start. " +
                                           "Found triplets so far: " + foundTriplets/* + " / " + found.size()*/);
                lastCheckPoint = now;
            }

            final Collection<Triplet> triplets = this.tripletHelper.searchBCathetus(w, n);

            //            System.out.println("Triplets: " + triplets);

            if (triplets.size() < 2) {
                continue;
            }

            Collection<Triplet> leftTriangles = new ArrayList<>();
            Collection<Triplet> rightTriangles = new ArrayList<>();

            for (Triplet t : triplets) {
                if (t.getC() >= n) {
                    continue;
                }
                if (t.getA() == w) {
                    leftTriangles.add(t);
                } else {
                    rightTriangles.add(t);
                }
            }

            for (Triplet rightTriangle : rightTriangles) {
                long b = rightTriangle.getA();
                for (Triplet leftTriangle : leftTriangles) {
                    long a = leftTriangle.getB();

                    if ((a * b) % (a + b) == 0) {
                        

                        foundTriplets++;
                    }
                }

            }
        }

        return foundTriplets;
    }
}
