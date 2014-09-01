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
public class AssetsFoundryTest {

    @Test
    public void typefaceIsReturnedIfTypefaceWithNameExists() {
        String typefaceName = "bentham";
        AssetsFoundry foundry = new AssetsFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNotNull();
    }

    @Test
    public void typefaceIsReturnedIfTypefaceWithNameExistsInSubdirectory() {
        String typefaceName = "bentham2";
        String subDirectory = "fonts";
        AssetsFoundry foundry = new AssetsFoundry(getAssetManager(), subDirectory);

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNotNull();
    }

    @Test
    public void typefaceIsReturnedIfTypefaceWithNameExistsInSubdirectoryWithCorrectExtension() {
        String typefaceName = "bentham2";
        String subDirectory = "fonts";
        String fileExtension = ".ttf";
        AssetsFoundry foundry = new AssetsFoundry(getAssetManager(), subDirectory, fileExtension);

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNotNull();
    }

    @Test
    public void nullIsReturnedIfTypefaceWithNameExistsInSubdirectoryWithIncorrectExtension() {
        String typefaceName = "bentham2";
        String subDirectory = "fonts";
        String fileExtension = ".otf";
        AssetsFoundry foundry = new AssetsFoundry(getAssetManager(), subDirectory, fileExtension);

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

    @Test
    public void nullIsReturnedIfTypefaceWithNameDoesNotExist() {
        String typefaceName = "doesNotExist";
        AssetsFoundry foundry = new AssetsFoundry(getAssetManager());

        Typeface result = foundry.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

    private AssetManager getAssetManager() {
        return buildActivity(Activity.class).create().get().getAssets();
    }

}
