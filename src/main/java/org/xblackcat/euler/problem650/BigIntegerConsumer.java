package org.xblackcat.euler.problem650;

import gnu.trove.procedure.TLongIntProcedure;

import java.math.BigInteger;

/**
 *
 */
public class BigIntegerConsumer implements TLongIntProcedure {
    private BigInteger product;
    private final Procedure valueBuilder;

    public BigIntegerConsumer(Procedure valueBuilder) {
        this(valueBuilder, BigInteger.ZERO);
    }

    public BigIntegerConsumer(Procedure valueBuilder, BigInteger initialValue) {
        this.valueBuilder = valueBuilder;
        product = initialValue;
    }

    @Override
    public boolean execute(long a, int b) {
        product = product.multiply(valueBuilder.toValue(a, b));
        return true;
    }

    public BigInteger getProduct() {
        return product;
    }

    public interface Procedure {
        BigInteger toValue(long factor, int pow);
    }
}
