package com.example.hoteltap;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.activeandroid.query.Select;
import com.example.hoteltap.adapters.CustomBreadItemAdapter;
import com.example.hoteltap.models.MenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ItemGridFragment extends Fragment implements OnItemClickListener {

	private static ItemGridFragment itemGridFragment;
	private static final String KEY_ITEM_NAME = "item_name";
	private static final String KEY_ITEM_ID = "item_id";
	private static final String TAG = ItemGridFragment.class.getSimpleName();
	private GridView mGridView;
	private HashMap<Integer, List<MenuItem>> hashMap;
	private List<MenuItem> menuItems;
	private DisplayImageOptions options;
	private ImageView imageView;
	private ImageLoader imageLoader=ImageLoader.getInstance();
	private Timer timer;
	private Random random;
	
	public static ItemGridFragment newInstance(String string, int itemCatagoryId) {
		itemGridFragment = new ItemGridFragment();
		Bundle bundle = new Bundle();
		bundle.putString(KEY_ITEM_NAME, string);
		bundle.putInt(KEY_ITEM_ID, itemCatagoryId);
		itemGridFragment.setArguments(bundle);
		
		return itemGridFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState)
		;
		timer=new Timer();
		random=new Random();
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.layout_item, null);
		mGridView=(GridView)v.findViewById(R.id.gridview);
		mGridView.setOnItemClickListener(this);
		imageView=(ImageView) v.findViewById(R.id.slideshow_image);
		return v;
	}

	@SuppressLint("UseSparseArrays")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		configureBitmapOptions();
		int itemId = getArguments().getInt(KEY_ITEM_ID);
		hashMap=new HashMap<Integer, List<MenuItem>>();
		
		if(!hashMap.containsValue(itemId))
		{
		List<MenuItem> menuItems = new Select()
		.from(MenuItem.class)
		.where("itemCatagoryId="
				+ itemId).execute();
		if(menuItems!=null && !menuItems.isEmpty())
		{
			hashMap.put(itemId, menuItems);
		}
		}
		menuItems=hashMap.get(itemId);
		
		if(menuItems!=null && !menuItems.isEmpty())
		{
			
			timer.schedule(timerTask, 0, 5*1000);
			mGridView.setAdapter(new CustomBreadItemAdapter(getActivity(), -1, menuItems,options));
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(timer!=null)
		{
			timer.cancel();
		}
	}
	
	private void configureBitmapOptions()
	{
		options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(0, 1, 0, "ORDER").setIcon(R.drawable.ic_cart).setShowAsAction(android.view.MenuItem.SHOW_AS_ACTION_ALWAYS|android.view.MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		MenuItem menuItem=menuItems.get(arg2);
		Intent intent=new Intent(getActivity(),ItemDetailActivity.class);
		intent.putExtra(ItemDetailActivity.KEY_EXTRA_MENUITEM, menuItem);
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		if(item.getItemId()==1)
		{
			startActivity(new Intent(getActivity(),CartActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void loadRandomImage()
	{
		
		
		int index=random.nextInt(menuItems.size());
		String url=menuItems.get(index).getItem_main_url();
		imageLoader.displayImage(url, imageView,options);
		
	}
	
	TimerTask timerTask=new TimerTask() {
		
		@Override
		public void run() {
			Message.obtain(handler, 100).sendToTarget();
		}
	};
	
	Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			loadRandomImage();
		}
		
	};
	

}
