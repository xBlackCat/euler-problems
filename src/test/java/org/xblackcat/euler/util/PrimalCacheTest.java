package org.xblackcat.euler.util;

import gnu.trove.set.hash.TLongHashSet;
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

    @Test
    public void allFactors() {
        TFactorizer cache = new TFactorizer();
        Assert.assertEquals(new TLongHashSet(new long[]{1, 2, 3, 4, 6}), MathUtils.allFactors(cache.factorize(12L)));
        Assert.assertEquals(new TLongHashSet(new long[]{1, 2, 4, 8, 16, 32, 64}), MathUtils.allFactors(cache.factorize(128L)));
        Assert.assertEquals(
                new TLongHashSet(new long[]{1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 15, 18, 19, 20, 22, 30, 33, 36, 38, 44, 45, 47, 55, 57, 60, 66, 76, 90, 94, 95, 99, 110, 114, 132, 141, 165, 171, 180, 188, 190, 198, 209, 220, 228, 235, 282, 285, 330, 342, 380, 396, 418, 423, 470, 495, 517, 564, 570, 627, 660, 684, 705, 836, 846, 855, 893, 940, 990, 1034, 1045, 1140, 1254, 1410, 1551, 1692, 1710, 1786, 1881, 1980, 2068, 2090, 2115, 2508, 2585, 2679, 2820, 3102, 3135, 3420, 3572, 3762, 4180, 4230, 4465, 4653, 5170, 5358, 6204, 6270, 7524, 7755, 8037, 8460, 8930, 9306, 9405, 9823, 10340, 10716, 12540, 13395, 15510, 16074, 17860, 18612, 18810, 19646, 23265, 26790, 29469, 31020, 32148, 37620, 39292, 40185, 46530, 49115, 53580, 58938, 80370, 88407, 93060, 98230, 117876, 147345, 160740, 176814, 196460, 294690, 353628, 442035, 589380, 884070}),
                MathUtils.allFactors(cache.factorize(1768140L))
        );
    }
}