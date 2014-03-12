package uk.co.josephearl.foundry;

import android.graphics.Typeface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import uk.co.josephearl.foundry.test.FoundryTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(FoundryTestRunner.class)
@Config(manifest = Config.NONE)
public class AndroidDefaultFoundryTest {

    @Test
    public void defaultTypefaceIsReturnedForNameNormal() {
        AndroidDefaultFoundry androidFoundry = new AndroidDefaultFoundry();

        Typeface result = androidFoundry.getTypeface(AndroidDefaultFoundry.NORMAL);

        assertThat(result).isEqualTo(Typeface.DEFAULT);
    }

    @Test
    public void defaultBoldTypefaceIsReturnedForNameBold() {
        AndroidDefaultFoundry androidFoundry = new AndroidDefaultFoundry();

        Typeface result = androidFoundry.getTypeface(AndroidDefaultFoundry.BOLD);

        assertThat(result).isEqualTo(Typeface.DEFAULT_BOLD);
    }

    @Test
    public void sanSerifTypefaceIsReturnedForNameSansSerif() {
        AndroidDefaultFoundry androidFoundry = new AndroidDefaultFoundry();

        Typeface result = androidFoundry.getTypeface(AndroidDefaultFoundry.SANS_SERIF);

        assertThat(result).isEqualTo(Typeface.SANS_SERIF);
    }

    @Test
    public void serifTypefaceIsReturnedForNameSerif() {
        AndroidDefaultFoundry androidFoundry = new AndroidDefaultFoundry();

        Typeface result = androidFoundry.getTypeface(AndroidDefaultFoundry.SERIF);

        assertThat(result).isEqualTo(Typeface.SERIF);
    }

    @Test
    public void monspaceTypefaceIsReturnedForNameMonospace() {
        AndroidDefaultFoundry androidFoundry = new AndroidDefaultFoundry();

        Typeface result = androidFoundry.getTypeface(AndroidDefaultFoundry.MONOSPACE);

        assertThat(result).isEqualTo(Typeface.MONOSPACE);
    }

    @Test
    public void nullIsReturnedIfTypefaceNameIsNotRecognized() {
        String typefaceName = "customTypeface";
        AndroidDefaultFoundry androidFoundry = new AndroidDefaultFoundry();

        Typeface result = androidFoundry.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

}
