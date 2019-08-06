package info.androidhive.tabsswipe.adapter;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.autohush.CustomDialogClass;
import com.example.autohush.MainActivity;
import com.example.autohush.MapActivity;
import com.example.autohush.R;
import com.example.autohush.R.layout;


public class NewLoc_Fragment extends Fragment {
	
	private ImageButton Vibration;
	private ImageButton Wifi;
	private ImageButton SendMessage;
	private ImageButton ToBeDecided;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	 View rootView = inflater.inflate(R.layout.fragment_new_loc, container, false);
		

		Vibration=(ImageButton) rootView.findViewById(R.id.Vib);
		Wifi=(ImageButton) rootView.findViewById(R.id.wifi);
		
		Vibration.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	
	        	CustomDialogClass cdd = new CustomDialogClass(getActivity());
				cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				cdd.show();
	        	
	        }
	        });
		//Wifi.setOnClickListener( getActivity());
		
		
		return rootView;
	}
	
	
	/*public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if( v == Vibration)
		{
			
			CustomDialogClass cdd = new CustomDialogClass(getActivity());
			cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			cdd.show();
			//Intent intent = new Intent(MainActivity.this, Activity2Activity.class); 
			//startActivity(intent);
		}
		
		if( v == Wifi)
		{
			Intent intent = new Intent(getActivity(), MapActivity.class); 
			startActivity(intent);
		}
		
	}*/

}
