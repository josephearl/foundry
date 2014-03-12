package uk.co.josephearl.foundry;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public final class FoundryLayoutInflater extends AbstractCustomLayoutInflater {

    private static final int[] TYPEFACE_ATTRIBUTESET = new int[]{R.attr.typeface};
    private final Foundry foundry;

    public FoundryLayoutInflater(final Context context, final Foundry foundry) {
        super(context);
        this.foundry = foundry;
    }

    protected FoundryLayoutInflater(final LayoutInflater original, final Context newContext, final Foundry foundry) {
        super(original, newContext);
        this.foundry = foundry;
    }

    @Override
    public LayoutInflater cloneInContext(final Context newContext) {
        return new FoundryLayoutInflater(this, newContext, foundry);
    }

    @Override
    protected void applyViewAttributes(final View view, final AttributeSet set) {
        if (view instanceof TextView) {
            applyTextViewAttributes((TextView) view, set);
        }
    }

    private void applyTextViewAttributes(final TextView textView, final AttributeSet set) {
        String typefaceName = getTypefaceFromAttributes(set);
        if (typefaceName != null) {
            applyTypeface(textView, typefaceName);
        }
    }

    private void applyTypeface(final TextView textView, final String typefaceName) {
        Typeface typeface = foundry.getTypeface(typefaceName);
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }

    private String getTypefaceFromAttributes(final AttributeSet set) {
        TypedArray a = getContext().obtainStyledAttributes(set, TYPEFACE_ATTRIBUTESET);
        String typeface = a.getString(0);
        a.recycle();
        return typeface;
    }

}
