package org.xblackcat.euler.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

/**
 * 21.11.2017 9:20
 *
 * @author xBlackCat
 */
public class PythagoreanTripletsTest {
    @Test
    public void iterate() {
        PythagoreanTriplets triplets = new PythagoreanTriplets();
        Iterator<Triplet> iterator = triplets.iterator();
        Assert.assertEquals(new Triplet(3, 4, 5), iterator.next());
        Assert.assertEquals(new Triplet(5, 12, 13), iterator.next());
        Assert.assertEquals(new Triplet(8, 15, 17), iterator.next());
    }

    @Test
    public void searchBCathetus() {
        final Collection<Triplet> triplets = new PythagoreanTriplets().searchBCathetus(105, 200);

        System.out.println(triplets);

        Assert.assertTrue(triplets.contains(new Triplet(105, 140, 175)));
    }
}