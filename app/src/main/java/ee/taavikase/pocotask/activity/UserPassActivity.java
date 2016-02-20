package ee.taavikase.pocotask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import ee.taavikase.pocotask.R;

public class UserPassActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pass);

        setActionBar("UserPassActivity", "Registration (2/3)");
    }

    public void next(View v) {
        String email = getTextFromEdits(R.id.emailEdit);
        String password = getTextFromEdits(R.id.passwordEdit);

        if (validEmail(email) && longPassword(password) && termsChecked()) {
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
