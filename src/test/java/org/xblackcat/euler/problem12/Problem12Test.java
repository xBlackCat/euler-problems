package org.xblackcat.euler.problem12;

import org.junit.Assert;
import org.junit.Test;

/**
 * 24.11.2017 9:53
 *
 * @author xBlackCat
 */
public class Problem12Test {

    @Test
    public void triangleNumberWithDivisors() {
        Problem12 problem12 = new Problem12();
        Assert.assertEquals(28, problem12.triangleNumberWithDivisors(5));
    }
}