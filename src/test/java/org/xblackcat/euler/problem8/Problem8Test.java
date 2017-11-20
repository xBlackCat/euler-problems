package org.xblackcat.euler.problem8;

import org.junit.Assert;
import org.junit.Test;

/**
 * 20.11.2017 17:36
 *
 * @author xBlackCat
 */
public class Problem8Test {

    @Test
    public void largestProduct() {
        Problem8 problem8 = new Problem8();
        Assert.assertEquals(5832, problem8.largestProduct(4));
    }
}