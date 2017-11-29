package org.xblackcat.euler.problem22;

import org.junit.Assert;
import org.junit.Test;

/**
 * 29.11.2017 8:39
 *
 * @author xBlackCat
 */
public class Problem22Test {
    @Test
    public void nameScore() {
        Problem22 problem22 = new Problem22();
        Assert.assertEquals(53, problem22.nameScore("Colin"));
    }
}