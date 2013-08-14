package com.javapapers.android.sqlitestorageoption;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d(LOGCAT,"Created");
    }
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE animals ( animalId INTEGER PRIMARY KEY, animalName TEXT,location TEXT,Typess TEXT,ImageName TEXT,city TEXT,descr TEXT)";
        database.execSQL(query);
 
        Log.d(LOGCAT,"animals Created");
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS animals";
		database.execSQL(query);
        onCreate(database);
	}
	
	
	
	
	public void insertAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("animalName", queryValues.get("animalName"));
		values.put("location", queryValues.get("location"));
		values.put("Typess", queryValues.get("Typess"));
		values.put("ImageName", queryValues.get("ImageName"));
		values.put("city", queryValues.get("city"));
		values.put("descr", queryValues.get("descr"));
		database.insert("animals", null, values);
		database.insert("location", null, values);
		database.insert("Typess", null, values);
		database.insert("ImageName", null, values);
		database.insert("city", null, values);
		database.insert("descr", null, values);
		database.close();
	}
	
	public int updateAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("animalName", queryValues.get("animalName"));
	    values.put("location", queryValues.get("location"));
	    values.put("Typess", queryValues.get("Typess"));
	    values.put("ImageName", queryValues.get("ImageName"));
	    values.put("city", queryValues.get("city"));
	    values.put("descr", queryValues.get("descr"));
	    return database.update("animals", values, "animalId" + " = ?", new String[] { queryValues.get("animalId") });
	
	}
	
	public void deleteAnimal(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  animals where animalId='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	
	public ArrayList<HashMap<String, String>> getAllAnimals() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM animals";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String,String>();
	        	map.put("animalId", cursor.getString(0));
	        	map.put("animalName", cursor.getString(1));
	        	map.put("location", cursor.getString(2));
	        	map.put("Typess", cursor.getString(3));
	        	map.put("ImageName", cursor.getString(4));
	        	map.put("city", cursor.getString(5));
	        	map.put("descr", cursor.getString(6));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wordList;
	}
	
	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM animals where animalId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {
					//HashMap<String, String> map = new HashMap<String, String>();
	        	wordList.put("animalName", cursor.getString(1));
	        	wordList.put("location", cursor.getString(2));
	        	wordList.put("Typess", cursor.getString(3));
	        	wordList.put("ImageName", cursor.getString(4));
	        	wordList.put("city", cursor.getString(5));
	        	wordList.put("descr", cursor.getString(6));
				  // wordList.add(map);
	        } while (cursor.moveToNext());
	    }				    
	return wordList;
	}	
}
