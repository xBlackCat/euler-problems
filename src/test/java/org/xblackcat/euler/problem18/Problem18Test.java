package org.xblackcat.euler.problem18;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * 28.11.2017 11:06
 *
 * @author xBlackCat
 */
public class Problem18Test {
    @Test
    public void maxSumOfTriangle() throws IOException {
        Problem18 problem18 = new Problem18();
        Assert.assertEquals(23, problem18.maxSumOfTriangle("/problem18/test.txt"));
        Assert.assertEquals(308, problem18.maxSumOfTriangle("/problem18/test1.txt"));
        Assert.assertEquals(390, problem18.maxSumOfTriangle("/problem18/test2.txt"));
    }
}