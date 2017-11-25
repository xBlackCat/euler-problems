package org.xblackcat.euler.problem14;

import org.junit.Assert;
import org.junit.Test;

/**
 * 25.11.2017 12:41
 *
 * @author xBlackCat
 */
public class Problem14Test {

    @Test
    public void findNuberWithLongestChain() {
        final Problem14 problem14 = new Problem14();
        Assert.assertEquals(10, problem14.chainLength(13));
        Assert.assertEquals(20, problem14.chainLength(9));
    }
}