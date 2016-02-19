package ee.taavikase.pocotask.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import ee.taavikase.pocotask.R;

public class UserPassActivity extends BaseActivity {
    private static final String TAG = "UserPassActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pass);

        setActionBar();
    }

    private void setActionBar() {
        try {
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Registration (2/3)");
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

    public void next(View v) {
        String email = getTextFromEdits(R.id.emailEdit);
        String password = getTextFromEdits(R.id.passwordEdit);

        if(validEmail(email) && longPassword(password) && termsChecked()) {
            Intent intent = new Intent(this, AddressActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("password", password);

            startActivity(intent);
            finish();
        }
    }

    private boolean longPassword(String password) {
        boolean isLong = false;

        if (password.length() > 6) {
            isLong = true;
        } else {
            showAlert("Password is not long enough!");
        }

        return isLong;
    }

    private boolean termsChecked() {
        CheckBox termsBox = (CheckBox) findViewById(R.id.termsBox);
        boolean isChecked = termsBox.isChecked();

        if (!isChecked) {
            showAlert("Service terms checkbox not checked!");
        }

        return isChecked;
    }
}
