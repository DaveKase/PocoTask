package ee.taavikase.pocotask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ee.taavikase.pocotask.R;

public class RegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setActionBar("RegisterActivity", "Registration (1/3)");
    }

    public void next(View v) {
        Intent intent = new Intent(this, UserPassActivity.class);
        startActivity(intent);
        finish();
    }
}
