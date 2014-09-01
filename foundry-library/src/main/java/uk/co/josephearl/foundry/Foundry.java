package uk.co.josephearl.foundry;

import android.graphics.Typeface;

/**
 * A service that provides a number of typefaces.
 */
public interface Foundry {

    /**
     * Retrieve a typeface from the foundry.
     *
     * @param name the name of the typeface; the actual mapping of names to typefaces is not defined and
     * thus left up to the specific implementation.
     * @return the typeface identified by the specified name, or {@code null} if no typeface exists with that name in
     * this foundry.
     */
    Typeface getTypeface(String name);

}
