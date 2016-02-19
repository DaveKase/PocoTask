package ee.taavikase.pocotask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ee.taavikase.pocotask.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View clickedButton) {
        Intent intent = null;

        switch (clickedButton.getId()) {
            case R.id.loginButton:
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.registerButton:
                intent = new Intent(this, RegisterActivity.class);
                break;
        }

        try {
            startActivity(intent);
        } catch (NullPointerException e) {
            Log.e(TAG, "Error with starting activity, have you declared an intent");
        }
    }
}
