package com.raivis.develops.lpd_personigais_budzeta_planotais;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by raivis on 18/05/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database name
    private static final String DATABASE_NAME = "usersInfo";
    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_OUTCOMES = "outcomes";
    private static final String TABLE_INCOMES = "incomes";
    // Users table columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    //Outcome table columns names
    private static final String KEY_OUTCOME_NAME = "outcome_name";
    private static final String KEY_OUTCOME_AMOUNT = "outcome_amount";
    private static final String KEY_OUTCOME_DATE = "outcome_date";
    private static final String KEY_OUTCOME_USER = "outcome_user_email";

    //Income table columns names
    private static final String KEY_INCOME_NAME = "income_name";
    private static final String KEY_INCOME_AMOUNT = "income_amount";
    private static final String KEY_INCOME_DATE = "income_date";
    private static final String KEY_INCOME_SOURCE = "income_source";
    private static final String KEY_INCOME_USER = "income_email";

    private final SQLiteDatabase db = this.getWritableDatabase();

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        Table for users
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT," + KEY_BIRTHDAY + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT" +")";
        db.execSQL(CREATE_USERS_TABLE);

//        Table for outcomes
        String CREATE_OUTCOME_TABLE = "CREATE TABLE " + TABLE_OUTCOMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_OUTCOME_NAME + " TEXT,"
                + KEY_OUTCOME_AMOUNT + " REAL," + KEY_OUTCOME_DATE + " TEXT,"
                + KEY_OUTCOME_USER + " TEXT " +")";
        db.execSQL(CREATE_OUTCOME_TABLE);

//        Table for incomes
        String CREATE_INCOME_TABLE = "CREATE TABLE " + TABLE_INCOMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INCOME_NAME + " TEXT,"
                + KEY_INCOME_AMOUNT + " REAL," + KEY_INCOME_DATE + " TEXT,"
                + KEY_INCOME_SOURCE + " TEXT," + KEY_INCOME_USER + " TEXT " +")";
        db.execSQL(CREATE_INCOME_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTCOMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOMES);
        // Creating tables again
        onCreate(db);
    }

    //Add new user
    public boolean addNewUser(User user)
    {
        ContentValues values = new ContentValues();

        String[] newUserInfo = user.getUserInfo();

        values.put(KEY_FIRST_NAME, newUserInfo[0]); // User first name
        values.put(KEY_LAST_NAME, newUserInfo[1]); //Users last name
        values.put(KEY_BIRTHDAY, newUserInfo[2]);
        values.put(KEY_EMAIL, newUserInfo[3]);
        values.put(KEY_PASSWORD, newUserInfo[4]);

        //insert value into DB
        //db.insert(TABLE_USERS, null, values);
        long rowInserted = db.insert(TABLE_USERS, null, values);
        if(rowInserted != -1) {
            return true;
        }
        else{
            return false;
        }
    }
    //Add new outcome
    public boolean addNewOutcome(Outcome outcome)
    {
        ContentValues values = new ContentValues();

        String[] newOutcomeInfo = outcome.getOutcomeInfo();

        values.put(KEY_OUTCOME_NAME, newOutcomeInfo[0]); // Outcome name and so on
        values.put(KEY_OUTCOME_AMOUNT, Double.parseDouble(newOutcomeInfo[1]));
        values.put(KEY_OUTCOME_DATE, newOutcomeInfo[2]);
        values.put(KEY_OUTCOME_USER, newOutcomeInfo[3]);

        //insert value into DB
        long rowInserted = db.insert(TABLE_OUTCOMES, null, values);
        //Check if successful
        if(rowInserted != -1) {
            return true;
        }
        else{
            return false;
        }
    }

    //Add new income
    public boolean addNewIncome(Income income)
    {
        ContentValues values = new ContentValues();

        String[] newIncomeInfo = income.getIncomeInfo();

        values.put(KEY_INCOME_NAME, newIncomeInfo[0]);
        values.put(KEY_INCOME_AMOUNT, Double.parseDouble(newIncomeInfo[1]));
        values.put(KEY_INCOME_DATE, newIncomeInfo[2]);
        values.put(KEY_INCOME_SOURCE, newIncomeInfo[3]);
        values.put(KEY_INCOME_USER, newIncomeInfo[4]);

        //insert value into DB
        long rowInserted = db.insert(TABLE_INCOMES, null, values);
        //Check if successful
        if(rowInserted != -1) {
            return true;
        }
        else{
            return false;
        }
    }

    //Check if user exist
    public boolean verification(String email, String pass) {

        //Cursor c = db.rawQuery("SELECT 1 FROM "+TABLE_USERS+" WHERE "+KEY_EMAIL+"=?",
        //        new String[] {email});
        Cursor c = db.rawQuery("SELECT 1 FROM "+TABLE_USERS+" WHERE "+KEY_EMAIL+"='" + email +
                "' AND " + KEY_PASSWORD +"='" + pass + "'", null);

        boolean exists = c.moveToFirst();
        c.close();

        return exists;
    }
}
