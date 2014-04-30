package com.example.bd30jours_git;

/**
 * Created by Dimitri on 08/01/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bandes";

    // Contacts table name
    private static final String TABLE_FAVORIS = "planches";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITRE = "titre";
    private static final String KEY_DESCRIPTION="description";
    private static final String KEY_RATING="rate";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORIS_TABLE = "CREATE TABLE " + TABLE_FAVORIS + "("
                + KEY_ID+" TEXT"+")";
        db.execSQL(CREATE_FAVORIS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORIS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addFavori(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id); // Contact Name


        // Inserting Row
        db.insert(TABLE_FAVORIS, null, values);
    }

    // Getting single contact


    // Getting All Contacts


    // Updating single contact


    // Deleting single contact


    // Getting contacts Count
    public int getFavorisCount() {
        int count=0;
        String countQuery = "SELECT  distinct id FROM " + TABLE_FAVORIS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count=cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public List<Integer> getAllFavoris() {
        List<Integer> liste_favoris = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT distinct id FROM " + TABLE_FAVORIS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                liste_favoris.add(Integer.parseInt( cursor.getString(0)));
            } while (cursor.moveToNext());
        }

        // return contact list
        return liste_favoris;
    }

}