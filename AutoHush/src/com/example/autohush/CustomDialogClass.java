package com.example.autohush;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogClass extends Dialog implements
android.view.View.OnClickListener {

public Activity c;
public Dialog d;
public Button yes, no;

public CustomDialogClass(Activity a) {
super(a);
// TODO Auto-generated constructor stub
this.c = a;
}

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.dialog_select_loc);

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
						  
						 Intent intent = new Intent( this.getContext() , Activity2Activity.class); 
						 //Intent intent = new Intent(this.getContext(), DatabaseActivity.class);
						 
						 this.getContext().startActivity(intent);
					  
					  	break;}
case R.id.btn_no:
					{
							dismiss();
							Intent intent = new Intent(this.getContext(), MapActivity.class); 
							this.getContext().startActivity(intent);
					
							break;}
	default:
						break;
}
							dismiss();
}
}