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
}