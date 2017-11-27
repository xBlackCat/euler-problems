package org.xblackcat.euler.problem15;

import org.junit.Assert;
import org.junit.Test;

/**
 * 27.11.2017 16:06
 *
 * @author xBlackCat
 */
public class Problem15Test {

    @Test
    public void routes() {
        Problem15 problem15 = new Problem15();
        Assert.assertEquals(6, problem15.routes(2, 2));
        Assert.assertEquals(20, problem15.routes(3, 3));
    }
}