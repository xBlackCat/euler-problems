package org.xblackcat.euler.problem387;

import org.junit.Assert;
import org.junit.Test;

/**
 * 16.11.2017 8:56
 *
 * @author xBlackCat
 */
public class Problem387Test {
    @Test
    public void sumR() {
        Assert.assertEquals(90619, new Problem387().sumRecursiveStart(10000));
    }
}