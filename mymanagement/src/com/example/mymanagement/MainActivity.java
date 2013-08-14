package com.example.mymanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	final Context context = this;
	File destination;
	String name111="";
	ImageView picture;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button2);
		 picture = (ImageView) findViewById(R.id.ImageView02);
    	gotonext1();
    	gotonext();
    	button1.setOnClickListener(new OnClickListener() {

    		public void onClick(View arg0) {
     
    		
    			
    			
    			 EditText eusername = (EditText) findViewById(R.id.editText1);
    			 EditText elocation=(EditText) findViewById(R.id.editText2);
    			 EditText etype=(EditText) findViewById(R.id.editText3);
    			
    			 
    				    			
    			 if (eusername.getText().toString().equals("") || elocation.getText().toString().equals(""))
    			 {
    				  Toast.makeText(MainActivity.this,"User Name and Location are required",
    	  						Toast.LENGTH_SHORT).show();
    			 }
    			 else
    			 {
    				
    			 
    			
    		 SharedPreferences sharedPreferences =getSharedPreferences("Mypref", 0);
    		String strSavedMem1 = sharedPreferences.getString("uname", "");		 
    				 if (strSavedMem1.equals(""))
    				 {
    					 SavePreferences("uname", eusername.getText().toString().concat("^").concat(elocation.getText().toString()).concat("^").concat(etype.getText().toString()).concat("^").concat(firstimage));
    				 }
    				 else
    				 {
    					 String s="|";
    					 SavePreferences("uname",strSavedMem1.concat(s).concat(eusername.getText().toString()).concat("^").concat(elocation.getText().toString()).concat("^").concat(etype.getText().toString()).concat("^").concat(firstimage));
    				 }
    				
    				 Intent intent = new Intent(context, listdata.class);
	    			 startActivity(intent);  
    			 
    			 
                
    			 } 
    		}

    	});
    	
    	
	}
	
	
	 public void gotonext1()
	    {
	    Button go;	
		 go = (Button) findViewById(R.id.button1);
	    	
	    	go.setOnClickListener(new OnClickListener() {

	    		@Override
	    		public void onClick(View arg0) {
	     
	    		
	    			// Intent intent = new Intent(context, listdata.class);
	    			// startActivity(intent);  
	    			name111="";
	    			  name111=android.text.format.DateFormat.format("yyyyMMdd", new java.util.Date()).toString() + "_" + android.text.format.DateFormat.format("hhmmss", new java.util.Date()).toString();
	    	  		     destination = new File(Environment.getExternalStorageDirectory(), name111 + ".jpg");
	    	         	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    	              cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
	    	              startActivityForResult(cameraIntent, 2);
	    				    			
	
	                
	    			 } 
	    		

	    	});
	    }
	

	 
	 String imagePath;
	    String firstimage="";
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == 2) {
	            try {
	            	FileInputStream in = new FileInputStream(destination);
	                BitmapFactory.Options options = new BitmapFactory.Options();
	               // options.inSampleSize = 50;
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
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	 private void SavePreferences(String key, String value){
		    SharedPreferences sharedPreferences = getSharedPreferences("Mypref", 0);
		    SharedPreferences.Editor editor = sharedPreferences.edit();
		    editor.putString(key, value);
		    editor.commit();
		   }
	 
	 public void gotonext()
	    {
		 Button go;	
		 go = (Button) findViewById(R.id.button3);
		 go.setOnClickListener(new OnClickListener() {

	    		@Override
	    		public void onClick(View arg0) {
	     
	    		
	    			 Intent intent = new Intent(context, listdata.class);
	    			 startActivity(intent);  
	    	
	    			 
	    				    			
	
	                
	    			 } 
	    		

	    	});
	    }

}
