package com.example.autohush;

/*import com.google.android.gms.location.LocationListener;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;



public class LocationService extends Service 
{
       public static final String BROADCAST_ACTION = "Hello World";
       private static final int TWO_MINUTES = 1000 * 60 * 1;
       public LocationManager locationManager;
       public MyLocationListener listener;
       public Location previousBestLocation = null;
       public Location cbl=null;
       int Lat=0;
       int Long = 0;

       Intent intent;
       int counter = 0;

    @Override
    public void onCreate() 
    {
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);     
        Log.d("test", "service created");
    }

    @Override
    public void onStart(Intent intent, int startId) 
    {      
    	Log.d("test", "Service onStart");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();        
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, listener);
        
        LocationData loc = DatabaseActivity.datasource.getLocation();
	     if(loc!=null)
	     {  Lat = (int)loc.latitude;
	     	 Long = (int)loc.longitude;}
	     
	     
	     if(cbl!=null)
	     {
	    	 Log.d("test", "previous best loc is not null");
	     
			     if( (Lat == (int)cbl.getLatitude()) && (Long == (int)cbl.getLongitude()))
			    		 {
			    	 	Log.d("test", "current location matches with database");
				    	AudioManager audioManager = (AudioManager)getBaseContext().getSystemService(Context.AUDIO_SERVICE);
				    	
				    	audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE); 
				    	
			    	 
			    		 }
	     }
    }

    @Override
    public IBinder onBind(Intent intent) 
    {
        return null;
    }

    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
        // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        cbl = currentBestLocation;
        
        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }



/* Checks whether two providers are the same 
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
          return provider2 == null;
        }
        return provider1.equals(provider2);
    }



@Override
    public void onDestroy() {       
       // handler.removeCallbacks(sendUpdatesToUI);     
        super.onDestroy();
        Log.v("STOP_SERVICE", "DONE");
        locationManager.removeUpdates(listener);        
    }   

    public static Thread performOnBackgroundThread(final Runnable runnable) {
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {

                }
            }
        };
        t.start();
        return t;
    }




public class MyLocationListener implements  android.location.LocationListener
    {

        public void onLocationChanged(final Location loc)
        {
            Log.i("**************************************", "Location changed");
            if(isBetterLocation(loc, previousBestLocation)) {
                loc.getLatitude();
                loc.getLongitude();             
                intent.putExtra("Latitude", loc.getLatitude());
                intent.putExtra("Longitude", loc.getLongitude());     
                intent.putExtra("Provider", loc.getProvider());                 
                sendBroadcast(intent);          

            }                               
        }

        public void onProviderDisabled(String provider)
        {
            Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
        }


        public void onProviderEnabled(String provider)
        {
            Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }


        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }

    }
}








*/

















import java.text.DecimalFormat;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class LocationService extends Service {

	String Lat;
	String Long;
	GPSTracker tracker;
	LatLng currentPos;
	String provider;
	String x;
	String y;
	LocationData loc;
	
	
	@Override
	public void onCreate()
	{
		Toast.makeText(this, "Service created", Toast.LENGTH_LONG).show();
		Log.d("LocationService", "onCreate");
		
		createLocationRequest();
		
		tracker = new GPSTracker(this);
        Location currentLocation = tracker.getLocation();
	    currentPos = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());

	    
        DecimalFormat df = new DecimalFormat("#.000");
	     String x = df.format(currentPos.latitude);
       Log.d("test", "Decimal format of" +currentPos.latitude + ".....is" + x);
	     
	     x = df.format(currentPos.latitude);
	     y = df.format(currentPos.longitude);
	    
	    
        String y = df.format(currentPos.latitude);
	     //checking if reached a desired point
        
	     loc = DatabaseActivity.datasource.getLocation();
	     if(loc!=null)
	     {  Lat = df.format(loc.latitude);
	     Long = df.format(loc.longitude);}
	     
	    
	   
 
		
	}
	
	protected void createLocationRequest() {
	    LocationRequest mLocationRequest = new LocationRequest();
	    mLocationRequest.setInterval(10000);
	    mLocationRequest.setFastestInterval(5000);
	    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent2, int flags, int startid) 
	{
		Toast.makeText(this,  "Service started", Toast.LENGTH_LONG).show();
		Log.d("LocationService", "onStart");
		//int Lat = 0;
		//int Long = intent2.getIntExtra("Long", 0);
		
		 //Context context = getApplicationContext();
		    if(Lat == x && Long == y)
		    {
		    	Log.d("test", "current location matches with database");
		    	AudioManager audioManager = (AudioManager)getBaseContext().getSystemService(Context.AUDIO_SERVICE);
		    	
		    	audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE); 
		    	
		    }
		
		
		return START_NOT_STICKY;
		
	}



	
}
