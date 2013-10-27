package com.example.hoteltap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoteltap.R;
import com.example.hoteltap.models.MenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CustomBreadItemAdapter extends ArrayAdapter<MenuItem> {

	private Context mContext;
	private List<MenuItem> productsList;
	private DisplayImageOptions displayImageOptions;
	private ImageLoader imageLoader=ImageLoader.getInstance();
	
	public CustomBreadItemAdapter(Context context, int resource,
			List<MenuItem> objects,DisplayImageOptions displayImageOptions) {
		super(context, resource, objects);
		this.mContext = context;
		this.productsList = objects;
		this.displayImageOptions=displayImageOptions;
	}

	@Override
	public int getCount() {
		return productsList.size();
	}

	@Override
	public MenuItem getItem(int position) {
		return productsList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					com.example.hoteltap.R.layout.custom_layout_item, null);
			holder.itemNameView = (TextView) convertView
					.findViewById(R.id.custom_item_name);
			holder.imageview = (ImageView) convertView
					.findViewById(R.id.custom_item_imageview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final MenuItem menuItem = getItem(position);
		holder.itemNameView.setText(menuItem.getItemName());
		imageLoader.displayImage(menuItem.getItem_thumb_url(), holder.imageview, displayImageOptions);
		return convertView;
	}

	public class ViewHolder {
		ImageView imageview;
		TextView itemNameView;
	}

}
