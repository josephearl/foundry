package uk.co.josephearl.foundry;

import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Foundry} which provides the default Android typefaces with the names: {@code normal}, {@code bold},
 * {@code sansSerif}, {@code serif} and {@code monospace}.
 */
class AndroidDefaultFoundry implements Foundry {

    public static final String NORMAL = "normal";
    public static final String BOLD = "bold";
    public static final String SANS_SERIF = "sansSerif";
    public static final String SERIF = "serif";
    public static final String MONOSPACE = "monospace";

    private static final Map<String, Typeface> defaultTypefaces = new HashMap<String, Typeface>();

    static {
        defaultTypefaces.put(NORMAL, Typeface.DEFAULT);
        defaultTypefaces.put(BOLD, Typeface.DEFAULT_BOLD);
        defaultTypefaces.put(SANS_SERIF, Typeface.SANS_SERIF);
        defaultTypefaces.put(SERIF, Typeface.SERIF);
        defaultTypefaces.put(MONOSPACE, Typeface.MONOSPACE);
    }

    public AndroidDefaultFoundry() {
    }

    @Override
    public Typeface getTypeface(final String name) {
        return defaultTypefaces.get(name);
    }

}
