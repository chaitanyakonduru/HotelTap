package com.example.hoteltap.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hoteltap.CartActivity;
import com.example.hoteltap.R;
import com.example.hoteltap.models.MenuItem;

public class CustomOrderAdapter extends BaseAdapter {

	private Context mContext;
	private HashMap<Integer, MenuItem> productsList;
	public CustomOrderAdapter(Context context,
			HashMap<Integer, MenuItem> hashMap) {
		this.mContext = context;
		this.productsList = hashMap;
	}

	@Override
	public int getCount() {
		return productsList.size();
	}

	@Override
	public MenuItem getItem(int position) {
		List<Integer> integer = new ArrayList<Integer>(productsList.keySet());
		return productsList.get(integer.get(position).intValue());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater
					.from(mContext)
					.inflate(
							com.example.hoteltap.R.layout.layout_cart_custom_dialog_view,
							null);
			holder.itemNameView = (TextView) convertView
					.findViewById(R.id.cart_item_name);
			holder.itemPriceView = (TextView) convertView
					.findViewById(R.id.cart_item_price);
			holder.itemQuantityView = (TextView) convertView
					.findViewById(R.id.cart_item_quantitiy);
			holder.listView = (ListView) convertView
					.findViewById(R.id.listview);
			holder.listView.setVisibility(View.GONE);
			holder.emptyView = (TextView) convertView
					.findViewById(R.id.cart_item_emptyview);
			holder.TotalView = (TextView) convertView
					.findViewById(R.id.cart_item_totalview);
			holder.emptyView.setVisibility(View.GONE);
			holder.TotalView.setVisibility(View.GONE);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.cart_item_delete);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		List<Integer> keys = new ArrayList<Integer>(productsList.keySet());
		if (!keys.isEmpty()) {
			final int currentObjectKey = keys.get(position);
			final MenuItem menuItem = productsList.get(currentObjectKey);
			if(menuItem!=null)
			{
			holder.itemNameView.setText(menuItem.getItemName());
			int orderedPrice=menuItem.getQuantity()*(Integer.parseInt(menuItem.getItemPrice()));
			menuItem.setOrderedPrice(orderedPrice);
			holder.itemPriceView.setText(String.valueOf(orderedPrice));
			holder.itemQuantityView.setText(String.valueOf(menuItem
					.getQuantity()));
			holder.imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (productsList.containsValue(menuItem)) {
						productsList.remove(currentObjectKey);

						CartActivity activity=(CartActivity) mContext;
						activity.bindTotalView();
					}
					notifyDataSetChanged();
				}
			});
			productsList.put(Integer.parseInt(menuItem.getItemId()), menuItem);
			
		}
		}
		return convertView;
	}

	public class ViewHolder {
		TextView itemNameView, itemPriceView, itemQuantityView, emptyView,
				TotalView;
		ListView listView;
		ImageView imageView;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

}
