package org.xblackcat.euler.problem1;

import org.junit.Assert;
import org.junit.Test;

/**
 * 16.11.2017 16:10
 *
 * @author xBlackCat
 */
public class Problem1Test {
    @Test
    public void sumOfNumbers() {
        final Problem1 problem1 = new Problem1();
        Assert.assertEquals(23, problem1.sumOfNumbers(10));
        Assert.assertEquals(14, problem1.sumOfNumbers(9));
    }
}