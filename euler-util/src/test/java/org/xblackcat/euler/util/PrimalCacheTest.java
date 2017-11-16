package org.xblackcat.euler.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 15.11.2017 17:09
 *
 * @author xBlackCat
 */
public class PrimalCacheTest {
    @Test
    public void testPrimals() {
        final PrimalCache cache = new PrimalCache();
        Assert.assertTrue(cache.isPrimal(2));
        Assert.assertTrue(cache.isPrimal(3));
        Assert.assertTrue(cache.isPrimal(5));
        Assert.assertTrue(cache.isPrimal(7));
        Assert.assertTrue(cache.isPrimal(11));
        Assert.assertTrue(cache.isPrimal(13));
        Assert.assertTrue(cache.isPrimal(17));
        Assert.assertTrue(cache.isPrimal(19));

        Assert.assertFalse(cache.isPrimal(4));
        Assert.assertFalse(cache.isPrimal(6));
        Assert.assertFalse(cache.isPrimal(8));
        Assert.assertFalse(cache.isPrimal(9));
        Assert.assertFalse(cache.isPrimal(10));
        Assert.assertFalse(cache.isPrimal(12));
        Assert.assertFalse(cache.isPrimal(14));
        Assert.assertFalse(cache.isPrimal(15));
        Assert.assertFalse(cache.isPrimal(16));
        Assert.assertFalse(cache.isPrimal(18));
        Assert.assertFalse(cache.isPrimal(20));

        Assert.assertTrue(cache.isPrimal(499));
        Assert.assertTrue(cache.isPrimal(463));
        Assert.assertTrue(cache.isPrimal(467));
        Assert.assertTrue(cache.isPrimal(479));
        Assert.assertTrue(cache.isPrimal(487));
        Assert.assertTrue(cache.isPrimal(491));
        Assert.assertTrue(cache.isPrimal(503));
        Assert.assertTrue(cache.isPrimal(509));

        Assert.assertFalse(cache.isPrimal(841));
        Assert.assertFalse(cache.isPrimal(444));
        Assert.assertFalse(cache.isPrimal(333));
        Assert.assertFalse(cache.isPrimal(555));
        Assert.assertFalse(cache.isPrimal(666));
        Assert.assertFalse(cache.isPrimal(777));
        Assert.assertFalse(cache.isPrimal(888));
    }

}