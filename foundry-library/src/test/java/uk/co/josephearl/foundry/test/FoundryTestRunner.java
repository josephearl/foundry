package uk.co.josephearl.foundry.test;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.Fs;
import org.robolectric.res.FsFile;

import java.io.File;

/**
 * Test runner that fixes issues with IntelliJ/Android Studio using different paths to Maven.
 */
public class FoundryTestRunner extends RobolectricTestRunner {

    private static final String MODULE_NAME = "foundry-library";

    public FoundryTestRunner(final Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest createAppManifest(final FsFile manifestFile, final FsFile resDir,
            final FsFile assetsDir) {
        FsFile moduleManifestFile = fixModuleDirectoryPathIfNeeded(manifestFile);
        FsFile moduleResDir = fixModuleDirectoryPathIfNeeded(resDir);
        FsFile moduleAssetsDir = fixModuleDirectoryPathIfNeeded(assetsDir);

        return super.createAppManifest(moduleManifestFile, moduleResDir, moduleAssetsDir);
    }

    private boolean directoryPathsNeedFixing() {
        String currentDirectory = new File(".").getAbsolutePath();
        return !currentDirectory.endsWith(MODULE_NAME) && !currentDirectory.endsWith(MODULE_NAME + "/.");
    }

    private FsFile fixModuleDirectoryPath(final FsFile fsFile) {
        return Fs.newFile(new File(fsFile.getPath().replace("./", "./" + MODULE_NAME + "/")));
    }

    private FsFile fixModuleDirectoryPathIfNeeded(final FsFile fsFile) {
        if (!directoryPathsNeedFixing()) {
            return fsFile;
        }
        return fixModuleDirectoryPath(fsFile);
    }

}
