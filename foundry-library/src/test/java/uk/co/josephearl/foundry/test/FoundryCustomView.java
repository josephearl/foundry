package uk.co.josephearl.foundry.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * The simplest custom view implementation.
 */
public class FoundryCustomView extends View {

    public FoundryCustomView(final Context context) {
        super(context);
    }

    public FoundryCustomView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public FoundryCustomView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

}
