package org.xblackcat.euler.problem4;

import org.junit.Assert;
import org.junit.Test;

/**
 * 16.11.2017 16:31
 *
 * @author xBlackCat
 */
public class Problem4Test {

    @Test
    public void sumOfFNumbers() {
        final Problem4 p = new Problem4();
        Assert.assertEquals(9009, p.largestPalindromeWithFactorOfDigits(2));
    }
}