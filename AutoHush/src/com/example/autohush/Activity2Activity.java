package com.example.autohush;


import java.text.DateFormat;
import java.util.Date;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
//import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2Activity extends FragmentActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {
		
		
		GoogleMap map;
		static Activity2Activity instanceOfAct2;
		EditText searchLoc;
		LocationManager locationManager;
		LatLng currentPos;
		 LocationListener locationListener;
		 String provider;
		 GPSTracker tracker;
		 FusedLocationProviderApi fusedLocationProviderAPI = LocationServices.FusedLocationApi;
		 GoogleApiClient mGoogleApiClient;
		 private static final String TAG = "LocationActivity";
		    private static final long INTERVAL = 1000 * 10;
		    private static final long FASTEST_INTERVAL = 1000 * 5;
		    Location mCurrentLocation;
		    String mLastUpdateTime;
		    LocationRequest mLocationRequest;
		   static public double lat;
		   static public double lng;

		 
		 
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity2);
			instanceOfAct2 = this;
			
			if (!isGooglePlayServicesAvailable()) {
	            finish();
	        }
			
	        createLocationRequest();

	        
			mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build();
            Log.d(TAG, "GPC made");

			updateUI();
			
		      // Getting Google Play availability status
	        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
	 
	        // Showing status
	      if(status!=ConnectionResult.SUCCESS)
	        { // Google Play Services are not available
	    	  
	            int requestCode = 10;
	            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	            dialog.show();
	 
	        }
	   
	   
	         // Google Play Services are available
	 
	            // Getting reference to the SupportMapFragment of activity_main.xml
	           // SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
	        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	        	
	        	map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	        	
	            // Getting GoogleMap object from the fragment
	           // googleMap = fm.getMap();
	 
	            // Enabling MyLocation Layer of Google Map
	            map.setMyLocationEnabled(true);
	 
	            // Getting LocationManager object from System Service LOCATION_SERVICE
	            //  locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	 
	            // Creating a criteria object to retrieve provider
	          //  Criteria criteria = new Criteria();
	 
	            // Getting the name of the best provider
	          //   provider = locationManager.getBestProvider(criteria, true);
	 
	            // Getting Current Location
	          //  Location location = locationManager.getLastKnownLocation(provider);
	 
	            
	            /* locationListener = new LocationListener()
	            {
	                 onLocationChanged(Location location);
	                
	              if(location!=null)
	              {
	                 //PLACE THE INITIAL MARKER              
	                 drawMarker(location);
	              }
	              locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);
	          }*/
	            tracker = new GPSTracker(this);
	           //Location currentlocation = tracker.getLocation();
		           Location currentlocation = mCurrentLocation;
		         //drawMarker(mCurrentLocation);

	            
	          /* if(currentlocation!=null){
	        	   
	        	   System.out.println("Provider " + provider + " has been selected.");
	                LocationChanged(currentlocation);
	                
	            }*/
	          //  locationManager.requestLocationUpdates(provider, 20000, 0, this);
	        
	       
	           map.getUiSettings().setZoomControlsEnabled(true); // true to enable
			   
	           
	          
	        
	}

		//private void search(String loc){
			
			  //returns 3 addresses, choose 1 and plot that marker
			
		//}
		
void createLocationRequest() {
	        mLocationRequest = new LocationRequest();
	        mLocationRequest.setInterval(INTERVAL);
	        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
	        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	    }
private void drawMarker(Location location)
{
		    map.clear();
		    //currentPos = new LatLng(location.getLatitude(),location.getLongitude());
		    currentPos = new LatLng(lat, lng);

		    
		    map.addMarker(new MarkerOptions()
		    .position(currentPos)
		    .snippet("Lat:" + lat + "Lng:"+ lng)
		    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
		    .title("ME"));
		    
            Log.d(TAG, "Marker added");

		    moveToCurrentLocation(currentPos);
		    
		    
		    map.setOnMarkerClickListener(new OnMarkerClickListener()
            {

                @Override
                public boolean onMarkerClick(Marker arg0) {
             	   
             	   /*Intent intent = new Intent(getBaseContext(), DatabaseActivity.class);	//Check here
            	    //intent.putExtra("Lat", currentLocation.latitude);
            	    //intent.putExtra("Long",currentLocation.longitude);
            	    intent.putExtra("Lat", lat);
            	    intent.putExtra("Long", lng);


            	    startActivity(intent);*/
            	    
            	    
            	   Confirm_Feature_DialogBox cfdb = new Confirm_Feature_DialogBox(Activity2Activity.this);
    				cfdb.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    				//cfdb.getWindow().setLayout(1000, 1500);
    				cfdb.show();
            	    
            	    
            	    
                    return true;
                }
                 
                
            });

}
	

	public void LocationChanged(Location location)
	 {
	
		//drawMarker(location);
		//currentPos = new LatLng(location.getLatitude(), location.getLongitude());
		
		
		// TODO Auto-generated method stub
		//currentPos = new LatLng(location.getLatitude(), location.getLongitude());
		
		  
		//  map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos, 20));

		    //Zoom in, animating the camera.
		 // map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);   
		   
	}
	private void moveToCurrentLocation(LatLng currentLocation)
	{   
	   map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomIn());
	    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
	    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

	    
	    

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Connection failed");
	}
	protected void startLocationUpdates() {
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }
	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		 Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
	        startLocationUpdates();
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}

	

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }
	
	@Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }
	
	private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }
	
	
	void updateUI()
	{
		if (null != mCurrentLocation) {
             lat = mCurrentLocation.getLatitude();
             lng = mCurrentLocation.getLongitude();
		}
	}
	
	@Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged................." + lat );
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        drawMarker(mCurrentLocation);
    }
	
	protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

	@Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }
	public static Activity2Activity getInstance()
	{
		return instanceOfAct2;
	}
	/* Request updates at startup 
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }
	  
	  
	  Remove the locationlistener updates when Activity is paused 
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }*/

	  /*
	  
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Enabled new provider " + provider,
		        Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
		        Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		
	}*/
}
