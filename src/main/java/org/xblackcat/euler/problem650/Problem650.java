package org.xblackcat.euler.problem650;

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
            final SparseFactorsMap productFactors = MathUtils.factorizeProduct(i, primals);

            final LongModConsumer procedure = new LongModConsumer();
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
            final SparseFactorsMap productFactors = MathUtils.factorizeProduct(i, primals);

            final BigIntegerConsumer procedure = new BigIntegerConsumer();
            productFactors.forEachEntry(procedure);
            final BigInteger factorsSum = procedure.getProduct();

            sum = sum.add(factorsSum);
        }
        return sum;
    }
}
