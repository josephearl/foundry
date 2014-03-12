package uk.co.josephearl.foundry;

import android.graphics.Typeface;
import android.support.v4.util.LruCache;

/**
 * A simple {@link Foundry} that caches the results of another foundry in a least-recently used cache.
 */
class LruCacheFoundry implements Foundry {

    private static final int DEFAULT_CACHE_SIZE = 5;

    private final Foundry foundry;
    private final LruCache<String, Typeface> typefaceCache;

    public LruCacheFoundry(final Foundry foundry) {
        this(foundry, DEFAULT_CACHE_SIZE);
    }

    public LruCacheFoundry(final Foundry foundry, final int cacheSize) {
        this.foundry = foundry;
        typefaceCache = new LruCache<String, Typeface>(cacheSize);
    }

    @Override
    public Typeface getTypeface(final String name) {
        Typeface cached = typefaceCache.get(name);
        if (cached != null) {
            return cached;
        }
        return loadTypefaceFromFoundry(name);
    }

    private Typeface loadTypefaceFromFoundry(final String name) {
        Typeface typeface = foundry.getTypeface(name);
        if (typeface != null) {
            typefaceCache.put(name, typeface);
        }
        return typeface;
    }

}
