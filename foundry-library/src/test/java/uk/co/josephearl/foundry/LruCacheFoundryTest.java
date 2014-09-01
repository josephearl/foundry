package uk.co.josephearl.foundry;

import android.graphics.Typeface;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LruCacheFoundryTest {

    @Test
    public void typefaceIsLoadedFromDelegatedFoundry() {
        String typefaceName = "test";
        Foundry delegate = mock(Foundry.class);
        LruCacheFoundry cachedFoundry = new LruCacheFoundry(delegate);

        cachedFoundry.getTypeface(typefaceName);

        verify(delegate).getTypeface(typefaceName);
    }

    @Test
    public void typefaceIsLoadedFromCacheIfPreviouslyLoaded() {
        String typefaceName = "test";
        Typeface typeface = mock(Typeface.class);
        Foundry delegate = mock(Foundry.class);
        when(delegate.getTypeface(typefaceName)).thenReturn(typeface);
        LruCacheFoundry cachedFoundry = new LruCacheFoundry(delegate);
        cachedFoundry.getTypeface(typefaceName);
        verify(delegate, times(1)).getTypeface(typefaceName);

        cachedFoundry.getTypeface(typefaceName);

        verifyNoMoreInteractions(delegate);
    }

    @Test
    public void typefaceIsReturnedFromDelegateFoundry() {
        String typefaceName = "test";
        Typeface typeface = mock(Typeface.class);
        Foundry delegate = mock(Foundry.class);
        when(delegate.getTypeface(typefaceName)).thenReturn(typeface);
        LruCacheFoundry cachedFoundry = new LruCacheFoundry(delegate);

        Typeface result = cachedFoundry.getTypeface(typefaceName);

        assertThat(result).isEqualTo(typeface);
    }

    @Test
    public void nullIsReturnedIfDelegateFoundryReturnsNull() {
        String typefaceName = "test";
        Foundry delegate = mock(Foundry.class);
        when(delegate.getTypeface(typefaceName)).thenReturn(null);
        LruCacheFoundry cachedFoundry = new LruCacheFoundry(delegate);

        Typeface result = cachedFoundry.getTypeface(typefaceName);

        assertThat(result).isNull();
    }

    @Test
    public void attemptIsMadeToLoadTypefaceAgainIfPreviousAttemptReturnedNull() {
        String typefaceName = "test";
        Foundry delegate = mock(Foundry.class);
        when(delegate.getTypeface(typefaceName)).thenReturn(null);
        LruCacheFoundry cachedFoundry = new LruCacheFoundry(delegate);

        cachedFoundry.getTypeface(typefaceName);
        cachedFoundry.getTypeface(typefaceName);

        verify(delegate, times(2)).getTypeface(typefaceName);
    }

    @Test
    public void typefaceIsReloadedIfRemovedFromCache() {
        int cacheSize = 2;
        String typefaceName1 = "test1";
        String typefaceName2 = "test2";
        String typefaceName3 = "test3";
        Typeface typeface1 = mock(Typeface.class);
        Typeface typeface2 = mock(Typeface.class);
        Typeface typeface3 = mock(Typeface.class);
        Foundry delegate = mock(Foundry.class);
        when(delegate.getTypeface(typefaceName1)).thenReturn(typeface1);
        when(delegate.getTypeface(typefaceName2)).thenReturn(typeface2);
        when(delegate.getTypeface(typefaceName3)).thenReturn(typeface3);
        LruCacheFoundry cachedFoundry = new LruCacheFoundry(delegate, cacheSize);
        cachedFoundry.getTypeface(typefaceName1);
        cachedFoundry.getTypeface(typefaceName2);
        cachedFoundry.getTypeface(typefaceName3);

        // The first typeface should now have been pushed out of the cache.
        cachedFoundry.getTypeface(typefaceName1);

        verify(delegate, times(2)).getTypeface(typefaceName1);
    }

}
