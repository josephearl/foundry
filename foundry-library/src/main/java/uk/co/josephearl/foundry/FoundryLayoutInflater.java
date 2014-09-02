package uk.co.josephearl.foundry;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public final class FoundryLayoutInflater extends AbstractCustomLayoutInflater {

    private static final String TYPEFACE_ATTRIBUTE_NAME = "foundryTypeface";

    private final Foundry foundry;
    private final String defaultTypefaceName;
    private final int[] typefaceAttributeSet;

    public FoundryLayoutInflater(final Context context) {
        this(context, new FoundryFoundry(context.getAssets()));
    }

    public FoundryLayoutInflater(final Context context, final String defaultTypefaceName) {
        this(context, new FoundryFoundry(context.getAssets()), defaultTypefaceName);
    }

    public FoundryLayoutInflater(final Context context, final Foundry foundry) {
        this(context, foundry, null);
    }

    public FoundryLayoutInflater(final Context context, final Foundry foundry, final String defaultTypefaceName) {
        super(context);
        this.foundry = foundry;
        this.defaultTypefaceName = defaultTypefaceName;
        typefaceAttributeSet = new int[]{resolveTypefaceAttribute()};
    }

    protected FoundryLayoutInflater(final LayoutInflater original, final Context newContext, final Foundry foundry) {
        this(original, newContext, foundry, null);
    }

    protected FoundryLayoutInflater(final LayoutInflater original, final Context newContext, final Foundry foundry,
            final String defaultTypefaceName) {
        super(original, newContext);
        this.foundry = foundry;
        this.defaultTypefaceName = defaultTypefaceName;
        typefaceAttributeSet = new int[]{resolveTypefaceAttribute()};
    }

    @Override
    public LayoutInflater cloneInContext(final Context newContext) {
        return new FoundryLayoutInflater(this, newContext, foundry, defaultTypefaceName);
    }

    @Override
    protected void applyViewAttributes(final View view, final AttributeSet set) {
        if (view instanceof TextView) {
            applyTextViewAttributes((TextView) view, set);
        }
    }

    private int resolveTypefaceAttribute() {
        return getContext().getResources().getIdentifier(TYPEFACE_ATTRIBUTE_NAME, "attr",
                getContext().getPackageName());
    }

    private void applyTextViewAttributes(final TextView textView, final AttributeSet set) {
        String typefaceName = getTypefaceFromAttributes(set);
        String resolvedTypefaceName = typefaceName != null ? typefaceName : defaultTypefaceName;
        if (resolvedTypefaceName != null) {
            applyTypeface(textView, resolvedTypefaceName);
        }
    }

    private void applyTypeface(final TextView textView, final String typefaceName) {
        Typeface typeface = foundry.getTypeface(typefaceName);
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }

    private String getTypefaceFromAttributes(final AttributeSet set) {
        TypedArray a = getContext().obtainStyledAttributes(set, typefaceAttributeSet);
        String typeface = a.getString(0);
        a.recycle();
        return typeface;
    }

}
