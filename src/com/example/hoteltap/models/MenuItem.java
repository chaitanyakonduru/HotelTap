package com.example.hoteltap.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "MenuItem")
public class MenuItem extends Model{

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

}
