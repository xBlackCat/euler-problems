package org.xblackcat.euler.problem124;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class Problem124Test {
    @Test
    public void simpleTest() {
        Problem124 problem124 = new Problem124();

        Assert.assertEquals(8, problem124.firstMonthDaySundays(4, 10));
        Assert.assertEquals(9, problem124.firstMonthDaySundays(6, 10));
    }

}