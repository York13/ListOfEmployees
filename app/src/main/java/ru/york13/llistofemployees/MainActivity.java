package ru.york13.llistofemployees;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "myLog";
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        this.deleteDatabase("employeesDB");
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.addData();
        Log.d(TAG, "Количество строк = " + databaseHelper.getPersonsCount());

    }

//    private static class ParceTask extends AsyncTask<Void, Void, String> {
//
//        private WeakReference<MainActivity> activityReference;
//        DatabaseHelper databaseHelper;
//
//        ParceTask(MainActivity context, DatabaseHelper databaseHelper) {
//            activityReference = new WeakReference<>(context);
//            this.databaseHelper = databaseHelper;
//        }
//
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String resultJson = "";
//
//        @Override
//        protected String doInBackground(Void... params) {
//            try {
//                URL url = new URL("http://gitlab.65apps.com/65gb/static/raw/master/testTask.json");
//
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer stringBuffer = new StringBuffer();
//
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuffer.append(line);
//                }
//
//                resultJson = stringBuffer.toString();
//
//                urlConnection.disconnect();
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return resultJson;
//        }
//
//        @Override
//        protected void onPostExecute(String strJson) {
//            super.onPostExecute(strJson);
//            Log.d(TAG, "Вот наш strJson: " + strJson);
//
//            MainActivity activity = activityReference.get();
//            if (activity == null || activity.isFinishing()) return;
//
//            ContentValues contentValues = new ContentValues();
//
//            SQLiteDatabase database = databaseHelper.getWritableDatabase();
//
//            JSONObject jsonObject;
//
//            try {
//                jsonObject = new JSONObject(strJson);
//                JSONArray workers = jsonObject.getJSONArray("response");
//
//                JSONObject worker = workers.getJSONObject(0);
//                JSONArray specialty = worker.getJSONArray("specialty");
//                JSONObject specialtyId = specialty.getJSONObject(0);
//
//                String name = worker.getString("f_name");
//                String surname = worker.getString("l_name");
//                String birthday = worker.getString("birthday");
//                String avatar = worker.getString("avatr_url");
//                String specialtyIdUser = specialtyId.getString("specialty_id");
//                String post = specialtyId.getString("name");
//
//                contentValues.put("name", name);
//                contentValues.put("surname", surname);
//                contentValues.put("birthday", birthday);
//                contentValues.put("avatar", avatar);
//                contentValues.put("specialty_id", specialtyIdUser);
//                contentValues.put("specialty", post);
//
//                long insertID = database.insert(databaseHelper.DATABASE_TABLE, null, contentValues);
//
//                Log.d(TAG, "Имя: " + name);
//                Log.d(TAG, "Фамилия: " + surname);
//                Log.d(TAG, "День рождения: " + birthday);
//                Log.d(TAG, "Аватар: " + avatar);
//                Log.d(TAG, "ID должности: " + specialtyIdUser);
//                Log.d(TAG, "Должность: " + post);
//                Log.d(TAG, "ID вставленной строки: " + insertID);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            TextView textView = activity.findViewById(R.id.textView);
//            Cursor cursor = database.query(databaseHelper.DATABASE_TABLE, null, null,
//                    null, null, null, null);
//
//            if (cursor.moveToFirst()) {
//                do {
//                    String user = "ID = " + cursor.getInt(cursor.getColumnIndex("id")) +
//                            "\nИмя = " + cursor.getString(cursor.getColumnIndex("name")) +
//                            "\nФамилия = " + cursor.getString(cursor.getColumnIndex("surname")) +
//                            "\nДень рождения = " + cursor.getString(cursor.getColumnIndex("birthday")) +
//                            "\nАватарка = " + cursor.getString(cursor.getColumnIndex("avatar")) +
//                            "\nID специальности = " + cursor.getString(cursor.getColumnIndex("specialty_id")) +
//                            "\nСпециальность = " + cursor.getString(cursor.getColumnIndex("specialty"));
//                    textView.setText(user);
//                } while (cursor.moveToNext());
//            }
//
//            cursor.close();
//            databaseHelper.close();
//
//        }
//    }
}
