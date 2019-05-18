package org.xblackcat.euler.problem650;

import org.junit.Test;

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
        assertEquals(5736, new Problem650().divisors(5));
    }

    @Test
    public void largeTest() {
        assertEquals(BigInteger.valueOf(141740594713218418L), new Problem650().divisorsLarge(10));
        assertEquals(721034267, new Problem650().divisors(10));
        assertEquals(721034267, new Problem650().divisors(10));
    }

    @Test
    public void veryLargeTest() {
        assertEquals(332792866, new Problem650().divisors(100));
        assertEquals(332792866, new Problem650().divisors(100));
    }
}