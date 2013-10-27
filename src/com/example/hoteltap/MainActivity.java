package com.example.hoteltap;

import java.util.List;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.example.hoteltap.models.MenuItem;
import com.example.hoteltap.models.MenuItemCatagory;
import com.example.hoteltap.network.JsonResponseParser;
import com.example.hoteltap.network.NetworkCallback;
import com.example.hoteltap.network.RestaurantNetworkServiceManager;
import com.example.hoteltap.utils.Constants;
import com.example.hoteltap.utils.Utils;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends FragmentActivity implements OnNavigationListener,onFragmentLoadListener,NetworkCallback<Object> {

	private static final String TAG = MainActivity.class.getSimpleName();
	private ItemGridFragment fragment=null;
	private RestaurantNetworkServiceManager serviceManager;
	private ActionBar actionBar;
	private List<MenuItemCatagory> menuItemCatagoriesList;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		menuItemCatagoriesList=MenuItemCatagory.rawQuery(MenuItemCatagory.class, "select * from MenuItemCatagory ", null); 
		
		serviceManager=RestaurantNetworkServiceManager.getInstance(this);
		
		actionBar=getActionBar();
		actionBar.show();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);
		actionBar.setTitle("MENU");
		
		if(menuItemCatagoriesList!=null && !menuItemCatagoriesList.isEmpty())
		{
			bindItemstoSpinner(menuItemCatagoriesList);
		}
		else
		{
			progressDialog=ProgressDialog.show(this, "", "Please wait..", false, true);
			serviceManager.fetchItemsRequest(Constants.FETCH_ITEMS, this);

		}
	}


	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		MenuItemCatagory itemCatagory=menuItemCatagoriesList.get(arg0);
		FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
		if(fragment!=null)
		{
			fragmentTransaction.remove(fragment);
		}
		
		fragment=ItemGridFragment.newInstance(itemCatagory.getItemCatagoryName(), Integer.parseInt(itemCatagory.getItemCatagoryId()));
		fragmentTransaction.replace(R.id.framelayout, fragment).commit();
		
		return false;
	}

	@Override
	public void onSuccess(int requestCode, Object object) {
		progressDialog.dismiss();
		switch (requestCode) {
		case Constants.FETCH_ITEMS:
			progressDialog.dismiss();
			if(object!=null && object instanceof String)
			{
			menuItemCatagoriesList=JsonResponseParser.parseMenuCatagoryResponse((String)object);
			if(menuItemCatagoriesList!=null && !menuItemCatagoriesList.isEmpty())
			{
				bindItemstoSpinner(menuItemCatagoriesList);
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
				Utils.showToastMessage(this, "Data Updated");
				ActiveAndroid.setTransactionSuccessful();
				ActiveAndroid.endTransaction();
				
			}
			}
			break;

		default:
			break;
		}
	}

	private void bindItemstoSpinner(List<MenuItemCatagory> menuItemCatagories) {
		if(menuItemCatagories!=null && !menuItemCatagories.isEmpty())
		{
		ArrayAdapter<MenuItemCatagory> adapter=new ArrayAdapter<MenuItemCatagory>(this, R.layout.layout_custom_spinner_text,menuItemCatagoriesList);
		actionBar.setListNavigationCallbacks(adapter,this);
		}
	}

	@Override
	public void onFailure(int requestCode, String errorMessge) {
		progressDialog.dismiss();
		Utils.showToastMessage(this, errorMessge);
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


	@Override
	public void onFragmentLoaded(List<String> urls) {
		
		
	}

}
