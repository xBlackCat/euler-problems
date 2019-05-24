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
    public void test2() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(267, problem309.ladders(2500));
    }

    @Test
    public void test2_1() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(268, problem309.ladders(2501));
    }

    @Test
    public void test3() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(146, problem309.ladders(1600));
    }

//    @Test
    public void test2_() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(267, problem309.bruteForce(2500));
    }

//    @Test
    public void test2_1_() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(268, problem309.bruteForce(2501));
    }

//    @Test
    public void test3_() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(146, problem309.bruteForce(1600));
    }

    @Test
    public void test4() {
        final Problem309 problem309 = new Problem309();
        Assert.assertEquals(13, problem309.ladders(300));
    }

    @Test
    public void searchTriangles() {
        System.out.println(new PythagoreanTriplets().searchByHypo(119));
        System.out.println(new PythagoreanTriplets().searchByCathetus(21, 119));
    }
}