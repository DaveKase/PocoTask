package ee.taavikase.pocotask.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setActionBar(String tag, String title) {
        try {
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if (title != null && !title.equals("")) {
                getSupportActionBar().setTitle(title);
            }
        } catch (NullPointerException e) {
            Log.e(tag, "No actionbar");
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

    protected String getTextFromEdits(int editTextId) {
        EditText editText = (EditText) findViewById(editTextId);
        return editText.getText().toString();
    }

    protected void showAlert(String text) {
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

    protected boolean validEmail(String email) {
        boolean isValid = !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS
                .matcher(email).matches();

        if (!isValid) {
            showAlert("E-mail not valid!");
        }

        return isValid;
    }

    protected void showProgress(int loginId, int progressId) {
        Button loginButton = (Button) findViewById(loginId);
        loginButton.setEnabled(false);

        ProgressBar progress = (ProgressBar) findViewById(progressId);
        progress.setVisibility(View.VISIBLE);
    }

    protected void hideProgress(int loginId, int progressId) {
        Button loginButton = (Button) findViewById(loginId);
        loginButton.setEnabled(true);

        ProgressBar progress = (ProgressBar) findViewById(progressId);
        progress.setVisibility(View.GONE);
    }
}
