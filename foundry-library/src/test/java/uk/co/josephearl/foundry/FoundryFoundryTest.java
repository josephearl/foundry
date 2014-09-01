package uk.co.josephearl.foundry;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import uk.co.josephearl.foundry.test.FoundryTestConfig;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.Robolectric.buildActivity;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = FoundryTestConfig.DEFAULT)
public class FoundryFoundryTest {

    @Test
    public void customTypefaceIsLoaded() {
        String typefaceName = "bentham";
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNotNull();
    }

    @Test
    public void customTypefaceIsLoadedIfTypefaceWithNameExistsInSubdirectory() {
        String typefaceName = "bentham2";
        String subDirectory = "fonts";
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager(), subDirectory);

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNotNull();
    }

    @Test
    public void customTypefaceIsLoadedIfTypefaceWithNameExistsInSubdirectoryWithCorrectExtension() {
        String typefaceName = "bentham2";
        String subDirectory = "fonts";
        String fileExtension = ".ttf";
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager(), subDirectory, fileExtension);

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNotNull();
    }

    @Test
    public void nullIsReturnedIfTypefaceWithNameExistsInSubdirectoryWithIncorrectExtension() {
        String typefaceName = "bentham2";
        String subDirectory = "fonts";
        String fileExtension = ".otf";
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager(), subDirectory, fileExtension);

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

    @Test
    public void androidDefaultTypefaceIsReturnedForNameNormal() {
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(AndroidDefaultFoundry.NORMAL);

        assertThat(result).isEqualTo(Typeface.DEFAULT);
    }

    @Test
    public void androidDefaultBoldTypefaceIsReturnedForNameBold() {
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(AndroidDefaultFoundry.BOLD);

        assertThat(result).isEqualTo(Typeface.DEFAULT_BOLD);
    }

    @Test
    public void androidSanSerifTypefaceIsReturnedForNameSansSerif() {
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(AndroidDefaultFoundry.SANS_SERIF);

        assertThat(result).isEqualTo(Typeface.SANS_SERIF);
    }

    @Test
    public void androidSerifTypefaceIsReturnedForNameSerif() {
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(AndroidDefaultFoundry.SERIF);

        assertThat(result).isEqualTo(Typeface.SERIF);
    }

    @Test
    public void androidMonspaceTypefaceIsReturnedForNameMonospace() {
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(AndroidDefaultFoundry.MONOSPACE);

        assertThat(result).isEqualTo(Typeface.MONOSPACE);
    }

    @Test
    public void nullIsReturnedIfTypefaceNameIsNotRecognized() {
        String typefaceName = "unrecognizedCustomTypeface";
        FoundryFoundry foundry = new FoundryFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

    private AssetManager getAssetManager() {
        return buildActivity(Activity.class).create().get().getAssets();
    }
    
}
