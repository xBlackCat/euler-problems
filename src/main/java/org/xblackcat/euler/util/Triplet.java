package org.xblackcat.euler.util;

import java.util.Arrays;

/**
 * 21.11.2017 9:15
 *
 * @author xBlackCat
 */
public class Triplet {
    private final long[] triplet = new long[3];

    public Triplet(Triplet triplet, long factor) {
        this.triplet[0] = triplet.triplet[0] * factor;
        this.triplet[1] = triplet.triplet[1] * factor;
        this.triplet[2] = triplet.triplet[2] * factor;
    }

    public Triplet(long a, long b, long c) {
        triplet[0] = a;
        triplet[1] = b;
        triplet[2] = c;
        Arrays.sort(triplet);
    }

    public long getA() {
        return triplet[0];
    }

    public long getB() {
        return triplet[1];
    }

    public long getC() {
        return triplet[2];
    }

    public Triplet multiply(long m) {
        return new Triplet(this, m);
    }

    public long sum() {
        return getA() + getB() + getC();
    }

    public long product() {
        return getA() * getB() * getC();
    }

    @Override
    public String toString() {
        return "<a = " + getA() + ", b = " + getB() + ", c = " + getC() + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet triplet1 = (Triplet) o;
        return Arrays.equals(triplet, triplet1.triplet);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(triplet);
    }
}
