package com.traning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.traning.models.ticket;

import java.util.ArrayList;

public class TicketDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TicketsDataBase";

    public static final String TABLE_NAME = "Tickets";

    public static final String userIdColumn = "userID";
    public static final String FlightCompanyColumn = "FlightCompany";
    public static final String PriceColumn = "Price";
    public static final String FromCodeColumn = "FromCode";
    public static final String ToCodeColumn = "ToCode";
    public static final String DepartureTakeoffColumn = "DepartureTakeoff";
    public static final String DepartureLandingColumn = "DepartureLanding";
    public static final String ReturnTakeoffColumn = "ReturnTakeoff";
    public static final String ReturnLandingColumn = "ReturnLanding";

    public TicketDatabase(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " + userIdColumn + " TEXT , " + FlightCompanyColumn + " TEXT , " + PriceColumn + " TEXT , " + FromCodeColumn + " TEXT , " + ToCodeColumn + " TEXT , " + DepartureTakeoffColumn + " TEXT , " + DepartureLandingColumn + " TEXT , " + ReturnTakeoffColumn + " TEXT , " + ReturnLandingColumn + " TEXT );";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + " ;");
        onCreate(db);
    }

    public boolean bookTicket(String id, ticket newTicket) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(userIdColumn, id);
        contentValues.put(FromCodeColumn, newTicket.getFromAirportCode());
        contentValues.put(ToCodeColumn, newTicket.getToAirportCode());
        contentValues.put(FlightCompanyColumn, newTicket.getFlightCompany());
        contentValues.put(DepartureTakeoffColumn, newTicket.getOutboundDepartureTime());
        contentValues.put(DepartureLandingColumn, newTicket.getDepartureLandingTime());
        contentValues.put(ReturnTakeoffColumn, newTicket.getOutboundReturnTime());
        contentValues.put(ReturnLandingColumn, newTicket.getReturnLandingTime());
        contentValues.put(PriceColumn, newTicket.getPrice());

        int result;
        result = (int) db.insert(TABLE_NAME, null, contentValues);
        return result != -1;

    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = null;
        if (db != null) {
            result = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            if (result != null) {
                return result;
            }
            else
                return null;
        }
        return null;
    }

}

