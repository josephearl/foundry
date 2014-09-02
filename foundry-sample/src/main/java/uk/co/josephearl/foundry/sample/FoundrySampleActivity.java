package uk.co.josephearl.foundry.sample;

import android.app.Activity;
import android.os.Bundle;
import uk.co.josephearl.foundry.Foundry;
import uk.co.josephearl.foundry.FoundryFoundry;
import uk.co.josephearl.foundry.FoundryLayoutInflater;

public class FoundrySampleActivity extends Activity {

    public FoundrySampleActivity() {
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Foundry foundry = new FoundryFoundry(getAssets());
        FoundryLayoutInflater inflater = new FoundryLayoutInflater(this, foundry);

        setContentView(inflater.inflate(R.layout.activity_foundry_sample, null));
    }

}
