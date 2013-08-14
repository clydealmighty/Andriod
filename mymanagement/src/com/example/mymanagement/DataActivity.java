package com.example.mymanagement;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;

public class DataActivity extends Activity {

	String[] uname=new String[10];
	String[] rows=new String[10];
	ImageView picture;
	File destination;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		
		String symbols = "";

		 SharedPreferences sharedPreferences =  getSharedPreferences("Mypref", 0);
		  symbols = sharedPreferences.getString("uname", "");
		  
		   rows=symbols.split("\\|");
		   String cname;
		   Intent intent = getIntent();
			cname = intent.getStringExtra("name");
			 for (int i = 0; i < rows.length; i++) {
				 if (cname.equals(rows[i].substring(0, rows[i].indexOf("^")).toString()))	
				 {
					 EditText eusername = (EditText) findViewById(R.id.editText1);
	    			 EditText elocation=(EditText) findViewById(R.id.editText2);
	    			 EditText etype=(EditText) findViewById(R.id.editText3);
	    			 picture = (ImageView) findViewById(R.id.ImageView02);
					uname=rows[i].split("\\^");
					
					eusername.setText(uname[0].toString());
					elocation.setText(uname[1].toString());
					etype.setText(uname[2].toString());
					
					if (!uname[3].toString().equals(""))
					{
						 destination = new File(uname[3].toString());
						FileInputStream in = null;
						try {
							in = new FileInputStream(destination);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                BitmapFactory.Options options = new BitmapFactory.Options();
		              
		             
		                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
		              
		                picture.setImageBitmap(bmp);
					}
				 }
				 
				 }

		 /*  for (int i = 0; i < rows.length; i++) {
			 if (cname.equals(rows[i].substring(0, rows[i].indexOf("^")).toString()))	
			 {

    			
			if (!uname[3].toString().equals(""))
				{
					 destination = new File(uname[3].toString());
					FileInputStream in = null;
					try {
						in = new FileInputStream(destination);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                BitmapFactory.Options options = new BitmapFactory.Options();
	              
	             
	                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
	              
	                picture.setImageBitmap(bmp);
				}
			 }
		   }*/
		     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_data, menu);
		return true;
	}

}
