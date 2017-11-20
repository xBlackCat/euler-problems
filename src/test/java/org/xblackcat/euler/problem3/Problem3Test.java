package org.xblackcat.euler.problem3;

import org.junit.Assert;
import org.junit.Test;

/**
 * 16.11.2017 16:31
 *
 * @author xBlackCat
 */
public class Problem3Test {

    @Test
    public void sumOfFNumbers() {
        final Problem3 p = new Problem3();
        Assert.assertEquals(29, p.largestPrimeFactor(13195));
        Assert.assertEquals(5, p.largestPrimeFactor(100));
    }
}