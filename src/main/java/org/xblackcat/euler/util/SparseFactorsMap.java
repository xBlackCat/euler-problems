package org.xblackcat.euler.util;

import gnu.trove.map.TLongIntMap;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.procedure.TLongIntProcedure;

import java.util.Arrays;

/**
 * Sparse map for storing only primal factors with theirs exponents
 */
public class SparseFactorsMap {
    private int[] exp;

    public SparseFactorsMap(int initialSize) {
        exp = new int[initialSize];
    }

    public SparseFactorsMap() {
        this(300);
    }

    public int get(long factor) {
        int idx = toIdx(factor);
        if (idx >= exp.length) {
            return 0;
        }

        return exp[idx];
    }

    public int adjustOrPutValue(long factor, int pow, int powAdjust) {
        int idx = toIdx(factor);
        if (idx >= exp.length) {
            growTill(idx);
        }

        final int oldVal = exp[idx];
        if (oldVal != 0) {
            exp[idx] += powAdjust;
        } else {
            exp[idx] = pow;
        }
        return oldVal;
    }

    public int put(long factor, int pow) {
        int idx = toIdx(factor);
        if (idx >= exp.length) {
            growTill(idx);
        }

        final int oldVal = exp[idx];
        exp[idx] = pow;
        return oldVal;
    }

    public int remove(long factor) {
        int idx = toIdx(factor);
        if (idx >= exp.length) {
            return 0;
        }

        final int oldVal = exp[idx];
        exp[idx] = 0;
        return oldVal;
    }

    public void clear() {
        Arrays.fill(exp, 0);
    }

    public TLongIntMap toTMap() {
        final TLongIntHashMap map = new TLongIntHashMap();
        if (exp[0] != 0) {
            map.put(2, exp[0]);
        }
        for (int i = 1; i < exp.length; i++) {
            final int pow = exp[i];
            if (pow != 0) {
                map.put((((long) i) << 1) + 1, pow);
            }
        }
        return map;
    }

    public void addMap(SparseFactorsMap map) {
        if (map.exp.length > exp.length) {
            exp = Arrays.copyOf(exp, map.exp.length);
        }

        for (int i = 0; i < exp.length; i++) {
            exp[i] += map.exp[i];
        }
    }

    public void addMapTwice(SparseFactorsMap map) {
        if (map.exp.length > exp.length) {
            exp = Arrays.copyOf(exp, map.exp.length);
        }

        for (int i = 0; i < exp.length; i++) {
            exp[i] += map.exp[i] << 1;
        }
    }

    public boolean forEachEntry(TLongIntProcedure procedure) {
        if (exp[0] != 0 && !procedure.execute(2, exp[0])) {
            return false;
        }
        for (int i = 1; i < exp.length; i++) {
            if (exp[i] != 0 && !procedure.execute((((long) i) << 1) + 1, exp[i])) {
                return false;
            }
        }
        return true;
    }

    private void growTill(int idx) {
        int newSize = Math.min((int) (idx * 1.3), Integer.MAX_VALUE);
        exp = Arrays.copyOf(exp, newSize);
    }

    private static int toIdx(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Only positive numbers are allowed");
        }
        if (number == 2) {
            return 0;
        }
        return (int) ((number >> 1) & 0x7FFF_FFFF);
    }
}
