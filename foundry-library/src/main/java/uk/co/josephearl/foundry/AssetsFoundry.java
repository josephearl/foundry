package uk.co.josephearl.foundry;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.Arrays;

/**
 * A {@link Foundry} that provides typefaces from an applications assets.
 */
class AssetsFoundry implements Foundry {

    private static final String DEFAULT_FILE_EXTENSION = ".ttf";

    private final AssetManager assetManager;
    private final String subDirectory;
    private final String fileExtension;

    public AssetsFoundry(final AssetManager assetManager) {
        this(assetManager, null);
    }

    public AssetsFoundry(final AssetManager assetManager, final String subDirectory) {
        this(assetManager, subDirectory, DEFAULT_FILE_EXTENSION);
    }

    public AssetsFoundry(final AssetManager assetManager, final String subDirectory, final String fileExtension) {
        this.assetManager = assetManager;
        this.subDirectory = subDirectory;
        this.fileExtension = fileExtension;
    }

    @Override
    public Typeface getTypeface(final String name) {
        if (typefaceExists(name)) {
            return loadTypefaceFromAssets(name);
        }
        return null;
    }

    private Typeface loadTypefaceFromAssets(final String typefaceName) {
        return Typeface.createFromAsset(assetManager, filePathFromTypeFaceName(typefaceName));
    }

    private String fileNameFromTypeFaceName(final String typefaceName) {
        return typefaceName + fileExtension;
    }

    private String filePathFromTypeFaceName(final String typefaceName) {
        return (subDirectory != null ? subDirectory + "/" : "") + fileNameFromTypeFaceName(typefaceName);
    }

    private boolean typefaceExists(final String typefaceName) {
        String fileName = fileNameFromTypeFaceName(typefaceName);
        String typefaceDirectory = subDirectory != null ? subDirectory : "";

        try {
            return Arrays.asList(assetManager.list(typefaceDirectory)).contains(fileName);
        } catch (final IOException ignored) {
        }
        return false;
    }

}
