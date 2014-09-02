package uk.co.josephearl.foundry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.AndroidLayoutInflater;
import android.view.LayoutInflater;
import android.view.View;

abstract class AbstractCustomLayoutInflater extends AndroidLayoutInflater {

    protected AbstractCustomLayoutInflater(final Context context) {
        super(context);
    }

    protected AbstractCustomLayoutInflater(final LayoutInflater original, final Context newContext) {
        super(original, newContext);
    }

    @Override
    public View createView2(final String name, final String prefix, final AttributeSet attrs) throws
            ClassNotFoundException {
        final View v = super.createView2(name, prefix, attrs);
        applyViewAttributes(v, attrs);
        return v;
    }

    @Override
    public View onCreateView(final String name, final AttributeSet attrs) throws ClassNotFoundException {
        final View v = super.onCreateView(name, attrs);
        applyViewAttributes(v, attrs);
        return v;
    }

    @Override
    public abstract LayoutInflater cloneInContext(Context newContext);

    /**
     * Process a views XML attributes after the view has been created.
     */
    protected abstract void applyViewAttributes(View view, AttributeSet set);

}