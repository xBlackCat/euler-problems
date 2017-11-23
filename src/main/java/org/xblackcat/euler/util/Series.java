package org.xblackcat.euler.util;

import java.util.Arrays;

/**
 * 21.11.2017 10:15
 *
 * @author xBlackCat
 */
public class Series implements Comparable<Series> {
    private final int[] factors;

    public Series(String part) {
        int length = part.length();
        factors = new int[length];
        for (int i = 0; i < length; i++) {
            factors[i] = (byte) (part.charAt(i) - '0');
        }
        Arrays.sort(factors);
    }

    public Series(int... factors) {
        this.factors = factors;
    }

    public long multiply() {
        long m = 1;
        for (int f : factors) {
            m *= f;
        }
        return m;
    }

    @Override
    public int compareTo(Series o) {
        for (int i = 0; i < factors.length; i++) {
            int c = factors[i] - o.factors[i];
            if (c != 0) {
                return c;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Series series = (Series) o;
        return Arrays.equals(factors, series.factors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(factors);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int f : factors) {
            str.append(f);
            str.append('*');
        }
        return str.substring(0, str.length());
    }
}
