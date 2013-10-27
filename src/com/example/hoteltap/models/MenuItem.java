package com.example.hoteltap.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "MenuItem")
public class MenuItem extends Model implements Parcelable{

	public String getItemCatagoryId() {
		return itemCatagoryId;
	}

	public void setItemCatagoryId(String itemCatagoryId) {
		this.itemCatagoryId = itemCatagoryId;
	}

	@Column(name = "itemid")
	private String itemId;
	
	@Column(name = "itemcatagoryid")
	private String itemCatagoryId;
	
	@Column(name = "itemname")
	private String itemName;
	
	@Column(name = "itemtype")
	private String itemType;
	
	@Column(name = "itemdescription")
	private String itemDescription;
	
	@Column(name = "itemprice")
	private String itemPrice;
	
	@Column(name = "item_thumb_url")
	private String item_thumb_url;
	
	@Column(name = "item_main_url")
	private String item_main_url;
	
	private int quantity;
	private int orderedPrice;
	

	public int getOrderedPrice() {
		return orderedPrice;
	}

	public void setOrderedPrice(int orderedPrice) {
		this.orderedPrice = orderedPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItem_thumb_url() {
		return item_thumb_url;
	}

	public void setItem_thumb_url(String item_thumb_url) {
		this.item_thumb_url = item_thumb_url;
	}

	public String getItem_main_url() {
		return item_main_url;
	}

	public void setItem_main_url(String item_main_url) {
		this.item_main_url = item_main_url;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public MenuItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(this.item_main_url);
		arg0.writeString(this.item_thumb_url);
		arg0.writeString(this.itemCatagoryId);
		arg0.writeString(this.itemDescription);
		arg0.writeString(this.itemId);
		arg0.writeString(this.itemName);
		arg0.writeString(this.itemPrice);
		arg0.writeString(this.itemType);
	}
	
	public MenuItem(Parcel parcel)
	{
		this.item_main_url=parcel.readString();
		this.item_thumb_url=parcel.readString();
		this.itemCatagoryId=parcel.readString();
		this.itemDescription=parcel.readString();
		this.itemId=parcel.readString();
		this.itemName=parcel.readString();
		this.itemPrice=parcel.readString();
		this.itemType=parcel.readString();
	}
	
	public static final Creator<MenuItem> CREATOR=new Creator<MenuItem>() {
		
		@Override
		public MenuItem[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return new MenuItem[arg0];
		}
		
		@Override
		public MenuItem createFromParcel(Parcel arg0) {
			// TODO Auto-generated method stub
			return new MenuItem(arg0);
		}
	};
	
	

}
