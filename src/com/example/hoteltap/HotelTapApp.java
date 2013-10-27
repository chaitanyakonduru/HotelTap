package com.example.hoteltap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.example.hoteltap.database.HotelTapDatabase;
import com.example.hoteltap.models.MenuItem;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class HotelTapApp extends Application {

	public HotelTapDatabase shareDatabaseInstance() {
		// TODO Auto-generated method stub
		return null;
	}

//	public List<MenuItem> orderedMenuItemsList = new ArrayList<MenuItem>();
	private HashMap<Integer, MenuItem> hashMap = new HashMap();

	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(this);
		ActiveAndroid.initialize(this);
	}

	public void setOrderedMenuItemsListCart(int key, MenuItem menuItem) {
		hashMap.put(key, menuItem);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ActiveAndroid.dispose();
	}

	public HashMap<Integer,MenuItem> getOrderedMenuItemsList() {
		return hashMap;
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCache(
						new UnlimitedDiscCache(StorageUtils
								.getOwnCacheDirectory(context, "Restaurant"),
								new Md5FileNameGenerator()))
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	public void clearOrder()
	{
		if(!hashMap.isEmpty())
		{
			hashMap.clear();
		}
	}

}
