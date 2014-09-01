package uk.co.josephearl.foundry;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import uk.co.josephearl.foundry.test.FoundryShadowTheme;
import uk.co.josephearl.foundry.test.FoundryTestConfig;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = FoundryTestConfig.DEFAULT, shadows = {FoundryShadowTheme.class})
public class FoundryLayoutInflaterTest {

    @Test
    public void correctTypefaceIsApplied() {
        String normalTypeface = "normal";
        String customTypeface = "bentham";

        Context context = Robolectric.getShadowApplication().getApplicationContext();
        Foundry foundry = mock(Foundry.class);
        Typeface normal = mock(Typeface.class);
        Typeface bentham = mock(Typeface.class);
        when(foundry.getTypeface(normalTypeface)).thenReturn(normal);
        when(foundry.getTypeface(customTypeface)).thenReturn(bentham);
        FoundryLayoutInflater inflater = new FoundryLayoutInflater(context, foundry);

        View root = inflater.inflate(R.layout.test_layout_simple, null);

        verify(foundry).getTypeface(normalTypeface);
        verify(foundry).getTypeface(customTypeface);
        verifyNoMoreInteractions(foundry);

        TextView text1 = (TextView) root.findViewById(android.R.id.text1);
        assertThat(text1).isNotNull();
        assertThat(text1.getTypeface()).isEqualTo(normal);

        TextView text2 = (TextView) root.findViewById(android.R.id.text2);
        assertThat(text2.getTypeface()).isEqualTo(bentham);
    }

}
