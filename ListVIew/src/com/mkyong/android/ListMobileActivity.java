package com.mkyong.android;

import com.mkyong.android.adaptor.MobileArrayAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

public class ListMobileActivity extends ListActivity {

	static final String[] MOBILE_OS = new String[] { "Android", "iOS",
			"WindowsMobile", "Blackberry"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
		

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	}

}