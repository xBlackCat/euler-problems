package org.xblackcat.euler.util;

import java.util.Objects;

/**
 * 21.11.2017 9:15
 *
 * @author xBlackCat
 */
public class Triplet {
    private final long a;
    private final long b;
    private final long c;

    public Triplet(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public long getA() {
        return a;
    }

    public long getB() {
        return b;
    }

    public long getC() {
        return c;
    }

    public Triplet multiply(long m) {
        return new Triplet(
                a * m,
                b * m,
                c * m
        );
    }

    public long sum() {
        return a + b + c;
    }

    public long product() {
        return a * b * c;
    }

    @Override
    public String toString() {
        return "a = " + a + ", b = " + b + ", c = " + c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Triplet triplet = (Triplet) o;
        return a == triplet.a &&
                b == triplet.b &&
                c == triplet.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
