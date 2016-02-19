package ee.taavikase.pocotask.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import ee.taavikase.pocotask.R;

public class RegisterSuccessActivity extends BaseActivity {
    private static final String TAG = "RegisterSuccessActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        setActionBar();
    }

    private void setActionBar() {
        try {
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(TAG, "No actionbar");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
