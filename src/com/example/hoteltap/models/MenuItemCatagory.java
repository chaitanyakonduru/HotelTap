package com.example.hoteltap.models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="MenuItemCatagory")
public class MenuItemCatagory extends Model{
	
	@Column(name="itemcatagoryid")
	private String itemCatagoryId;
	
	@Column(name="itemcatagoryname")
	
	private String itemCatagoryName;
	
	private List<MenuItem> itemsList;
	
	public String getItemCatagoryId() {
		return itemCatagoryId;
	}
	public void setItemCatagoryId(String itemCatagoryId) {
		this.itemCatagoryId = itemCatagoryId;
	}
	public String getItemCatagoryName() {
		return itemCatagoryName;
	}
	public void setItemCatagoryName(String itemCatagoryName) {
		this.itemCatagoryName = itemCatagoryName;
	}
	public List<MenuItem> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<MenuItem> itemsList) {
		this.itemsList = itemsList;
	}
	
	
	
	

}
