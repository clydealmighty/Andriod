package com.javapapers.android.sqlitestorageoption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.javapapers.android.sqlitestorageoption.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;



public class NewAnimal extends Activity{
	private static final int PICK_FROM_CAMERA = 100;
	EditText animalName;
	EditText location;
	EditText city;
	EditText Type;
	EditText descrption;
	File destination;
	String name111="";
	ImageView picture;
	DBController controller = new DBController(this);
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_new_animal);
	        animalName = (EditText) findViewById(R.id.animalName);
	        location=(EditText)findViewById(R.id.editText2);
	       Type=(EditText)findViewById(R.id.editText3);
	       city=(EditText)findViewById(R.id.descr);
	       descrption=(EditText)findViewById(R.id.editText3);
	       picture = (ImageView) findViewById(R.id.ImageView02);
	       gotonext1();
	    
	    
	    }
	public void addNewAnimal(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("animalName", animalName.getText().toString());
		queryValues.put("location", location.getText().toString());
		queryValues.put("Typess", Type.getText().toString());
		queryValues.put("ImageName", firstimage);
		queryValues.put("city", city.getText().toString());
		queryValues.put("descr", descrption.getText().toString());
		controller.insertAnimal(queryValues);
		this.callHomeActivity(view);
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}	
	
	
	 String imagePath;
	    String firstimage="";
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	
	       if (requestCode == PICK_FROM_CAMERA) {
	            try {
	            	FileInputStream in = new FileInputStream(destination);
	                BitmapFactory.Options options = new BitmapFactory.Options();
	               	options.inSampleSize = 5;
	               	options.inPurgeable = true;
	               	options.inInputShareable = true;
	                imagePath = destination.getAbsolutePath();
	             
	                firstimage=imagePath;
	                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
	              
	                picture.setImageBitmap(bmp);
	        	
	              
	           	            
	        }
	            catch (FileNotFoundException e) {
	                e.printStackTrace();
	            }

	        }
	 
	      
	    }
	
	 public void gotonext1()
	    {
	    Button go;	
		 go = (Button) findViewById(R.id.button1);
	    	
	    	go.setOnClickListener(new OnClickListener() {

	    		@Override
	    		public void onClick(View arg0) {
	     
	    		
	    		
	    			name111="";
	    			
	    			  name111=android.text.format.DateFormat.format("yyyyMMdd", new java.util.Date()).toString() + "_" + android.text.format.DateFormat.format("hhmmss", new java.util.Date()).toString();
	    	  		     destination = new File(Environment.getExternalStorageDirectory(), name111 + ".jpg");
	    	         	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    	              cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
	    	              startActivityForResult(cameraIntent, PICK_FROM_CAMERA);
	    				    			
	
	                
	    			 } 
	    		

	    	});
	    }
	

}
