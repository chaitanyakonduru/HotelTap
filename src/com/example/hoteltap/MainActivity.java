package com.example.hoteltap;

import java.util.List;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.example.hoteltap.models.MenuItem;
import com.example.hoteltap.models.MenuItemCatagory;
import com.example.hoteltap.network.JsonResponseParser;
import com.example.hoteltap.network.NetworkCallback;
import com.example.hoteltap.network.RestaurantNetworkServiceManager;
import com.example.hoteltap.utils.Constants;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends FragmentActivity implements OnNavigationListener, NetworkCallback<Object> {

	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String [] itemCatagories=new String[]{"Breakfast","Lunch","Dinner"};
	ItemGridFragment fragment=null;
	private RestaurantNetworkServiceManager serviceManager;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		serviceManager=RestaurantNetworkServiceManager.getInstance(this);
		serviceManager.fetchItemsRequest(Constants.FETCH_ITEMS, this);
		actionBar=getActionBar();
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

	@Override
	public void onSuccess(int requestCode, Object object) {
		switch (requestCode) {
		case Constants.FETCH_ITEMS:
			if(object!=null && object instanceof String)
			{
			List<MenuItemCatagory> menuItemCatagoriesList=JsonResponseParser.parseMenuCatagoryResponse((String)object);
			if(menuItemCatagoriesList!=null && !menuItemCatagoriesList.isEmpty())
			{
				ArrayAdapter<MenuItemCatagory> adapter=new ArrayAdapter<MenuItemCatagory>(this, android.R.layout.simple_list_item_1,menuItemCatagoriesList);
				actionBar.setListNavigationCallbacks(adapter,this);
				ActiveAndroid.beginTransaction();
				for(MenuItemCatagory menuItemCatagory:menuItemCatagoriesList)
				{
					menuItemCatagory.save();
					if(menuItemCatagory.getItemsList()!=null && !menuItemCatagory.getItemsList().isEmpty())
					{
					for(MenuItem menuItem:menuItemCatagory.getItemsList())
					{
						if(!checkIfExists(menuItem))
						{
						menuItem.save();
						}
					}
					}
				}
				Log.v(TAG, "Updation Completed");
				ActiveAndroid.setTransactionSuccessful();
				ActiveAndroid.endTransaction();
				
			}
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onFailure(int requestCode, String errorMessge) {
		
	}
	
	private boolean checkIfExists(MenuItem calendarEvent) {
		MenuItem calendarEvents = new Select("itemid")
				.from(MenuItem.class)
				.where("itemid="
						+ calendarEvent.getItemId()).executeSingle();
		if (calendarEvents != null) {
			return true;
		} else {
			return false;
		}

	}

}
