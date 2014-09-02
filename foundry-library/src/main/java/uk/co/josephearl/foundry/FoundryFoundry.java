package uk.co.josephearl.foundry;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * The default Foundry {@link Foundry} implementation.
 */
public final class FoundryFoundry implements Foundry {

    private final FoundryChain foundryChain;

    public FoundryFoundry(final AssetManager assetManager) {
        this(new AssetsFoundry(assetManager));
    }

    public FoundryFoundry(final AssetManager assetManager, final String subDirectory) {
        this(new AssetsFoundry(assetManager, subDirectory));
    }

    public FoundryFoundry(final AssetManager assetManager, final String subDirectory, final String fileExtension) {
        this(new AssetsFoundry(assetManager, subDirectory, fileExtension));
    }

    private FoundryFoundry(final AssetsFoundry assetsFoundry) {
        foundryChain = new FoundryChain(new AndroidDefaultFoundry(), new LruCacheFoundry(assetsFoundry));
    }

    @Override
    public Typeface getTypeface(final String name) {
        return foundryChain.getTypeface(name);
    }

}
