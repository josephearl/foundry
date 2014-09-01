package uk.co.josephearl.foundry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import uk.co.josephearl.foundry.test.FoundryTestConfig;
import uk.co.josephearl.foundry.test.FoundryCustomView;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = FoundryTestConfig.DEFAULT)
public class AbstractCustomLayoutInflaterTest {

    @Test
    @Config(reportSdk=8)
    public void applyIsCalledOnceForAllViewsInHeirarchy() {
        Context context = Robolectric.getShadowApplication().getApplicationContext();
        TestCustomLayoutInflater inflater = new TestCustomLayoutInflater(context);

        inflater.inflate(R.layout.test_layout_simple, null);

        List<TestCustomLayoutInflater.ViewEntry> viewEntries = inflater.getViewEntries();
        assertThat(viewEntries).hasSize(4);
        assertThat(viewEntries.get(0).view).isInstanceOf(LinearLayout.class);
        assertThat(viewEntries.get(1).view).isInstanceOf(TextView.class);
        assertThat(viewEntries.get(1).view.getId()).isEqualTo(android.R.id.text1);
        assertThat(viewEntries.get(2).view).isInstanceOf(FrameLayout.class);
        assertThat(viewEntries.get(3).view).isInstanceOf(TextView.class);
        assertThat(viewEntries.get(3).view.getId()).isEqualTo(android.R.id.text2);
    }

    @Test
    @Config(reportSdk=18)
    public void applyIsCalledOnceForAllViewsInHeirarchyOnHoneycomb() {
        applyIsCalledOnceForAllViewsInHeirarchy();
    }

    @Test
    public void applyIsCalledOnceForAllViewsInHeirarchyWithCustomViewOnHoneyComb() {
        Context context = Robolectric.getShadowApplication().getApplicationContext();
        TestCustomLayoutInflater inflater = new TestCustomLayoutInflater(context);

        inflater.inflate(R.layout.test_layout_custom_view, null);

        List<TestCustomLayoutInflater.ViewEntry> viewEntries = inflater.getViewEntries();
        assertThat(viewEntries).hasSize(1);
        assertThat(viewEntries.get(0).view).isInstanceOf(FoundryCustomView.class);
    }

    @Test
    public void customViewIsCorrectlyInflated() {
        Context context = Robolectric.getShadowApplication().getApplicationContext();
        TestCustomLayoutInflater inflater = new TestCustomLayoutInflater(context);

        View root = inflater.inflate(R.layout.test_layout_custom_view, null);

        assertThat(root).isInstanceOf(FoundryCustomView.class);
    }

    private static class TestCustomLayoutInflater extends AbstractCustomLayoutInflater {

        private List<ViewEntry> viewEntries = new ArrayList<ViewEntry>();

        private TestCustomLayoutInflater(final Context context) {
            super(context);
        }

        private TestCustomLayoutInflater(final LayoutInflater original, final Context newContext) {
            super(original, newContext);
        }

        @Override
        public LayoutInflater cloneInContext(final Context newContext) {
            return new TestCustomLayoutInflater(this, newContext);
        }

        @Override
        protected void applyViewAttributes(final View view, final AttributeSet set) {
            ViewEntry entry = new ViewEntry();
            entry.view = view;
            entry.set = set;

            viewEntries.add(entry);
        }

        public List<ViewEntry> getViewEntries() {
            return viewEntries;
        }

        public static class ViewEntry {
            View view;
            AttributeSet set;
        }

    }

}
