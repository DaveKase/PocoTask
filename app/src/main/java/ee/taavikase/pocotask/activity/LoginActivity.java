package ee.taavikase.pocotask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.net.HttpURLConnection;

import ee.taavikase.pocotask.R;
import ee.taavikase.pocotask.web.Sender;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setActionBar("LoginActivity", "");
    }

    public void login(View v) {
        String email = getTextFromEdits(R.id.emailEdit);
        String password = getTextFromEdits(R.id.passwordEdit);

        if (validEmail(email) && passwordEntered(password)) {
            sendData(email, password);
        }
    }

    public boolean passwordEntered(String password) {
        boolean isValid = false;

        if (password.equals("")) {
            showAlert("Password not entered!");
        } else {
            isValid = true;
        }

        return isValid;
    }

    private void sendData(final String email, final String password) {
        showProgress(R.id.loginButton, R.id.progressBar);

        new Thread("Login") {
            public void run() {
                int responseCode = Sender.login(email, password);
                runOnUi(responseCode);
            }
        }.start();
    }

    private void runOnUi(final int responseCode) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    hideProgress(R.id.loginButton, R.id.progressBar);
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    hideProgress(R.id.loginButton, R.id.progressBar);
                    showAlert("Error with logging in, wrong credentials");
                }
            }
        });
    }
}
