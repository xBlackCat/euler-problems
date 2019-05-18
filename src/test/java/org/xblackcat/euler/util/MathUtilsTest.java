package org.xblackcat.euler.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 */
public class MathUtilsTest {

    @Test
    public void generateBinomialCoefficients() {
        Assert.assertEquals(Collections.emptyList(), Arrays.asList(MathUtils.generateBinomialCoefficientsBig(529)));
    }


}