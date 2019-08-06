package com.example.autohush;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{

	
	public static final String TABLE_LOCATIONS = "Locations";
	public static final String COLUMN_LAT = "latitude";
	public static final String COLUMN_LONG = "longitude";
	public static final String COLUMN_PLACE = "place";
	
	private static final String DATABASE_NAME = "Location data";
	private static int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table Locations ( latitude double precision primary key, longitude double precision , place varchar(30)); ";
	
	public MySQLiteHelper(Context context)
	{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		database.execSQL(DATABASE_CREATE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version" + oldVersion + "to" + newVersion + "which will destroy all old data");
		db.execSQL("Drop table if exists Locations");
		onCreate(db);
		
	}
}
