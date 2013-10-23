package com.example.hoteltap.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;

import com.example.hoteltap.HotelTapApp;

public class DatabaseQueryManager {
	private static DatabaseQueryManager databaseQueryManager;
	private ExecutorService executorService;
	private HotelTapDatabase database;
	private HotelTapApp application;

	public static DatabaseQueryManager getInstance(Context context) {
		return databaseQueryManager == null ? new DatabaseQueryManager(context)
				: databaseQueryManager;
	}

	public DatabaseQueryManager(Context context) {
		executorService = Executors.newSingleThreadExecutor();
		application = (HotelTapApp) context.getApplicationContext();
		database = application.shareDatabaseInstance();
	}


	public void getAllDrivers(final int reqCode,
			final DbQueryCallback<Object> callback) {
		final DatabaseHandler databaseHandler = new DatabaseHandler(reqCode,
				callback);
		executorService.execute(new Runnable() {

			@Override
			public void run() {
			}
		});

	}
	public void checkTableNullOrNot(int reqCode, final String tableName,
			DbQueryCallback<Object> callback) {
		final DatabaseHandler databaseHandler = new DatabaseHandler(reqCode,
				callback);
		executorService.execute(new Runnable() {

			@Override
			public void run() {
			}
		});
	}

	public void getAllProducts(final int reqCode,
			final DbQueryCallback<Object> callback) {
		final DatabaseHandler databaseHandler = new DatabaseHandler(reqCode,
				callback);
		executorService.execute(new Runnable() {

			@Override
			public void run() {
			}
		});

	}

}
