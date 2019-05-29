package org.xblackcat.euler.problem500;

import org.junit.Assert;
import org.junit.Test;
import org.xblackcat.euler.util.BigFactorizer;
import org.xblackcat.euler.util.MathUtils;

import java.math.BigInteger;

/**
 *
 */
public class Problem500Test {
    @Test
    public void simpleTest() {
        final Problem500 problem500 = new Problem500();
        Assert.assertEquals(120, problem500.smallestNumber(4));
        Assert.assertEquals(7560, problem500.smallestNumber(6));
        Assert.assertEquals(17297280L, problem500.smallestNumber(9));
        Assert.assertEquals(125970234L, problem500.smallestNumber(16));
    }

//    @Test
    public void research() {
        final BigFactorizer factorizer = new BigFactorizer();

        final var twoMod16 = BigInteger.valueOf(17297280L * 17 * 19 * 23 * 25 * 29 * 31 * 37);
        System.out.println(twoMod16.mod(BigInteger.valueOf(500500507)));
        final var number = twoMod16.multiply(BigInteger.valueOf(41 * 43 * 47 * 49 * 53))
                .multiply(BigInteger.valueOf(59 * 61 * 67 * 71 * 73))
                .multiply(BigInteger.valueOf(79 * 83 * 89 * 97 ))
                .multiply(BigInteger.valueOf(101 * 103 * 107 ));
        final var factors = factorizer.factorize(number);
        final long factorsCount = MathUtils.allFactorsCount(factors);

        System.out.println(number + ": " + factors + " (" + factorsCount + ")");
    }

}