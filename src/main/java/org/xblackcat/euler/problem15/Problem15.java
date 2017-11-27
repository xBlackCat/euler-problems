package org.xblackcat.euler.problem15;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * https://projecteuler.net/problem=15
 * <p>
 * 27.11.2017 16:03
 *
 * @author xBlackCat
 */
@InputData({"20", "20"})
@ResultDescription("{0,number,#} routes are exists through a {1}×{2} grid")
public class Problem15 {
    private final Map<R, Long> foundRects = new HashMap<>();

    @EntryPoint
    public long routes(int h, int w) {
        R key = R.of(h, w);
        Long value = foundRects.get(key);
        if (value != null) {
            return value;
        }
        if (h == 1) {
            return w + 1;
        }
        if (w == 1) {
            return h + 1;
        }
        long result = routes(h - 1, w) + routes(h, w - 1);
        foundRects.put(key, result);
        return result;
    }
}
