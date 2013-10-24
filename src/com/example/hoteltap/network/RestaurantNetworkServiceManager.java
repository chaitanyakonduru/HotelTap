package com.example.hoteltap.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.content.Context;
import android.util.Log;

public class RestaurantNetworkServiceManager {

	private static RestaurantNetworkServiceManager serviceManager;
	private static ExecutorService executorService;
	private static final String BASE_URL = "http://avml.in/ravi/";
	private static final String TAG = RestaurantNetworkServiceManager.class
			.getSimpleName();

	public static RestaurantNetworkServiceManager getInstance(Context context) {
		return serviceManager == null ? serviceManager = new RestaurantNetworkServiceManager(
				context) : serviceManager;
	}

	public RestaurantNetworkServiceManager(Context context) {
		executorService = Executors.newSingleThreadExecutor();
	}

	public Future fetchItemsRequest(int requestCode,
			NetworkCallback<Object> callback) {

		String driversUrl = "restauarant_menu.json";
		final String UrlString = String.format(BASE_URL) + driversUrl;

		Log.v(TAG, "Request Url:" + UrlString);
		final RestaurantAppHandler handler = new RestaurantAppHandler(requestCode,
				callback);
		return executorService.submit(new HttpRestConn(UrlString, handler));

	}

	

}