package org.xblackcat.euler.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 16.11.2017 16:02
 *
 * @author xBlackCat
 */
public class NumberUtilsTest {
    @Test
    public void sumNumber() {
        Assert.assertEquals(55, NumberUtils.sumNumber(10));
        Assert.assertEquals(45, NumberUtils.sumNumber(9));
    }

    @Test
    public void sumSquares() {
        Assert.assertEquals(1, NumberUtils.sumSquares(1));
        Assert.assertEquals(5, NumberUtils.sumSquares(2));
        Assert.assertEquals(14, NumberUtils.sumSquares(3));
        Assert.assertEquals(385, NumberUtils.sumSquares(10));
    }
}