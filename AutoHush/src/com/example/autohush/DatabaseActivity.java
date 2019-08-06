package com.example.autohush;

import java.util.List;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DatabaseActivity {
	
	static public LocationsDataSource datasource;
	static public double Lat;
	static public double Long;
	static LocationData loc;
	
	public DatabaseActivity(Context context)
	{
	
		
		//Intent intent = getIntent();
		// Lat = intent.getDoubleExtra("Lat", 0);
		// Long = intent.getDoubleExtra("Long", 0);
		
		Log.d("test", "DatabaseActivity constructor");

		Lat = Activity2Activity.lat;
		Long = Activity2Activity.lng;
		
		
		
	
		datasource = new LocationsDataSource(context);
		datasource.open();
		
		//List<LocationData> values = datasource.getAllLocations();
		
		// use the SimpleCursorAdapter to show the elements in a ListView
		//ArrayAdapter<LocationData> adapter = new ArrayAdapter<LocationData> (this, R.id.list, values);
		
		//setListAdapter(adapter);
		
	

	

	String Place= "You can do it";
	
	//TextView tv2 = (TextView) findViewById(R.id.tv2);
		//	tv2.setText(Double.toString(Lat));
			
	//TextView tv3 = (TextView) findViewById(R.id.tv3);
			//tv3.setText(Double.toString(Long));
	
	//save the new location record to the database
	
	datasource.createRecord(Lat, Long, Place);
	
	
	//adapter.notifyDataSetChanged();
	
	 loc = datasource.getLocation();
	
	//TextView tv = (TextView) findViewById(R.id.tv1);
	//tv.setText(Double.toString(loc.getLat()));
	
	/*Intent intent2= new Intent(this, LocationService.class);
	intent2.putExtra("Lat", loc.getLat());
	intent2.putExtra("Long", loc.getLong());
    startService(intent2);*/
	 checkLatLong();
    Log.d("test", "Latitude is" + loc.getLat() + "..... Longitude is" + loc.getLong());	
	}
	
	/*
	  	@Override
	protected void onResume()
	{
		datasource.open();
		super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		datasource.close();
		super.onPause();
	}*/
	void checkLatLong()
	{
		if(loc.getLat()!=0 && loc.getLong()!=0)
			Log.d("Test", "got back values from table");
	}
	
}
