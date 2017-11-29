package org.xblackcat.euler.problem26;

import org.junit.Assert;
import org.junit.Test;

/**
 * 29.11.2017 18:03
 *
 * @author xBlackCat
 */
public class Problem26Test {
    @Test
    public void searchPeriodicNumbersTill() {
        Problem26 problem26 = new Problem26();
        Assert.assertEquals(7, problem26.searchPeriodicNumbersTill(10));
    }
}