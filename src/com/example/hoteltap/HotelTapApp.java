package com.example.hoteltap;

import com.activeandroid.ActiveAndroid;
import com.example.hoteltap.database.HotelTapDatabase;

import android.app.Application;

public class HotelTapApp extends Application{

	public HotelTapDatabase shareDatabaseInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		ActiveAndroid.dispose();
	}

}
