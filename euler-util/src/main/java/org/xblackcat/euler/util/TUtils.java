package org.xblackcat.euler.util;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.set.TLongSet;

/**
 * 17.11.2017 13:21
 *
 * @author xBlackCat
 */
public class TUtils {
    public static long max(TLongSet set) {
        if (set.isEmpty()) {
            throw new IllegalArgumentException("Empty set");
        }

        final TLongIterator it = set.iterator();
        long max = it.next();
        while (it.hasNext()) {
            final long v = it.next();
            if (max < v) {
                max = v;
            }
        }

        return max;
    }
}
