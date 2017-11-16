package org.xblackcat.euler.problem2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 16.11.2017 16:31
 *
 * @author xBlackCat
 */
public class Problem2Test {

    @Test
    public void sumOfFNumbers() {
        final Problem2 p = new Problem2();
        Assert.assertEquals(10, p.sumOfFNumbers(10));
        Assert.assertEquals(44, p.sumOfFNumbers(100));
    }
}