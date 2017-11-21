package org.xblackcat.euler.util;

import gnu.trove.list.TLongList;
import gnu.trove.list.array.TLongArrayList;

/**
 * 21.11.2017 9:15
 *
 * @author xBlackCat
 */
public class SquareCache {
    private final TLongList squares = new TLongArrayList();

    public long squareOf(int n) {
        if (squares.size() > n) {
            return squares.get(n);
        }

        int i = squares.size();
        while (i <= n) {
            squares.add(i * (long) i);
            i++;
        }
        return squares.get(n);
    }
}
