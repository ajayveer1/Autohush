package com.example.autohush;

import java.io.IOException;
import java.util.List;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity implements OnItemClickListener {

	Geocoder geocoder ;
    List<Address> addresses = null;
    Address address;
	MarkerOptions markerOptions;
	LatLng latLng;
	
	GoogleMap map;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);

	    AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.SearchLocation);
	    autoCompView.setAdapter(new PlacesAutoCompleteAdaptereturnAddress(this, R.layout.list_item));


	    
	     // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
      if(status!=ConnectionResult.SUCCESS)
        { // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        }
   
   else 
        { // Google Play Services are available
 
            // Getting reference to the SupportMapFragment of activity_main.xml
           // SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        	
        	map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        	
	           map.getUiSettings().setZoomControlsEnabled(true); // true to enable

        	

        	
        }
 	    autoCompView.setOnItemClickListener(this);

	   
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String str = (String) adapterView.getItemAtPosition(position);
		Toast toast = Toast.makeText(this,"Click on the marker to select this location", Toast.LENGTH_LONG);
	    
		    LinearLayout toastLayout = (LinearLayout) toast.getView();
		    TextView toastTV = (TextView) toastLayout.getChildAt(0);
		    toastTV.setTextSize(16);
		    toast.show();


	        
			geocoder = new Geocoder(getBaseContext());

				 try {
					 
					addresses = geocoder.getFromLocationName(str,1);
					while (addresses.size()==0) {
				        addresses = geocoder.getFromLocationName(str, 1);
				    }
					
					if (addresses.size()>0) {
				        Address address = addresses.get(0);
			        	latLng = new LatLng(address.getLatitude(), address.getLongitude());
			        	
			            markerOptions = new MarkerOptions();
	                    markerOptions.position(latLng);
	                    markerOptions.title(str);
	     
	                    
	                    map.addMarker(markerOptions);
                 
	                  //map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
	          	// map.animateCamera(CameraUpdateFactory.zoomTo(5), 4000, null);
	                  
	                  map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,(float) 14));//Animates camera and zooms to preferred state on the user's current location.



			        	
			        	
			        	
			        	

				    }
				} catch (Exception e) {
				    System.out.print(e.getMessage());
				}
					
					
					
					
				//} catch (IOException e) {
					// TODO Auto-generated catch block
					
				//	e.printStackTrace();
				//}
				 
		//		 if(addresses==null || addresses.size() == 0){
		//                Toast.makeText(this, "No Location found", Toast.LENGTH_SHORT).show();
		          
	    //    map.clear();


	        
	        
	   /*     if(addresses.size() > 0)
            {

	        	 address = (Address) addresses.get(0);
	        	
	        	latLng = new LatLng(address.getLatitude(), address.getLongitude());

	        	
	        	//String addressText = String.format("%s, %s",
	              //      address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	              //      address.getCountryName());
	     
	                    markerOptions = new MarkerOptions();
	                    markerOptions.position(latLng);
	                    markerOptions.title(str);
	     
	                    
	                    map.addMarker(markerOptions);
                 
	                  map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
	          	    map.animateCamera(CameraUpdateFactory.zoomBy(10), 2000, null);


                   
            }
            else
            {
                    AlertDialog.Builder adb = new AlertDialog.Builder(this);
                    adb.setTitle("Autohush says");
                    adb.setMessage("Please Provide the Proper Place");
                    adb.setPositiveButton("Close",null);
                    adb.show();
            }
	        */
	        
	        
	        

	        
	}


	
	}
	


