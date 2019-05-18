package org.xblackcat.euler.problem650;

import gnu.trove.procedure.TLongIntProcedure;

import java.math.BigInteger;

/**
 *
 */
public class BigIntegerConsumer implements TLongIntProcedure {
    private BigInteger product = BigInteger.ONE;

    @Override
    public boolean execute(long a, int b) {
        product = product.multiply(toValue(a, b));
        return true;
    }

    private BigInteger toValue(long key, int value) {
        return BigInteger.valueOf(key).pow(value + 1).subtract(BigInteger.ONE).divide(BigInteger.valueOf(key - 1));
    }

    public BigInteger getProduct() {
        return product;
    }
}
