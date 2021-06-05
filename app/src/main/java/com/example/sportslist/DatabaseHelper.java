package com.example.sportslist;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public class DatabaseHelper
        extends SQLiteOpenHelper {

    // The Android's default system path
    // of your application database.
    private static String DB_PATH = "";
    private static String DB_NAME = "SPORT_TABLE.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;

    // Table name in the database.
    public static final String
            SPORT_TABLE
            = "SPORT_TABLE";

    /**
     * Constructor
     * Takes and keeps a reference of
     * the passed context in order
     * to access the application assets and resources. */
    public DatabaseHelper(Context context)
    {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME)
                .toString();
    }

    // Creates an empty database
    // on the system and rewrites it
    // with your own database.
    public void createDataBase()
            throws IOException
    {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        }
        else {
            // By calling this method and
            // the empty database will be
            // created into the default system
            // path of your application
            // so we are gonna be able
            // to overwrite that database
            // with our database.
            this.getWritableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error(
                        "Error copying database");
            }
        }
    }
    // Check if the database already exist
    // to avoid re-copying the file each
    // time you open the application
    // return true if it exists
    // false if it doesn't.
    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB
                    = SQLiteDatabase
                    .openDatabase(
                            myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {

            // database doesn't exist yet.
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Copies your database from your
     * local assets-folder to the just
     * created empty database in the
     * system folder, from where it
     * can be accessed and handled.
     * This is done by transferring bytestream.
     * */
    private void copyDataBase()
            throws IOException
    {
        // Open your local db as the input stream
        InputStream myInput
                = myContext.getAssets()
                .open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput
                = new FileOutputStream(outFileName);

        // transfer bytes from the
        // inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase()
            throws SQLException
    {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase
                .openDatabase(
                        myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // It is an abstract method
        // but we define our own method here.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion)
    {
        // It is an abstract method which is
        // used to perform different task
        // based on the version of database.
    }

    // This method is used to get the
    // all exercise from the database.
    public LinkedList<Exercise> getAllExercise(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        // query help us to return all data
        // the present in the SPORT_TABLE table.
        String query = "SELECT * FROM " + SPORT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManArmsBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Beginner' AND TYPE like 'Arms'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManArmsMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Medium' AND TYPE like 'Arms'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManArmsAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Advanced' AND TYPE like 'Arms'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanArmsAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Advanced' AND TYPE like 'Arms'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanArmsMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Medium' AND TYPE like 'Arms'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanArmsBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Beginner' AND TYPE like 'Arms'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }


    public LinkedList<Exercise> getManLegsBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Beginner' AND TYPE like 'Legs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManLegsMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Medium' AND TYPE like 'Legs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManLegsAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Advanced' AND TYPE like 'Legs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanLegsAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Advanced' AND TYPE like 'Legs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanLegsMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Medium' AND TYPE like 'Legs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanLegsBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Beginner' AND TYPE like 'Legs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManAbsBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Beginner' AND TYPE like 'Abs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManAbsMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Medium' AND TYPE like 'Abs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManAbsAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Advanced' AND TYPE like 'Abs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanAbsAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Advanced' AND TYPE like 'Abs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanAbsMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Medium' AND TYPE like 'Abs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanAbsBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Beginner' AND TYPE like 'Abs'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManFullBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Beginner' AND TYPE like 'full body'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManFullMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Medium' AND TYPE like 'full body'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getManFullAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Men' AND COMPLAXITY like 'Advanced' AND TYPE like 'full body'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanFullAdvanced(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Advanced' AND TYPE like 'full body'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanFullMedium(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Medium' AND TYPE like 'full body'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    public LinkedList<Exercise> getWomanFullBeginner(
            Activity activity)
    {
        sqLiteOpenHelper
                = new DatabaseHelper(activity);
        SQLiteDatabase db
                = sqLiteOpenHelper
                .getReadableDatabase();

        LinkedList<Exercise> list
                = new LinkedList<>();

        String query = "SELECT * FROM SPORT_TABLE WHERE GENDER like 'Women' AND COMPLAXITY like 'Beginner' AND TYPE like 'full body'";
        Cursor cursor = db.rawQuery(query, null);

        createAList(list, cursor);

        cursor.close();
        db.close();
        return list;
    }

    private void createAList(LinkedList<Exercise> list, Cursor cursor) {
        if (cursor.moveToFirst()) {
            do {
                String type = cursor.getString(0);
                String complexity = cursor.getString(1);
                String gender = cursor.getString(2);
                String exercise = cursor.getString(3);
                int time = cursor.getInt(4);
                String description = cursor.getString(5);
                Exercise exer = new Exercise(type, complexity, gender, exercise, time, description, null);
                list.add(exer);
            } while (cursor.moveToNext());
        }
    }


}