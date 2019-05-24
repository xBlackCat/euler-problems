package org.xblackcat.euler.util;

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
    public static SparseFactorsMap factorizeProduct(int n, PrimalCache primals) {
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

    private static void updateFactors(PrimalCache primals, SparseFactorsMap currentFactors, int k, int numerator) {
        final SparseFactorsMap factorsAdd = primals.factorizeSparseMap(numerator);
        final SparseFactorsMap factorsRemove = primals.factorizeSparseMap(k);

        currentFactors.addMap(factorsAdd);
        currentFactors.subtractMap(factorsRemove);

        if (primals.isPrimal(numerator)) {
            currentFactors.adjustOrPutValue(numerator, 1, 1);
        }
        if (primals.isPrimal(k)) {
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
}
