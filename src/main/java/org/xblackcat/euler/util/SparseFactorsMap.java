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
    private Boolean isEmpty;

    public SparseFactorsMap() {
        this(10);
    }

    public SparseFactorsMap(int initialSize) {
        this(new int[initialSize]);
        isEmpty = true;
    }

    private SparseFactorsMap(int[] exp) {
        this.exp = exp;
        if (exp.length == 0) {
            isEmpty = true;
        }
    }

    public static SparseFactorsMap gcd(SparseFactorsMap... maps) {
        if (maps == null || maps.length == 0) {
            return new SparseFactorsMap();
        } else if (maps.length == 1) {
            return maps[0];
        }

        int minSize = Integer.MAX_VALUE;
        for (SparseFactorsMap m : maps) {
            if (minSize > m.exp.length) {
                minSize = m.exp.length;
            }
        }

        int[] result = new int[minSize];
        int maxRealIndex = -1;

        for (int i = 0; i < result.length; i++) {
            int minExp = Integer.MAX_VALUE;
            for (SparseFactorsMap m : maps) {
                final int exp = m.exp[i];
                if (minExp > exp) {
                    minExp = exp;
                }
            }
            if (minExp != 0) {
                maxRealIndex = i;
                result[i] = minExp;
            }
        }

        if (maxRealIndex == -1) {
            return new SparseFactorsMap(new int[0]);
        }

        return new SparseFactorsMap(Arrays.copyOf(result, maxRealIndex + 1));
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
        probeState(idx);
        return oldVal;
    }

    public int put(long factor, int pow) {
        int idx = toIdx(factor);
        if (idx >= exp.length) {
            growTill(idx);
        }

        final int oldVal = exp[idx];
        exp[idx] = pow;
        probeState(idx);
        return oldVal;
    }

    public int remove(long factor) {
        int idx = toIdx(factor);
        if (idx >= exp.length) {
            return 0;
        }

        final int oldVal = exp[idx];
        exp[idx] = 0;
        probeState(idx);
        return oldVal;
    }

    public void clear() {
        Arrays.fill(exp, 0);
        isEmpty = true;
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

    /**
     * @param idx true - value was set. False - value was cleared
     */
    private void probeState(int idx) {
        final int value = exp[idx];
        if (isEmpty == Boolean.FALSE) {
            if (value == 0) {
                isEmpty = null;
            }
        } else if (value != 0) {
            isEmpty = false;
        }
    }

    public boolean isEmpty() {
        if (isEmpty == null) {
            if (exp.length == 0) {
                isEmpty = true;
                return true;
            }

            for (int i : exp) {
                if (i != 0) {
                    isEmpty = false;
                    return false;
                }
            }

            isEmpty = true;
            return true;
        } else {
            return isEmpty;
        }
    }

    @Override
    public SparseFactorsMap clone() {
        return new SparseFactorsMap(exp.clone());
    }

    public void addMap(SparseFactorsMap map) {
        final int length = map.exp.length;
        if (length > exp.length) {
            exp = Arrays.copyOf(exp, length);
        }

        for (int i = 0; i < length; i++) {
            exp[i] += map.exp[i];
        }
    }

    public void subtractMap(SparseFactorsMap map) {
        final int length = map.exp.length;
        if (length > exp.length) {
            exp = Arrays.copyOf(exp, length);
        }

        for (int i = 0; i < length; i++) {
            exp[i] -= map.exp[i];
        }
    }

    public void addMapTwice(SparseFactorsMap map) {
        final int length = map.exp.length;
        if (length > exp.length) {
            exp = Arrays.copyOf(exp, length);
        }

        for (int i = 0; i < length; i++) {
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

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        forEachEntry(new TLongIntProcedure() {
            private boolean first = true;

            public boolean execute(long key, int value) {
                if (first) first = false;
                else buf.append(", ");

                buf.append(key);
                buf.append("=");
                buf.append(value);
                return true;
            }
        });
        buf.append("}");
        return buf.toString();
    }

    public void removeAll(SparseFactorsMap gcd) {
        int limit = Math.min(gcd.exp.length, exp.length);
        isEmpty = true;
        for (int i = 0; i < limit; i++) {
            final int thatExp = gcd.exp[i];
            if (thatExp != 0) {
                if (thatExp > exp[i]) {
                    exp[i] = 0;
                } else {
                    exp[i] -= thatExp;
                }
            } else if (isEmpty && exp[i] != 0) {
                isEmpty = false;
            }
        }

        if (isEmpty) {
            for (int i = limit; i < exp.length; i++) {
                if (exp[i] != 0) {
                    isEmpty = false;
                    break;
                }
            }
        }
    }

    public void merge(SparseFactorsMap gcd) {
        int limit = Math.min(gcd.exp.length, exp.length);
        isEmpty = true;
        for (int i = 0; i < limit; i++) {
            final int thatExp = gcd.exp[i];
            int value = Math.max(exp[i], thatExp);
            if (isEmpty && value != 0) {
                isEmpty = false;
            }
        }

        if (isEmpty) {
            for (int i = limit; i < exp.length; i++) {
                if (exp[i] != 0) {
                    isEmpty = false;
                    break;
                }
            }
        }
    }

    public long value() {
        long v = 1;
        if (exp[0] != 0) {
            v <<= exp[0];
        }
        for (int i = 1; i < exp.length; i++) {
            if (exp[i] > 0) {
                int e = exp[i];
                int f = (i << 1) + 1;
                while (e-- > 0) {
                    v *= f;
                }
            }
        }
        return v;
    }

    /**
     * Returns integer square root of the value or -1 if it is not possible to get integer square root
     *
     * @return integer square root or -1
     */
    public long sqrtInt() {
        long v = 1;
        if (exp[0] != 0) {
            if ((exp[0] & 1) != 0) {
                return -1;
            }
            v <<= (exp[0] >> 1);
        }
        for (int i = 1; i < exp.length; i++) {
            if (exp[i] > 0) {
                int e = exp[i];
                if ((e & 1) != 0) {
                    return -1;
                }
                e >>= 1;
                int f = (i << 1) + 1;
                while (e-- > 0) {
                    v *= f;
                }
            }
        }
        return v;
    }

    public double sqrtScalar() {
        final long l = sqrtInt();
        if (l != -1) {
            return l;
        } else {
            return Math.sqrt(value());
        }
    }
}
