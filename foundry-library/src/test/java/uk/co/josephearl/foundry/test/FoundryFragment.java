package uk.co.josephearl.foundry.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.josephearl.foundry.R;

import java.lang.Override;

/**
 * A simple fragment.
 */
public class FoundryFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.test_layout_fragment_content, container, false);
    }

}
