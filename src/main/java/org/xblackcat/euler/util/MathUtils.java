package org.xblackcat.euler.util;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;

import java.math.BigInteger;

/**
 *
 */
public class MathUtils {
    public static long modPow(long base, long exp, long modulus) {
        base %= modulus;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) != 0) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exp >>= 1;
        }
        return result;
    }

    public static long pow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) != 0) {
                result = result * base;
            }
            base *= base;
            exp >>= 1;
        }
        return result;
    }

    public static long[] generateBinomialCoefficients(int n) {
        long[] result = new long[n + 1];

        result[0] = result[n] = 1;
        final int limit = n >> 1;
        for (int i = 1; i <= limit; i++) {
            result[n - i] = result[i] = result[i - 1] * (n - i + 1) / i;
        }
        return result;
    }

    public static BigInteger[] generateBinomialCoefficientsBig(int n) {
        BigInteger[] result = new BigInteger[n + 1];

        result[0] = result[n] = BigInteger.ONE;
        final int limit = n >> 1;
        for (int i = 1; i <= limit; i++) {
            result[n - i] = result[i] = result[i - 1].multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Returns factors of product all the binomial coefficients for n
     *
     * @param n       set dimension
     * @param primals primals cache for factorization
     * @return factors as map: keys are factor and correspond values are exponent for the factor.
     */
    public static SparseFactorsMap factorizeProduct(int n, SparseMapFactorizer primals) {
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

        return productFactors;
    }

    private static void updateFactors(SparseMapFactorizer factorizer, SparseFactorsMap currentFactors, long k, long numerator) {
        final SparseFactorsMap factorsAdd = factorizer.factorize(numerator);
        final SparseFactorsMap factorsRemove = factorizer.factorize(k);

        currentFactors.addMap(factorsAdd);
        currentFactors.subtractMap(factorsRemove);

        if (factorizer.getPrimalCache().isPrimal(numerator)) {
            currentFactors.adjustOrPutValue(numerator, 1, 1);
        }
        if (factorizer.getPrimalCache().isPrimal(k)) {
            currentFactors.adjustOrPutValue(k, -1, -1);
        }
    }

    public static int gcd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    public static long gcd(long a, long b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    public static long allFactorsCount(TLongIntMap factorsMap) {
        long allFactorsAmount = 1;
        for (int amount : factorsMap.values()) {
            allFactorsAmount *= amount + 1;
        }
        return allFactorsAmount;
    }

    public static long allFactorsCount(TObjectIntMap<?> factorsMap) {
        long allFactorsAmount = 1;
        for (int amount : factorsMap.values()) {
            allFactorsAmount *= amount + 1;
        }
        return allFactorsAmount;
    }

    /**
     * Try to resolve square root from the factors map. Returns -1 if result is not an integer value.
     *
     * @param factorsMap factorized number
     * @return square root or -1 if result is not integer
     */
    public static long sqrtInt(TLongIntMap factorsMap) {
        final long[] v = {1};
        factorsMap.forEachEntry((f, e) -> {
            if ((e & 1) != 0) {
                v[0] = -1;
                return false;
            }
            e >>= 1;
            if (f != 2) {
                while (e-- > 0) {
                    v[0] *= f;
                }
            } else {
                v[0] <<= e;
            }
            return true;
        });
        return v[0];
    }

    public static long sqrtInt(SparseFactorsMap factorsMap) {
        return factorsMap.sqrtInt();
    }

    public static TLongSet allFactors(TLongIntMap factorsMap) {
        TLongSet result = new TLongHashSet();
        result.add(1);
        long[] keys = factorsMap.keys();
        if (keys.length == 1) {
            long primalFactor = keys[0];
            int amount = factorsMap.get(primalFactor);
            long f = 1;
            while (amount-- > 0) {
                f *= primalFactor;
                result.add(f);
            }
        } else {
            for (long primalFactor : keys) {
                int amount = factorsMap.remove(primalFactor);
                TLongSet factors = allFactors(factorsMap);
                factorsMap.put(primalFactor, amount);
                result.addAll(factors);
                long f = 1;
                while (amount-- > 0) {
                    f *= primalFactor;
                    TLongIterator it = factors.iterator();
                    while (it.hasNext()) {
                        result.add(f * it.next());
                    }
                }
            }
        }
        return result;
    }
}
