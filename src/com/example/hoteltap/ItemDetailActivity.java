package com.example.hoteltap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoteltap.models.MenuItem;
import com.example.hoteltap.utils.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ItemDetailActivity extends BaseActivity implements OnClickListener {
	public static final String KEY_EXTRA_MENUITEM = "menuitem";
	private MenuItem menuItem;
	private TextView itemNameView;
	private TextView itemPriceView;
	private TextView itemTypeView;
	private ImageView itemImageView;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private TextView itemDescriptionView;
	private HotelTapApp hotelTapApp;
	private EditText quantityText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_item_details);
		prepareUI();
		hotelTapApp = (HotelTapApp) getApplication();
		configureBitmapOptions();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey(KEY_EXTRA_MENUITEM)) {
			menuItem = (MenuItem) bundle.get(KEY_EXTRA_MENUITEM);
		}
		if (menuItem != null) {
			itemNameView.setText(menuItem.getItemName());
			itemPriceView.setText(menuItem.getItemPrice());
			itemTypeView.setText(menuItem.getItemType());
			imageLoader.displayImage(menuItem.getItem_main_url(),
					itemImageView, options);
			itemDescriptionView.setText("Description:"
					+ menuItem.getItemDescription());
			quantityText.setText(String.valueOf(menuItem.getQuantity()));

		}
	}

	private void prepareUI() {
		itemNameView = (TextView) findViewById(R.id.item_details_item_name);
		itemPriceView = (TextView) findViewById(R.id.item_details_item_price);
		itemTypeView = (TextView) findViewById(R.id.item_details_item_type);
		itemImageView = (ImageView) findViewById(R.id.item_details_image);
		itemDescriptionView = (TextView) findViewById(R.id.item_details_item_description);
		findViewById(R.id.item_details_item_add).setOnClickListener(this);
		findViewById(R.id.item_details_item_add).setOnClickListener(this);
		quantityText = (EditText) findViewById(R.id.item_details_item_quantity);
		getActionBar().setTitle("Item Details");
	}

	private void configureBitmapOptions() {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.item_details_item_add:
			if (menuItem != null) {
				if (!TextUtils.isEmpty(quantityText.getText().toString()) && Utils.checkZero(quantityText.getText().toString().trim())) {
					menuItem.setQuantity(Integer.parseInt(quantityText
							.getText().toString()));
				Utils.showToastMessage(this, "Your Order Added to Cart");
				hotelTapApp.setOrderedMenuItemsListCart(Integer.parseInt(menuItem.getItemId()), menuItem);
				finish();
				}
				else
				{
					Utils.showToastMessage(this, "Invalid Quantity");
				}
			}
			break;
		case R.id.item_details_item_back:
			finish();

		default:
			break;
		}

	}
}
