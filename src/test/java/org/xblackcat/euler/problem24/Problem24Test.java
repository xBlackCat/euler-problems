package org.xblackcat.euler.problem24;

import org.junit.Assert;
import org.junit.Test;

/**
 * 29.11.2017 15:20
 *
 * @author xBlackCat
 */
public class Problem24Test {

    @Test
    public void nthPermutation() {
        Problem24 problem24 = new Problem24();

        Assert.assertEquals("0123456789", problem24.nthPermutation(1));
        Assert.assertEquals("0123456798", problem24.nthPermutation(2));
    }
}