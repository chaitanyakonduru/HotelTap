package com.example.hoteltap;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.example.hoteltap.R;

public class MainActivity extends FragmentActivity implements OnNavigationListener {

	private static final String TAG = null;
	private static final String [] itemCatagories=new String[]{"Breakfast","Lunch","Dinner"};
	ItemGridFragment fragment=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG, "Test");
		setContentView(R.layout.layout_main);
		
		ActionBar actionBar=getActionBar();
		actionBar.show();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO);
		actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemCatagories), this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		String itemCatagoryName=itemCatagories[arg0];
		FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
		if(fragment!=null)
		{
			fragmentTransaction.remove(fragment);
		}
		
		fragment=ItemGridFragment.newInstance(itemCatagoryName, 10);
		fragmentTransaction.replace(R.id.framelayout, fragment).commit();
		
		return false;
	}

}
