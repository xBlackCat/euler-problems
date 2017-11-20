package org.xblackcat.euler.problem7;

import org.junit.Assert;
import org.junit.Test;

/**
 * 20.11.2017 17:13
 *
 * @author xBlackCat
 */
public class Problem7Test {

    @Test
    public void nthPrime() {
        Problem7 problem7 = new Problem7();
        Assert.assertEquals(13, problem7.nthPrime(6));
        Assert.assertEquals(17, problem7.nthPrime(7));
        Assert.assertEquals(19, problem7.nthPrime(8));
    }

}