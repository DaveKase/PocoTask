package ee.taavikase.pocotask.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getTextFromEdits(int editTextId) {
        EditText editText = (EditText) findViewById(editTextId);
        return editText.getText().toString();
    }

    public void showAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage(text);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public boolean validEmail(String email) {
        boolean isValid =  !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS
                .matcher(email).matches();

        if (!isValid) {
            showAlert("E-mail not valid!");
        }

        return isValid;
    }

    public void showProgress(int loginId, int progressId) {
        Button loginButton = (Button) findViewById(loginId);
        loginButton.setEnabled(false);

        ProgressBar progress = (ProgressBar) findViewById(progressId);
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgress(int loginId, int progressId) {
        Button loginButton = (Button) findViewById(loginId);
        loginButton.setEnabled(true);

        ProgressBar progress = (ProgressBar) findViewById(progressId);
        progress.setVisibility(View.GONE);
    }
}
