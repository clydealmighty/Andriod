package com.example.mymanagement;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
public class listdata extends ListActivity {


	
	String[] uname=new String[10];
	String[] rows=new String[10];
	ArrayList<String> child = new ArrayList<String>();
	final Context context = this;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// no more this
		// setContentView(R.layout.list_fruit);

		String symbols = "";

		 SharedPreferences sharedPreferences =  getSharedPreferences("Mypref", 0);
		     symbols = sharedPreferences.getString("uname", "");
		   if (!symbols.equals(""))
		   {
			   if (!symbols.contains("|"))
			   {
				   child.add(symbols.substring(0, symbols.indexOf("^")));
				  // child.add(symbols);
				  // Toast.makeText(getApplicationContext(),
							//"in", Toast.LENGTH_SHORT).show();
			   }
			   else
			   {
				
				   rows=symbols.split("\\|");
				   for (int i = 0; i < rows.length; i++) {
					   child.add(rows[i].substring(0, rows[i].indexOf("^")));
				   }
			   }
		   }
		
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list,
				child));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				
			
				
 				Intent i = new Intent(context, DataActivity.class);
 				i.putExtra("name", ((TextView) view).getText());
 				 startActivity(i);  


			}
		});

	}

}
