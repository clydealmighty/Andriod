package com.android.event;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;
 
public class MainActivity extends Activity {
	protected static final String TAG = "SlidableActivity";
	private static final int ACTION_TYPE_DEFAULT = 0;
	private static final int ACTION_TYPE_UP = 1;
	private static final int ACTION_TYPE_RIGHT = 2;
	private static final int ACTION_TYPE_DOWN = 3;
	private static final int ACTION_TYPE_LEFT = 4;
	private static final int SLIDE_RANGE = 100;
	private float mTouchStartPointX;
	private float mTouchStartPointY;
	private int mActionType = ACTION_TYPE_DEFAULT;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
	}	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getRawX();
		int y = (int) event.getRawY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mTouchStartPointX = event.getRawX();
			mTouchStartPointY = event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (mTouchStartPointX - x > SLIDE_RANGE) {
				mActionType = ACTION_TYPE_LEFT;
			} else if (x - mTouchStartPointX > SLIDE_RANGE) {
				mActionType = ACTION_TYPE_RIGHT;
			} else if (mTouchStartPointY - y > SLIDE_RANGE) {
				mActionType = ACTION_TYPE_UP;
			} else if (y - mTouchStartPointY > SLIDE_RANGE) {
				mActionType = ACTION_TYPE_DOWN;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mActionType == ACTION_TYPE_UP) {
				slideUp();
			} else if (mActionType == ACTION_TYPE_RIGHT) {
				slideToRight();
			} else if (mActionType == ACTION_TYPE_DOWN) {
				slideDown();
			} else if (mActionType == ACTION_TYPE_LEFT) {
				slideToLeft();
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	protected void slideToLeft() {
		SharedPreferences sharedPreferences = getSharedPreferences("Mypref", 0);
		String key = sharedPreferences.getString("AppName", "");
		if (!key.equals(""))
		{	Intent intent = new Intent(this, CalendarViewActivity.class);
		startActivity(intent);
		overridePendingTransition(R.drawable.slide_in, R.drawable.slide_out);
		}
		else {
			Intent intent = new Intent(getBaseContext(),ApplicationName.class);
			startActivity(intent);
		}
		
		
	}
	
	protected void slideToRight() {
		Log.d(TAG, "slideToRight() was called.");
		Toast.makeText(getBaseContext(), "slideToRight", Toast.LENGTH_LONG).show();
	}
	
	protected void slideUp() {
		SharedPreferences sharedPreferences = getSharedPreferences("Mypref", 0);
		String key = sharedPreferences.getString("AppName", "");
		if (!key.equals(""))
		{	Intent intent = new Intent(this, CalendarViewActivity.class);
		startActivity(intent);
		overridePendingTransition(R.drawable.slide_in, R.drawable.slide_out);
		}
		else {
			Intent intent = new Intent(getBaseContext(), ApplicationName.class);
			startActivity(intent);
		}
	}
	
	protected void slideDown() {
		Toast.makeText(getBaseContext(), "SlideDown",  Toast.LENGTH_LONG).show();
		Log.d(TAG, "slideDown() was called.");
	}
}