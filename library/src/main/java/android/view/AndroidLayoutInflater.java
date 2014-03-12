package android.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Copy of the PhoneLayoutInflater included with Android. Placed in the {@code android.view} package so we can override
 * {@link #createViewFromTag(View, String, android.util.AttributeSet)}.
 */
public class AndroidLayoutInflater extends LayoutInflater {

    private static final String[] classPrefixList = {"android.widget.", "android.webkit."};

    public AndroidLayoutInflater(final Context context) {
        super(context);
    }

    protected AndroidLayoutInflater(final LayoutInflater original, final Context newContext) {
        super(original, newContext);
    }

    @Override
    public LayoutInflater cloneInContext(final Context newContext) {
        return new AndroidLayoutInflater(this, newContext);
    }

    public View createView2(final String name, final String prefix, final AttributeSet attrs) throws
            InflateException, ClassNotFoundException {
        return createView(name, prefix, attrs);
    }

    @Override
    protected View onCreateView(final String name, final AttributeSet attrs) throws ClassNotFoundException {
        for (final String prefix : classPrefixList) {
            try {
                final View view = createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (final ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }

        return super.onCreateView(name, attrs);
    }

    /*
     * This actually overrides the createViewFromTag from LayoutInflater at runtime,
     * but we can't use the @override annotation or we will get a compile error.
     */
    @SuppressLint("NewApi")
    View createViewFromTag(final View parent, String name, final AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }

        try {
            View view;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && getFactory2() != null) {
                view = getFactory2().onCreateView(parent, name, getContext(), attrs);
            } else if (getFactory() != null) {
                view = getFactory().onCreateView(name, getContext(), attrs);
            } else {
                view = null;
            }

            if (view == null) {
                if (-1 == name.indexOf('.')) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        view = onCreateView(parent, name, attrs);
                    } else {
                        view = onCreateView(name, attrs);
                    }
                } else {
                    view = createView2(name, null, attrs);
                }
            }

            return view;

        } catch (final InflateException e) {
            throw e;
        } catch (final ClassNotFoundException e) {
            final InflateException ie = new InflateException(attrs
                    .getPositionDescription() + ": Error inflating class " + name + ", not found");
            ie.initCause(e);
            throw ie;

        } catch (final Exception e) {
            final InflateException ie = new InflateException(attrs
                    .getPositionDescription() + ": Error inflating class " + name);
            ie.initCause(e);
            throw ie;
        }
    }

}