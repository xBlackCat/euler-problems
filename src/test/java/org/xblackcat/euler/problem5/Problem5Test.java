package org.xblackcat.euler.problem5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 20.11.2017 16:57
 *
 * @author xBlackCat
 */
public class Problem5Test {

    @Test
    public void smallestNumber() {
        Problem5 problem5 = new Problem5();
        Assert.assertEquals(2520, problem5.smallestNumber(10));
    }
}