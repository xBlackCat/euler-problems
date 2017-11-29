package org.xblackcat.euler.problem25;

import org.junit.Assert;
import org.junit.Test;

/**
 * 29.11.2017 17:41
 *
 * @author xBlackCat
 */
public class Problem25Test {

    @Test
    public void indexOfFibonacciOfNDigits() {
        Problem25 problem25 = new Problem25();
        Assert.assertEquals(12, problem25.indexOfFibonacciOfNDigits(3));
    }
}