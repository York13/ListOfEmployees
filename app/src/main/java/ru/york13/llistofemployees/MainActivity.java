package ru.york13.llistofemployees;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ParceTask(this).execute();
    }

    private static class ParceTask extends AsyncTask<Void, Void, String> {

        private WeakReference<MainActivity> activityReference;

        ParceTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://gitlab.65apps.com/65gb/static/raw/master/testTask.json");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                resultJson = stringBuilder.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            Log.d(TAG, "Вот наш strJson: " + strJson);

            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            JSONObject jsonObject;

            try {
                jsonObject = new JSONObject(strJson);
                JSONArray workers = jsonObject.getJSONArray("response");

                JSONObject worker = workers.getJSONObject(0);
                JSONObject specialty = worker.getJSONObject("specialty");

                String name = worker.getString("f_name");
                String lastname = worker.getString("l_name");
                String birthday = worker.getString("birthday");
                String avatarURL = worker.getString("avatr_url");
                String specialtyId = specialty.getString("specialty_id");
                String post = specialty.getString("name");

                Log.d(TAG, "Имя: " + name);
                Log.d(TAG, "Фамилия: " + lastname);
                Log.d(TAG, "День рождения: " + birthday);
                Log.d(TAG, "Аватар: " + avatarURL);
                Log.d(TAG, "ID должности: " + specialtyId);
                Log.d(TAG, "Должность: " + post);

            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView textView = activity.findViewById(R.id.textView);
            textView.setText(strJson);

        }
    }
}
