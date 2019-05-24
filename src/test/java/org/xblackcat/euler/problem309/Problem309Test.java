package org.xblackcat.euler.problem309;

import org.junit.Assert;
import org.junit.Test;
import org.xblackcat.euler.util.PythagoreanTriplets;

/**
 *
 */
public class Problem309Test {
    @Test
    public void test() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(5, problem309.ladders(200));
    }

    @Test
    public void searchTriangles() {
        System.out.println(new PythagoreanTriplets().searchByHypo(119));
        System.out.println(new PythagoreanTriplets().searchBCathetus(21, 119));
    }
}