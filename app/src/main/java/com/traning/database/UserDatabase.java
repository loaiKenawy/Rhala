package com.traning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.traning.models.User;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String TAG = "UserDatabase";
    private static final String databaseName = "USERS_Database";

    public static final String tableName = "USERS";

    public static final String phoneColumn = "Phone";
    public static final String firstNameColumn = "FirstName";
    public static final String lastNameColumn = "LastName";
    public static final String emailColumn = "Email";
    public static final String passwordColumn = "Password";

    public UserDatabase(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + tableName + "( " + phoneColumn + " TEXT PRIMARY KEY ," + firstNameColumn + " TEXT , " + lastNameColumn + " TEXT , " + emailColumn + " TEXT , " + passwordColumn + " TEXT);";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName + ";");
        onCreate(db);
    }

    public boolean registerNewUser(User newUser) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(phoneColumn, newUser.getPhone());
        contentValues.put(firstNameColumn, newUser.getFirstName());
        contentValues.put(lastNameColumn, newUser.getLastName());
        contentValues.put(emailColumn, newUser.getEmail());
        contentValues.put(passwordColumn, newUser.getPassword());
        int result;
        result = (int) db.insert(tableName, null, contentValues);
        return result != -1;
    }

    public User login(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result;
        User user;
        if (db != null) {
            result = db.rawQuery("SELECT * FROM "+tableName+";", null);
            String phone = null, fName = null, lName = null, password = null, email = null;
            if (result.getCount() == 0) {
                Log.d(TAG, "Data --- >>>>" + "NULL !!");
                return null;
            }
            while (result.moveToNext()) {
                phone = result.getString(
                        result.getColumnIndexOrThrow(phoneColumn));
                fName = result.getString(
                        result.getColumnIndexOrThrow(firstNameColumn));
                lName = result.getString(
                        result.getColumnIndexOrThrow(lastNameColumn));
                email = result.getString(
                        result.getColumnIndexOrThrow(emailColumn));
                password = result.getString(
                        result.getColumnIndexOrThrow(passwordColumn));
                if(phone.equals(id)){
                    user = new User(phone, email, fName, lName, password);
                    return user;
                }

            }
            return null;
        } else {
            return null;
        }

    }
}
