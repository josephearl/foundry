package uk.co.josephearl.foundry.test;

import android.content.Intent;
import android.graphics.Typeface;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;
import uk.co.josephearl.foundry.sample.FoundrySampleActivity;

public class FoundrySampleActivityUnitTest extends ActivityUnitTestCase<FoundrySampleActivity> {

    public FoundrySampleActivityUnitTest() {
        super(FoundrySampleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), FoundrySampleActivity.class);
        startActivity(intent, null, null);
    }

    public void testActivityIsStartedSuccessfully() {
        getActivity();

        assertTrue(true);
    }

    public void testFirstTextViewHasCorrectTypeface() {
        TextView text1 = (TextView) getActivity().findViewById(android.R.id.text1);

        Typeface result = text1.getTypeface();

        assertNotNull(result);
        assertEquals(Typeface.DEFAULT, result);
    }

    public void testSecondTextViewHasCorrectTypeface() {
        TextView text2 = (TextView) getActivity().findViewById(android.R.id.text2);

        Typeface result = text2.getTypeface();

        assertNotNull(result);
        assertNotSame(Typeface.DEFAULT, result);
    }

}
