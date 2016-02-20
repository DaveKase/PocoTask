package ee.taavikase.pocotask.activity;

import android.os.Bundle;

import ee.taavikase.pocotask.R;

public class LoginSuccessActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        setActionBar("LoginSuccessActivity", "");
    }
}
