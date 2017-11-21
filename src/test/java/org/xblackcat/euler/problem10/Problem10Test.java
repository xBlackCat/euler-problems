package org.xblackcat.euler.problem10;

import org.junit.Assert;
import org.junit.Test;

/**
 * 21.11.2017 10:11
 *
 * @author xBlackCat
 */
public class Problem10Test {

    @Test
    public void primalSum() {
        Problem10 problem10 = new Problem10();
        Assert.assertEquals(17, problem10.primalSum(10));
    }
}