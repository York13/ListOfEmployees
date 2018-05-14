package ru.york13.llistofemployees;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParceTask extends AsyncTask<Void, Void, String> {

    private String resultJson = "";
    private SQLiteDatabase db;

    public ParceTask(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL("http://gitlab.65apps.com/65gb/static/raw/master/testTask.json");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            resultJson = stringBuilder.toString();

            urlConnection.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);
        Log.d("myLog", "Вот наш strJson: " + strJson);

        try {
            ContentValues contentValues = new ContentValues();

            JSONObject jsonObject = new JSONObject(resultJson);

            JSONArray workers = jsonObject.getJSONArray("response");

            for (int i = 0; i < workers.length(); i++) {
                JSONObject worker = workers.getJSONObject(i);
                JSONArray specialty = worker.getJSONArray("specialty");
                for (int j = 0; j < specialty.length(); j++) {
                    JSONObject specialtyId = specialty.getJSONObject(j);

                    contentValues.put("name", worker.getString("f_name"));
                    contentValues.put("surname", worker.getString("l_name"));
                    contentValues.put("birthday", worker.getString("birthday"));
                    contentValues.put("avatar", worker.getString("avatr_url"));
                    contentValues.put("specialty_id", specialtyId.getString("specialty_id"));
                    contentValues.put("specialty", specialtyId.getString("name"));

                    db.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
