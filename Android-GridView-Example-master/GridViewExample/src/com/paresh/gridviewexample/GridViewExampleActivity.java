package com.paresh.gridviewexample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridViewExampleActivity extends Activity {
    /** Called when the activity is first created. */
	
	private GridviewAdapter mAdapter;
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;
	
	private GridView gridView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        prepareList();
        
        // prepared arraylist and passed it to the Adapter class
        mAdapter = new GridviewAdapter(this,listCountry, listFlag);
        
        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);
        
        // Implement On Item click listener
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Toast.makeText(GridViewExampleActivity.this, mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
			}
		});
        
    }
    
    public void prepareList()
    {
    	  listCountry = new ArrayList<String>();
    	  
    	  listCountry.add("india");
    	  listCountry.add("Brazil");
          listCountry.add("Canada");
          listCountry.add("China");

          
          listFlag = new ArrayList<Integer>();
          listFlag.add(R.drawable.india);
          listFlag.add(R.drawable.brazil);
          listFlag.add(R.drawable.canada);
          listFlag.add(R.drawable.china);

    }
   
}