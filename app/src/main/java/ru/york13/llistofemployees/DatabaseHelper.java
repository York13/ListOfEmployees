package ru.york13.llistofemployees;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "employeesDB";
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "employees";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_AVATAR = "avatar";
    private static final String COLUMN_SPECIALTY_ID = "specialty_id";
    private static final String COLUMN_SPECIALTY = "specialty";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("myLog", "Создали БД");
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_BIRTHDAY + " TEXT, "
        + COLUMN_AVATAR + " TEXT, " + COLUMN_SPECIALTY_ID + " INTEGER, " + COLUMN_SPECIALTY + " TEXT"
        + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData() {
        Log.d("myLog", "Пытаемся заполнить БД");
        SQLiteDatabase db = this.getWritableDatabase();

        ParceTask parceTask = new ParceTask(db);
        parceTask.execute();

        db.close();
    }

    public void deleteAll() {
        Log.d("myLog", "Удалили таблицу");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

    public int getPersonsCount() {
        Log.d("myLog", "Вернули количество строк");
        String countQuery = "SELECT  * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}
