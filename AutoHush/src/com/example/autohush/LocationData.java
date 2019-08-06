package com.example.autohush;

public class LocationData {

	public double latitude;
	public double longitude;
	public String place;
	
	LocationData(double lat, double lng, String place)
	{
		this.latitude = lat;
		this.longitude = lng;
		this.place = place;
	}
	
	public double getLat()
	{
		return latitude;
	}
	
	public double getLong()
	{
		return longitude;
	}
	
	public String getPlace()
	{
		return place;	
	}
	
	public void setLat(double d)
	{
		this.latitude = d;
	}
	
	public void setLong(double d)
	{
		this.longitude = d;
	}
	
	public void setPlace(String place)
	{
		this.place = place;
	}
	
}
