package com.example.hoteltap;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends BaseActivity{
	
	private HotelTapApp app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("Home");
		setContentView(R.layout.layout_home);
		app=(HotelTapApp) getApplication();
	}
	
	public void onMenuClicked(View v)
	{
		startActivity(new Intent(this, MainActivity.class));
	}

	public void onOrderClicked(View v)
	{
		startActivity(new Intent(this, CartActivity.class));
	}
	
	@Override
	public void onBackPressed() {
		if(app.getOrderedMenuItemsList().isEmpty())
		{
		super.onBackPressed();
		}
		else
		{
			displayAlertDialog();
		}
	}
	
	private  void displayAlertDialog()
	{
		Builder builder=new Builder(this);
		builder.setMessage("Your Cart is not empty.Changes will discarded");
		builder.setPositiveButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.dismiss();
			}
		});
		builder.setNegativeButton("Discard", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
		builder.create().show();
	}

}
