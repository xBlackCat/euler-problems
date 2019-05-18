package org.xblackcat.euler.problem650;

import gnu.trove.map.TLongIntMap;
import org.junit.Test;
import org.xblackcat.euler.util.PrimalCache;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class Problem650Test {
    @Test
    public void inputTest() {
        assertEquals(BigInteger.valueOf(5736), new Problem650().divisorsLarge(5));
        assertEquals(5736, new Problem650().divisors(5));
//        assertEquals(332792866, new Problem650().divisors(100));
    }

    @Test
    public void largeTest() {
        assertEquals(BigInteger.valueOf(141740594713218418L), new Problem650().divisorsLarge(10));
        assertEquals(721034267, new Problem650().divisors(10));
    }

    @Test
    public void veryLargeTest() {
        assertEquals(332792866, new Problem650().divisors(100));
    }

/*
    @Test
    public void performanceFactorization() {
        final PrimalCache primalCache = new PrimalCache();
        final Problem650 problem650 = new Problem650();
        long start = System.currentTimeMillis();

        final TLongIntMap map = problem650.factorizeProduct(20000, primalCache);

        System.out.println("Factorized in " + (System.currentTimeMillis() - start) + " ms");
    }
*/
}