package uk.co.josephearl.foundry;

import android.graphics.Typeface;
import org.junit.Test;
import org.mockito.InOrder;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FoundryChainTest {

    @Test
    public void foundriesAreCheckedInOrder() {
        String typefaceName = "test";
        Foundry foundry1 = mock(Foundry.class);
        Foundry foundry2 = mock(Foundry.class);
        Foundry foundry3 = mock(Foundry.class);
        FoundryChain chain = new FoundryChain(foundry1, foundry2, foundry3);

        chain.getTypeface(typefaceName);

        InOrder order = inOrder(foundry1, foundry2, foundry3);
        order.verify(foundry1).getTypeface(typefaceName);
        order.verify(foundry2).getTypeface(typefaceName);
        order.verify(foundry3).getTypeface(typefaceName);
    }


    @Test
    public void nullIsReturnedIfTypefaceWithNameDoesNotExistInChain() {
        String typefaceName = "test";
        Foundry foundry1 = mock(Foundry.class);
        Foundry foundry2 = mock(Foundry.class);
        Foundry foundry3 = mock(Foundry.class);
        FoundryChain chain = new FoundryChain(foundry1, foundry2, foundry3);

        Typeface result = chain.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

    @Test
    public void typefaceIsReturnedFromFirstMatchingFoundryInChain() {
        String typefaceName = "test";
        Foundry foundry1 = mock(Foundry.class);
        Foundry foundry2 = mock(Foundry.class);
        Foundry foundry3 = mock(Foundry.class);
        Typeface typeface1 = mock(Typeface.class);
        Typeface typeface2 = mock(Typeface.class);
        when(foundry1.getTypeface(typefaceName)).thenReturn(null);
        when(foundry2.getTypeface(typefaceName)).thenReturn(typeface1);
        when(foundry3.getTypeface(typefaceName)).thenReturn(typeface2);
        FoundryChain chain = new FoundryChain(foundry1, foundry2, foundry3);

        Typeface result = chain.getTypeface(typefaceName);

        assertThat(result).isEqualTo(typeface1);
    }

}
