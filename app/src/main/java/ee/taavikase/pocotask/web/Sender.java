package ee.taavikase.pocotask.web;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sender {
    private static final String TAG = "Sender";

    public static int register(String email, String password, String country, String city, int zipCode) {
        try {
            String url = "https://poco-test.herokuapp.com/addUser";

            JSONObject jObject = new JSONObject();
            jObject.put("email", email);
            jObject.put("password", password);
            jObject.put("country", country);
            jObject.put("city", city);
            jObject.put("postal_code", zipCode);

            return send(url, jObject);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return -1;
        }
    }

    public static int login(String email, String password) {
        try {
            String url = "https://poco-test.herokuapp.com/login";
            JSONObject jObject = new JSONObject();
            jObject.put("email", email);
            jObject.put("password", password);

            return send(url, jObject);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return -1;
        }
    }

    private static int send(String uri, JSONObject jObject) {
        try {
            URL url = new URL(uri);
            HttpURLConnection  httpConn = setUpConnection(url);
            DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream ());
            wr.writeBytes(jObject.toString());
            wr.flush();
            wr.close();

            int HttpResult = httpConn.getResponseCode();

            if (HttpResult != HttpURLConnection.HTTP_OK){
                Log.i(TAG, HttpResult + " Error: " + httpConn.getResponseMessage());
            }

            httpConn.disconnect();
            return HttpResult;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static HttpURLConnection setUpConnection(URL url) throws IOException {
        HttpURLConnection  httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        httpConn.setUseCaches(false);
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.connect();

        return httpConn;
    }
}
