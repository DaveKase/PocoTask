package ee.taavikase.pocotask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.net.HttpURLConnection;

import ee.taavikase.pocotask.R;
import ee.taavikase.pocotask.web.Sender;

public class AddressActivity extends BaseActivity {
    private static final String TAG = "AddressActivity";
    private int mTryOuts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        String email = getIntent().getExtras().getString("email");
        assert email != null;
        String substringedMail = email.substring(0, 5);
        setActionBar("AddressActivity", "Registration (3/3) " + substringedMail);

        setSpinner();
    }

    private void setSpinner() {
        Countries countries = new Countries();
        Spinner spinner = (Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, countries.getCountries());

        spinner.setAdapter(adapter);
    }

    public void next(View v) {
        String country = getCountryCode();
        String city = getTextFromEdits(R.id.cityEdit);
        String zipCode = getTextFromEdits(R.id.zipEdit);

        if (isValidCity(city) && isValidZipCode(zipCode)) {
            sendData(country, city, zipCode);
        }
    }

    private String getCountryCode() {
        Spinner spinner = (Spinner) findViewById(R.id.countrySpinner);
        String selectedCountry = spinner.getSelectedItem().toString();
        Countries countries = new Countries();
        return countries.getCountryCode(selectedCountry);
    }

    private boolean isValidCity(String city) {
        boolean isValid = false;

        if (city.equals("")) {
            showAlert("City not entered!");
        } else {
            isValid = true;
        }

        return isValid;
    }

    private boolean isValidZipCode(String zipCode) {
        boolean isValid = false;

        if (zipCode.equals("")) {
            showAlert("Postal code not entered!");
        } else {
            isValid = true;
        }

        return isValid;
    }

    private void sendData(final String country, final String city, final String zipCode) {
        showProgress(R.id.nextButton, R.id.progressBar);

        new Thread("Register") {
            public void run() {
                String email = getIntent().getExtras().getString("email");
                String password = getIntent().getExtras().getString("password");
                int zip = Integer.parseInt(zipCode);
                Log.i(TAG, "country = " + country);
                int responseCode = Sender.register(email, password, country, city, zip);
                runOnUi(responseCode);
            }
        }.start();
    }

    private void runOnUi(final int responseCode) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    hideProgress(R.id.nextButton, R.id.progressBar);
                    Intent intent = new Intent(AddressActivity.this, RegisterSuccessActivity.class);
                    startActivity(intent);
                    finish();
                } else if (responseCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT && mTryOuts < 3) {
                    mTryOuts++;
                    sendData(getCountryCode(), getTextFromEdits(R.id.cityEdit),
                            getTextFromEdits(R.id.zipEdit));
                } else {
                    hideProgress(R.id.nextButton, R.id.progressBar);
                    showAlert("Error with registering");
                    mTryOuts = 0;
                }
            }
        });
    }
}
