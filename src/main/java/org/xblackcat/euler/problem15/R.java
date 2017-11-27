package org.xblackcat.euler.problem15;

import java.util.Objects;

/**
 * 27.11.2017 16:13
 *
 * @author xBlackCat
 */
class R {
    private final int h;
    private final int w;

    public static R of(int a, int b) {
        if (a > b) {
            return new R(b, a);
        } else {
            return new R(a, b);
        }
    }

    private R(int h, int w) {
        if (h > w) {
            throw new IllegalArgumentException("Height should be less than width");
        }
        this.h = h;
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final R r = (R) o;
        return h == r.h &&
                w == r.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, w);
    }
}
