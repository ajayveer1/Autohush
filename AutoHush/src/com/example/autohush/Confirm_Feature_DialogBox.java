package com.example.autohush;

import info.androidhive.tabsswipe.adapter.SavedLoc_Fragment;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Confirm_Feature_DialogBox extends Dialog implements
android.view.View.OnClickListener {

public Activity c;
public Dialog d;
public Button yes, no;

public Confirm_Feature_DialogBox(Activity a) {
super(a);
// TODO Auto-generated constructor stub
this.c = a;
}

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.activity_confirm_feature_dialog_box);


Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(),
        "Fonts/Century_gothic.ttf");
TextView tv = (TextView) findViewById(R.id.txt_dia);
tv.setTypeface(tf);




yes = (Button) findViewById(R.id.btn_yes);
no = (Button) findViewById(R.id.btn_no);
yes.setOnClickListener(this);
no.setOnClickListener(this);

}

@Override
public void onClick(View v) {
switch (v.getId()) {
case R.id.btn_yes:
					  {
						  dismiss();
						  
						 //Intent intent = new Intent(this.getContext(), DatabaseActivity.class);
						// intent.putExtra("From box", "hey");

						  DatabaseActivity DA = new DatabaseActivity(this.getContext());

						  Intent intent2 = new Intent(this.getContext(), LocationService.class);
						  this.getContext().startService(intent2);
						  
						 // Intent intent = new Intent(this.getContext(), List_Locations.class);
						 // this.getContext().startActivity(intent);
						 Intent intent = new Intent(this.getContext(), MainActivity.class);
						 intent.putExtra("id1", 1);
						 
						this.getContext().startActivity(intent);

					  
					  	break;}
case R.id.btn_no:
					{
							dismiss();
							
							 Intent intent = new Intent( this.getContext() , MainActivity.class); 
							 this.getContext().startActivity(intent);

					
							break;}
	default:
						break;
}
							dismiss();
}
}