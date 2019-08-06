package info.androidhive.tabsswipe.adapter;

import java.util.ArrayList;

import com.example.autohush.Activity2Activity;
import com.example.autohush.DatabaseActivity;
import com.example.autohush.LocationData;
import com.example.autohush.LocationService;
import com.example.autohush.MainActivity;
import com.example.autohush.MyAdapter;
import com.example.autohush.R;
import com.example.autohush.R.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class SavedLoc_Fragment extends Fragment{
	
	double Lat;
	double Long;
	DatabaseActivity dbManager;
	LocationService service;
	  private RecyclerView mRecyclerView;
	    private RecyclerView.Adapter mAdapter;
	    private RecyclerView.LayoutManager mLayoutManager;
	    ArrayList<LocationData> locs;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_saved_loc, container, false);
		
		
		//Context context = (Context)SavedLoc_Fragment.this.getActivity();
		//context.startService(new Intent(context, LocationService.class));
		//MainActivity act = new MainActivity();
		//act.displayCountOfLocations();
		
		
		//Intent intent = getActivity().getIntent();
		
		Log.d("test", "Saved Loc fragment");
		
	    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        Log.d("test", "In saved loc fragment");
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DatabaseActivity DA = new DatabaseActivity(this.getContext());
        Log.d("test", "Checking get all loc");
        if(DatabaseActivity.datasource.getAllLocations()!=null)
        { Log.d("test", "not null");
        locs = DatabaseActivity.datasource.getAllLocations();}
        
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(locs);
        mRecyclerView.setAdapter(mAdapter);
		
		
		return rootView;
	}
	
}
