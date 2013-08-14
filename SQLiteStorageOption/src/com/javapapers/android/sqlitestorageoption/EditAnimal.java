package com.javapapers.android.sqlitestorageoption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;

import java.util.HashMap;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.javapapers.android.sqlitestorageoption.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class EditAnimal extends Activity{
	EditText animalName;
	EditText location;
	EditText Type;
	TextView txtimage;
	EditText city;
	File destination;
	EditText descrption;
	ImageView picture;
	DBController controller = new DBController(this);
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_edit_animal);
			animalName = (EditText) findViewById(R.id.animalName);
			 location=(EditText)findViewById(R.id.editText2);
			 Type=(EditText)findViewById(R.id.editText3);
			 descrption=(EditText)findViewById(R.id.descr);
			 txtimage=(TextView)findViewById(R.id.txtimagename);
			 picture = (ImageView) findViewById(R.id.ImageView02);
			 city=(EditText)findViewById(R.id.city);
			Intent objIntent = getIntent();
			String animalId = objIntent.getStringExtra("animalId");
			Log.d("Reading: ", "Reading all contacts..");
			HashMap<String, String> animalList = controller.getAnimalInfo(animalId);
			Log.d("animalName",animalList.get("animalName"));
			if(animalList.size()!=0) {
			animalName.setText(animalList.get("animalName"));
			location.setText(animalList.get("location"));
			Type.setText(animalList.get("Typess"));
			city.setText(animalList.get("city"));
			descrption.setText(animalList.get("descr"));
			//Toast.makeText(getApplicationContext(),animalList.get("ImageName"), Toast.LENGTH_SHORT).show();
			if (!animalList.get("ImageName").equals(""))
			{
				txtimage.setText(animalList.get("ImageName"));
				destination = new File(animalList.get("ImageName"));
				  try {
				  FileInputStream in;
				
					in = new FileInputStream(destination);
				
	                BitmapFactory.Options options = new BitmapFactory.Options();	              
	            	options.inSampleSize = 5;
	               	options.inPurgeable = true;
	               	options.inInputShareable = true;
	                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
	              
	                picture.setImageBitmap(bmp);
				  } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
			}
			}}
			
	    }
	public void editAnimal(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		animalName = (EditText) findViewById(R.id.animalName);
		 location=(EditText)findViewById(R.id.editText2);
		 Type=(EditText)findViewById(R.id.editText3);
		 txtimage=(TextView)findViewById(R.id.txtimagename);
		 city=(EditText)findViewById(R.id.city);
		 descrption=(EditText)findViewById(R.id.descr);
		Intent objIntent = getIntent();
		String animalId = objIntent.getStringExtra("animalId");
		queryValues.put("animalId", animalId);
		queryValues.put("animalName", animalName.getText().toString());
		queryValues.put("location", location.getText().toString());
		queryValues.put("Typess", Type.getText().toString());
		queryValues.put("ImageName", txtimage.getText().toString());
		queryValues.put("city", city.getText().toString());
		queryValues.put("descr", descrption.getText().toString());
		controller.updateAnimal(queryValues);
		this.callHomeActivity(view);
		
	}
	public void removeAnimal(View view) {
		Intent objIntent = getIntent();
		String animalId = objIntent.getStringExtra("animalId");
		controller.deleteAnimal(animalId);
		this.callHomeActivity(view);
		
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}
}

