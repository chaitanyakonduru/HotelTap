package com.example.hoteltap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class HotelTapDatabase {

	private static HotelTapDatabase invoiceAppDatabase;
	private static InvoiceDatabaseHelper databaseHelper;
	private static final String DATABASE_NAME = "hoteltap.db";
	private static final int DATABASE_VERSION = 2;
	private static final String TAG = HotelTapDatabase.class.getSimpleName();

	public HotelTapDatabase(Context context) {
		databaseHelper = new InvoiceDatabaseHelper(context, DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	public static HotelTapDatabase getInstance(Context context) {
		if (invoiceAppDatabase == null) {
			invoiceAppDatabase = new HotelTapDatabase(context);
		}
		return invoiceAppDatabase;
	}

	private static class InvoiceDatabaseHelper extends SQLiteOpenHelper {

		public InvoiceDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(ProductColumns.DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("Drop table if exists " + ProductColumns.TABLE_NAME);
		}

	}

	

	public static class ProductColumns implements BaseColumns {
		public static final String TABLE_NAME = "Products";
		public static final String PRODUCT_ID = "product_id";
		public static final String PRODUCT_NAME = "product_name";
		public static final String QTY_PICKUP = "qty_pickup";
		public static final String PRODUCT_PRICE = "product_price";
		public static final String QTY_DELIVERED = "qty_delivered";
		public static final String QTY_RETURNED="qty_returned";
		public static final String QTY_STOCK_IN_HAND="qty_stock_in_hand";

		private static final String DATABASE_CREATE = "create table Products(_id integer primary key autoincrement,"
				+ "product_id integer,"
				+ "product_price text,"
				+ "product_name text," +
				 "qty_pickup text," +
				 "qty_stock_in_hand text," +
				 "qty_returned text," +
				"qty_delivered text);";
	}

	

}
