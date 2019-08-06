package com.example.autohush;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocationsDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_LAT, MySQLiteHelper.COLUMN_LONG, MySQLiteHelper.COLUMN_PLACE};
	static ArrayList<LocationData> locations ;
	
	public LocationsDataSource(Context context)
	{
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	//Record = Location
	public void createRecord(double Lat, double Long, String place)
	{
		
		
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_LAT, Lat);
		values.put(MySQLiteHelper.COLUMN_LONG, Long);
		values.put(MySQLiteHelper.COLUMN_PLACE, place);
		
		long insertId = database.insert(MySQLiteHelper.TABLE_LOCATIONS, null, values);
		
		//Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, MySQLiteHelper.selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		
	}
	
	public ArrayList<LocationData> getAllLocations()
	{
		ArrayList<LocationData> locations = new ArrayList<LocationData>();
		
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_LOCATIONS, allColumns, null, null, null, null, null);
		//Cursor cursor =database.rawQuery("select * from Locations where place = ? ;", new String[]{"You can do it"});

		Log.d("test", "select all query executed");
		Log.d("test", "no. of rows =" + cursor.getCount());
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast())
		{
			LocationData location = cursorToLocation(cursor);
			locations.add(location);
			cursor.moveToNext();
			
		}
		
		cursor.close();
		
		return locations;
		
	}
	
	public LocationData getLocation()
	{
		//Cursor cursor = database.query(MySQLite, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		
		Cursor cursor =database.rawQuery("select * from Locations where place = ? ;", new String[]{"You can do it"});
		cursor.moveToLast();
		LocationData location = cursorToLocation(cursor);
		cursor.close();
		return location;

	}
	
	private LocationData cursorToLocation(Cursor cursor)
	{
		
		/*Cursor x = null;
		x = database.rawQuery("select count(*) from Locations;", null);
		x.moveToFirst();*/

		//int y = x.getInt(0);
		//cursor.moveToLast();
		LocationData location = new LocationData(0,0,"You can do it");
		//location.setLat(y);

		location.setLat(cursor.getDouble(0));
		location.setLong(cursor.getDouble(1));
		location.setPlace(cursor.getString(2));
		return location;
	}
	

public int getRowCount()
{
	Cursor cursor =database.rawQuery("select * from Locations", null);
	int count = cursor.getCount();
	return count;

	
}

}
