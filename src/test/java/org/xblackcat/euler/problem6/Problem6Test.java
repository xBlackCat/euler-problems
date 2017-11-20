package org.xblackcat.euler.problem6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 20.11.2017 17:07
 *
 * @author xBlackCat
 */
public class Problem6Test {

    @Test
    public void sumDifferences() {
        Problem6 problem6 = new Problem6();
        Assert.assertEquals(2640, problem6.sumDifferences(10));
    }
}