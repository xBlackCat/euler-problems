package org.xblackcat.euler.problem9;

import org.junit.Assert;
import org.junit.Test;

/**
 * 21.11.2017 9:03
 *
 * @author xBlackCat
 */
public class Problem9Test {

    @Test
    public void tripletWithSum() {
        Problem9 problem9 = new Problem9();
        Assert.assertEquals(60, problem9.tripletWithSum(12));
        Assert.assertEquals(780, problem9.tripletWithSum(30));
        Assert.assertEquals(2040, problem9.tripletWithSum(40));
    }

}