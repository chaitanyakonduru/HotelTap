package com.example.hoteltap.network;


import android.os.Handler;
import android.os.Message;

import com.example.hoteltap.utils.Constants;

public class RestaurantAppHandler extends Handler{
	
	private NetworkCallback<Object> callback;
	private int requestCode;
	public RestaurantAppHandler(int requestCode,NetworkCallback<Object> callback)
	{
		this.requestCode=requestCode;
		this.callback=callback;
	}
	
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		
		if(msg.what==Constants.SUCCESS)
		{
			Object object=msg.obj;
			callback.onSuccess(requestCode,object);
		}
		else if(msg.what==Constants.FAILURE)
		{
			Object object=msg.obj;
			callback.onFailure(requestCode,object.toString());
		}
	}
}
