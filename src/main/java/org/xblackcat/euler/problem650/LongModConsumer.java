package org.xblackcat.euler.problem650;

import gnu.trove.procedure.TLongIntProcedure;

/**
 *
 */
public class LongModConsumer implements TLongIntProcedure {
    private long product;
    private final Procedure valueBuilder;

    public LongModConsumer(Procedure valueBuilder) {
        this(valueBuilder, 0);
    }

    public LongModConsumer(Procedure valueBuilder, long initialValue) {
        this.valueBuilder = valueBuilder;
        product = initialValue;
    }

    @Override
    public boolean execute(long a, int b) {
        product = (product * valueBuilder.toValue(a, b)) % Problem650.BASE;
        return true;
    }

    public long getProduct() {
        return product;
    }

    public interface Procedure {
        long toValue(long factor, int pow);
    }
}
