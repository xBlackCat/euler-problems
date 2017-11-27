package org.xblackcat.euler.problem16;

import org.junit.Assert;
import org.junit.Test;

/**
 * 27.11.2017 16:25
 *
 * @author xBlackCat
 */
public class Problem16Test {
    @Test
    public void sumOfDigits() {
        Problem16 problem16 = new Problem16();
        Assert.assertEquals(26, problem16.sumOfDigits(15));
    }
}