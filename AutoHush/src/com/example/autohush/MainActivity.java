package com.example.autohush;


import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;





public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	


	private ImageButton ChooseLocation;
	private ImageButton CurrentLocation;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	
	private ActionBar actionBar;  

	private String[] tabs = { "New Location", "Saved Locations" };

	private ImageButton Vibration;
	private ImageButton Wifi;
	private ImageButton SendMessage;
	private ImageButton ToBeDecided;
	int flagForSavedLoc = 0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		//LocationService locservice = new LocationService();
		// RelativeLayout rl = (RelativeLayout) findViewById(R.id.rel);
		/*RelativeLayout rl = (RelativeLayout) findViewById(R.id.my_relative_layout);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(30, 40);
		params.leftMargin = 50;
		params.topMargin = 60;
		rl.addView(Vibration, params);*/
		String TAG = "MainActivity";
		
		
		displayCountOfLocations();
		
		
		
		actionBar =  getActionBar();  
		actionBar.setTitle(Html.fromHtml("<font  color='#ffffff'>Autohush</font>"));
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		 

		

		
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		
		
			Intent intent = this.getIntent();
			if(intent!= null)
			{
				Log.d(TAG, "Received intent");
				//String msg = intent.getStringExtra("id");
				int x = intent.getIntExtra("id1", 0);
				if(x == 1)
					{
					Toast.makeText(this, "Vibration mode active", Toast.LENGTH_LONG).show();
					
					viewPager.setCurrentItem(1);
					}
				flagForSavedLoc=1;
				//Lat = intent.getDoubleExtra("id2", 10);
				//Long = intent.getDoubleExtra("id3", 10);
				TextView tv3 = (TextView) findViewById(R.id.NoOfLocations);
				//int No_of_locations = DatabaseActivity.datasource.getRowCount();
				//tv3.setText(No_of_locations + "active");
				
			}

			
		
		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});



	/*	
		//TextView auto = (TextView) findViewById(R.id.textView2);
		//ChooseLocation = (ImageButton) findViewById(R.id.ChooseLoc_button);
		//CurrentLocation = (ImageButton) findViewById(R.id.CurrentLoc_button);
         
		LayoutParams layoutParams=new LayoutParams(500,150);
		layoutParams.leftMargin = 550;
		//layoutParams.rightMargin = 50;
		layoutParams.topMargin = 1170;
		ChooseLocation.setLayoutParams(layoutParams);
		
		LayoutParams layoutParams2=new LayoutParams(500,150);
		layoutParams2.leftMargin = 25 ;
		//layoutParams2.rightMargin = 25;
		layoutParams2.topMargin = 1170; 
		CurrentLocation.setLayoutParams(layoutParams2);*/
		
	/*	int dest = 1;
	      if (auto.getAlpha() > 0) {
	        dest = 0;
	      }
		ObjectAnimator fade = ObjectAnimator.ofFloat(auto,
		          "alpha", 0, 1f);
		
		fade.setDuration(3000);
	      fade.start(); */
	      
		
		
		
	}}





	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// on tab selected
				// show respected fragment view
				viewPager.setCurrentItem(tab.getPosition());
	}



	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	public void displayCountOfLocations()
	{
		
		LocationsDataSource datasource = new LocationsDataSource(getBaseContext());
		datasource.open();
		int count = datasource.getRowCount();
		TextView tv = (TextView) findViewById(R.id.NoOfLocations);
		TextView tv2 = (TextView) findViewById(R.id.textView1);
		TextView tv3 = (TextView) findViewById(R.id.textView2);

		
		Typeface tf = Typeface.createFromAsset(this.getBaseContext().getAssets(),
		        "Fonts/Century_gothic.ttf");

		tv2.setTypeface(tf);
		tv.setTypeface(tf);
		tv3.setTypeface(tf);
		
		
		AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ; 
		//AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ; 
		tv.startAnimation(fadeIn);
		//tv.startAnimation(fadeOut);
		fadeIn.setDuration(2000);
		fadeIn.setFillAfter(true);
		//fadeOut.setDuration(1200);
		//fadeOut.setFillAfter(true);
		//fadeOut.setStartOffset(4200+fadeIn.getStartOffset());
	      tv.setText(count+"");
	      tv3.setText(" active");
			tv2.setText("locations");
		datasource.close();

		
	}

	
	
		
	
	

		
	
	
	


	
}