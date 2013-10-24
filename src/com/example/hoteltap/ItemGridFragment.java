package com.example.hoteltap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ItemGridFragment extends Fragment {

	private static ItemGridFragment itemGridFragment;
	private static final String KEY_ITEM_NAME = "item_name";
	private static final String KEY_ITEM_ID = "item_id";
	private static final String TAG = ItemGridFragment.class.getSimpleName();
	private GridView mGridView;

	public static ItemGridFragment newInstance(String string, int itemCatagoryId) {
		itemGridFragment = new ItemGridFragment();
		Bundle bundle = new Bundle();
		bundle.putString(KEY_ITEM_NAME, string);
		bundle.putInt(KEY_ITEM_ID, itemCatagoryId);
		itemGridFragment.setArguments(bundle);
		return itemGridFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.layout_item_details, null);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String itemCatagoryName = getArguments().getString(KEY_ITEM_NAME);
		int itemId = getArguments().getInt(KEY_ITEM_ID);
	}

}
