package uk.co.josephearl.foundry;

import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Foundry} that delegates to a collection of other foundries. The foundries are searched in order until a
 * typeface is found or there are no more foundries.
 */
class FoundryChain implements Foundry {

    private final List<Foundry> foundries = new ArrayList<Foundry>();

    public FoundryChain(final Foundry... foundries) {
        for (Foundry foundry : foundries) {
            this.foundries.add(foundry);
        }
    }

    @Override
    public Typeface getTypeface(final String name) {
        for (Foundry foundry : foundries) {
            Typeface result = foundry.getTypeface(name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

}
