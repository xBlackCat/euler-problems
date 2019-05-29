package org.xblackcat.euler.util;

import gnu.trove.set.TLongSet;

import java.util.Set;

/**
 *
 */
public abstract class AFactorizer<V, R> {
    protected final PrimalCache primalCache;

    protected AFactorizer() {
        this(new PrimalCache());
    }

    protected AFactorizer(PrimalCache primalCache) {
        this.primalCache = primalCache;
    }

    public PrimalCache getPrimalCache() {
        return primalCache;
    }

    public abstract R factorize(V n);

}
