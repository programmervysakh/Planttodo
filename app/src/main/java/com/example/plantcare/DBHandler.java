package com.example.plantcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "plantdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "myplants";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String DATE_COL = "date";

    // below variable for our course description column.
    private static final String WATER_FREQUENCY_COL = "water_frequency";

    // below variable is for our course tracks column.
    private static final String WATER_AMOUNT_COL = "water_amount";

    // below variable for our course description column.
    private static final String FEED_FREQ_COL = "feed_frequency";

    // below variable is for our course tracks column.
    private static final String FEED_AMOUNT_COL = "feed_amount";

    private static final String STATUS_COL = "status";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_COL + " TEXT," + DATE_COL + " TEXT," + WATER_FREQUENCY_COL + " TEXT," + WATER_AMOUNT_COL + " TEXT," + FEED_FREQ_COL + " TEXT," + FEED_AMOUNT_COL + " TEXT," + STATUS_COL + " TEXT) ";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewPlant(String plantName, String currentDate, String waterFrequency, String waterAmount, String feedFrequency, String feedAmount, String status) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, plantName);
        values.put(DATE_COL, currentDate);
        values.put(WATER_FREQUENCY_COL, waterFrequency);
        values.put(WATER_AMOUNT_COL, waterAmount);
        values.put(FEED_FREQ_COL, feedFrequency);
        values.put(FEED_AMOUNT_COL, feedAmount);
        values.put(STATUS_COL, status);


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
//    public ArrayList<PlantModal> readPlants() {
//        // on below line we are creating a
//        // database for reading our database.
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // on below line we are creating a cursor with query to read data from database.
//        Cursor cursorPlant = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//
//        // on below line we are creating a new array list.
//        ArrayList<PlantModal> plantModalArrayList = new ArrayList<>();
//
//        // moving our cursor to first position.
//        if (cursorPlant.moveToFirst()) {
//            do {
//                // on below line we are adding the data from cursor to our array list.
//                plantModalArrayList.add(new PlantModal(cursorPlant.getString(1),
//                        cursorPlant.getString(2),
//                        cursorPlant.getString(3),
//                        cursorPlant.getString(4),
//                        cursorPlant.getString(5),
//                        cursorPlant.getString(6),
//                        cursorPlant.getString(7)));
//            } while (cursorPlant.moveToNext());
//            // moving our cursor to next.
//        }
//        // at last closing our cursor
//        // and returning our array list.
//        cursorPlant.close();
//        return plantModalArrayList;
//    }

    public ArrayList<PlantModal> readPlants() {
        String currentDate;
        Calendar calendar;
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = dateFormat.format(calendar.getTime());
        // on below line we are creating a
        // database for reading our database.
        String query = "SELECT DISTINCT * FROM myplants WHERE date='" + currentDate + "' ORDER BY status DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorPlant = db.rawQuery(query, null);

        // on below line we are creating a new array list.
        ArrayList<PlantModal> plantModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorPlant.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                plantModalArrayList.add(new PlantModal(cursorPlant.getString(1),
                        cursorPlant.getString(2),
                        cursorPlant.getString(3),
                        cursorPlant.getString(4),
                        cursorPlant.getString(5),
                        cursorPlant.getString(6),
                        cursorPlant.getString(7)));
            } while (cursorPlant.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.

//        for(int i =0;i<30;i++) {
//            Log.i("MSHJK", cursorPlant.getString(2));
//        }

        cursorPlant.close();
        return plantModalArrayList;
    }

    public void updatePlants(String name, String date, String status) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(DATE_COL, date);
        values.put(STATUS_COL, status);

        Calendar calendar;
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(calendar.getTime());
        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME,
                values,
                NAME_COL + " = ? AND " + DATE_COL + " = ?",
                new String[]{name, currentDate});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

