package com.example.hoteltap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hoteltap.adapters.CustomOrderAdapter;
import com.example.hoteltap.models.MenuItem;

public class CartActivity extends Activity {

	private static final String TAG = CartActivity.class.getSimpleName();
	private ListView listView;
	private HotelTapApp hotelTapApp;
	private HashMap<Integer,MenuItem> orderedList;
	private TextView totalView;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_cart_custom_dialog_view);
		ActionBar actionBar =getActionBar();
		actionBar.setTitle("What's in your Cart");
		actionBar.show();
		
		hotelTapApp = (HotelTapApp) getApplication();
		listView = (ListView) findViewById(R.id.listview);
		orderedList = hotelTapApp.getOrderedMenuItemsList();
		findViewById(R.id.cart_item_delete).setVisibility(View.INVISIBLE);
		totalView=(TextView)findViewById(R.id.cart_item_totalview);
		
		if (orderedList != null && !orderedList.isEmpty()) {
			findViewById(R.id.cart_header_layout).setVisibility(View.VISIBLE);
			listView.setVisibility(View.VISIBLE);
			findViewById(R.id.cart_item_emptyview).setVisibility(View.GONE);
			listView.setAdapter(new CustomOrderAdapter(this,orderedList));
			totalView.setVisibility(View.VISIBLE);
			  bindTotalView();
			
		} else {
			listView.setVisibility(View.GONE);
			totalView.setVisibility(View.GONE);
			findViewById(R.id.cart_header_layout).setVisibility(View.GONE);
			findViewById(R.id.cart_item_emptyview).setVisibility(View.VISIBLE);
		}
	}

	public  void bindTotalView() {
		List<Integer> keysList=new ArrayList<Integer>(orderedList.keySet());
		 if(!keysList.isEmpty())
		  {
			 int total=0;
			  for(Integer key:keysList)
			  {
				  MenuItem menuItem=orderedList.get(key);
				  int orderedPrice=menuItem.getQuantity()*(Integer.parseInt(menuItem.getItemPrice()));
						  total+=orderedPrice;
			  }
			  totalView.setText("Total:"+String.valueOf(total));
		  }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,1,0,"CHECK OUT").setShowAsAction(android.view.MenuItem.SHOW_AS_ACTION_ALWAYS);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		if(item.getItemId()==1)
		{
			displayDialog();
		}
		return super.onOptionsItemSelected(item);
	}

	private void displayDialog() {
		// TODO Auto-generated method stub
		Builder builder=new Builder(this);
		builder.setTitle("Thanks for your Order");
		builder.setMessage("Your Order has been shipped to our Kitchen. It may take 15min to deliver. Please be patient");
		builder.setPositiveButton("Okay", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
				hotelTapApp.clearOrder();
				finish();
			}
		});
		builder.create().show();
	}


}
