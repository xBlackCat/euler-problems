package org.xblackcat.euler.util;

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
}
