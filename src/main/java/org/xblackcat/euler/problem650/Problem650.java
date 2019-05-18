package org.xblackcat.euler.problem650;

import gnu.trove.map.TLongIntMap;
import gnu.trove.map.hash.TLongIntHashMap;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.MathUtils;
import org.xblackcat.euler.util.PrimalCache;
import org.xblackcat.euler.util.SparseFactorsMap;

import java.math.BigInteger;

/**
 * Solving problem https://projecteuler.net/problem=650
 * <p>
 * 15.11.2017 16:39
 *
 * @author xBlackCat
 */
@InputData("20000")
@ResultDescription("S({1,number,#}) mod 1000000007 = {0,number,#}")
public class Problem650 {
    static long BASE = 1_000_000_007;

    @EntryPoint
    public long divisors(int n) {
        final PrimalCache primals = new PrimalCache();
        long sum = 1;

        long start = System.currentTimeMillis();
        long lastCheckPoint = start;
        for (int i = 2; i <= n; i++) {
            final SparseFactorsMap productFactors = factorizeProduct(i, primals);

            final LongModConsumer procedure = new LongModConsumer(this::getModVal, 1);
            productFactors.forEachEntry(procedure);
            final long factorsMod = procedure.getProduct();

            sum += factorsMod;
            if (sum >= BASE) {
                sum -= BASE;
            }
            if ((i & 0x3FF) == 0) {
                final long now = System.currentTimeMillis();
                System.out.println(i + " number reached after " + (now - start) + " ms (+" + (now - lastCheckPoint) + " ms) since start");
                lastCheckPoint = now;
            }
        }
        return sum;
    }

    public BigInteger divisorsLarge(int n) {
        final PrimalCache primals = new PrimalCache();
        BigInteger sum = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            final SparseFactorsMap productFactors = factorizeProduct(i, primals);

            final BigIntegerConsumer procedure = new BigIntegerConsumer(this::getBigVal, BigInteger.ONE);
            productFactors.forEachEntry(procedure);
            final BigInteger factorsSum = procedure.getProduct();

            sum = sum.add(factorsSum);
        }
        return sum;
    }

    SparseFactorsMap factorizeProduct(int n, PrimalCache primals) {
        final SparseFactorsMap productFactors = new SparseFactorsMap();
        final SparseFactorsMap currentFactors = new SparseFactorsMap();

        int limit = (n + 1) >> 1;
        int k = 1;
        while (k < limit) {
            final int numerator = n - k + 1;
            updateFactors(primals, currentFactors, k, numerator);

            productFactors.addMapTwice(currentFactors);
            k++;
        }

        if ((n & 1) == 0) {
            final int numerator = (n >> 1) + 1;
            updateFactors(primals, currentFactors, k, numerator);

            productFactors.addMap(currentFactors);
        }


//        System.out.println(n + " -> " + productFactors);

        return productFactors;
    }

    private void updateFactors(PrimalCache primals, SparseFactorsMap currentFactors, int k, int numerator) {
        final TLongIntMap factorsChange = new TLongIntHashMap(primals.factorizeMap(numerator));
        if (primals.isPrimal(numerator)) {
            factorsChange.adjustOrPutValue(numerator, 1, 1);
        }
        final TLongIntMap factorsRemove = primals.factorizeMap(k);
        factorsRemove.forEachEntry((a, b) -> {
            factorsChange.adjustOrPutValue(a, -b, -b);
            return true;
        });
        if (primals.isPrimal(k)) {
            factorsChange.adjustOrPutValue(k, -1, -1);
        }

        factorsChange.forEachEntry((l, i) -> {
            if (i != 0) {
                currentFactors.adjustOrPutValue(l, i, i);
            }
            return true;
        });
    }

    private BigInteger collectAllFactorsSumBig(TLongIntMap factorsMap) {
        BigInteger sum = BigInteger.ONE;

        for (long key : factorsMap.keys()) {
            int value = factorsMap.get(key);

            sum = sum.multiply(getBigVal(key, value));
        }

        return sum;
    }

    private BigInteger getBigVal(long key, int value) {
        return BigInteger.valueOf(key).pow(value + 1).subtract(BigInteger.ONE).divide(BigInteger.valueOf(key - 1));
    }

    private long collectAllFactorsSumMod(TLongIntMap factorsMap) {
        long sum = 1;

        for (long key : factorsMap.keys()) {
            int value = factorsMap.get(key);

            sum *= getModVal(key, value);
            if (sum >= BASE) {
                sum %= BASE;
            }
        }

        return sum;
    }

    private long getModVal(long key, int value) {
        long l = MathUtils.modPow(key, value + 1, BASE);
        if (l == 0) {
            l = BASE - 1;
        } else {
            l--;
        }
        long val = l * MathUtils.modPow(key - 1, BASE - 2, BASE);
        if (val >= BASE) {
            val %= BASE;
        }
        return val;
    }

}
