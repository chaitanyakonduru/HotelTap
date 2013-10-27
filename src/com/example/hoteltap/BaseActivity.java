package com.example.hoteltap;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar=getActionBar();
		actionBar.show();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE|ActionBar.DISPLAY_HOME_AS_UP);
	}

}
