package uk.co.josephearl.foundry.test;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowResources;
import uk.co.josephearl.foundry.R;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Fixes a bug in the Robolectric {@link org.robolectric.shadows.ShadowResources.ShadowTheme} not obtaining
 * attributes properly: in this case we only ensure the behaviour is fixed for the {@code typeface} attribute.
 */
@Implements(Resources.Theme.class)
public class FoundryShadowTheme extends ShadowResources.ShadowTheme {

    private static final String TYPEFACE_ATTR_NAME_SUFFIX = ":foundryTypeface";

    public FoundryShadowTheme() {
    }

    @Override
    public TypedArray obtainStyledAttributes(final AttributeSet set, final int[] attrs, final int attrId,
            final int styleId) {
        if (isTypefaceAttrs(set, attrs)) {
            return createTypefaceTypedArray(set);
        }
        return super.obtainStyledAttributes(set, attrs, attrId, styleId);
    }

    private boolean isTypefaceAttrs(final AttributeSet set, final int[] attrs) {
        return set != null && attrs.length == 1 && attrs[0] == R.attr.foundryTypeface;
    }

    private TypedArray createTypefaceTypedArray(final AttributeSet set) {
        TypedArray a = mock(TypedArray.class);
        when(a.getString(0)).thenReturn(getTypefaceAttributeValue(set));
        return a;
    }

    private String getTypefaceAttributeValue(final AttributeSet set) {
        for (int i = 0; i < set.getAttributeCount(); i++) {
            if (set.getAttributeName(i).endsWith(TYPEFACE_ATTR_NAME_SUFFIX)) {
                return set.getAttributeValue(i);
            }
        }
        return null;
    }

}
