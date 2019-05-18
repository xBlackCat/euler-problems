package org.xblackcat.euler.problem650;

import gnu.trove.procedure.TLongIntProcedure;
import org.xblackcat.euler.util.MathUtils;

/**
 *
 */
public class LongModConsumer implements TLongIntProcedure {
    private static final long BASE = Problem650.BASE;
    private long numerator = 1;
    private long denominator = 1;

    @Override
    public boolean execute(long factor, int pow) {
        numerator = (numerator * (MathUtils.modPow(factor, pow + 1, BASE) - 1)) % BASE;

        denominator = (denominator * (factor - 1)) % BASE;
        return true;
    }

    public long getProduct() {
        return (numerator * MathUtils.modPow(denominator, BASE - 2, BASE)) % BASE;
    }
}
