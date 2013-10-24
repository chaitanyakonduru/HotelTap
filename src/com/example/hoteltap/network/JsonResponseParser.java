package com.example.hoteltap.network;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hoteltap.models.MenuItem;
import com.example.hoteltap.models.MenuItemCatagory;

public class JsonResponseParser {

	public static List<MenuItemCatagory> parseMenuCatagoryResponse(String response)
	{
		List<MenuItemCatagory> menuItemCatagories=null;
		List<MenuItem> menuItemsList=null;
		
		try
		{
			JSONObject jsonObject=new JSONObject(response);
			if(jsonObject.has("item_catagories"))
			{
				JSONArray menuItemCatagoriesArrayObj=jsonObject.getJSONArray("item_catagories");
				if(menuItemCatagoriesArrayObj!=null && menuItemCatagoriesArrayObj.length()>0)
				{
					menuItemCatagories=new ArrayList<MenuItemCatagory>();
					for(int i=0;i<menuItemCatagoriesArrayObj.length();i++)
					{
						MenuItemCatagory itemCatagory=new MenuItemCatagory();
						JSONObject menuItemCatagoryObject=menuItemCatagoriesArrayObj.getJSONObject(i);
						itemCatagory.setItemCatagoryId(menuItemCatagoryObject.has("catagory_id")?menuItemCatagoryObject.getString("catagory_id"):null);
						itemCatagory.setItemCatagoryName(menuItemCatagoryObject.has("catagory_name")?menuItemCatagoryObject.getString("catagory_name"):null);
						if(menuItemCatagoryObject.has("items"))
						{
							JSONArray itemsArrayObj=menuItemCatagoryObject.getJSONArray("items");
							if(itemsArrayObj!=null && itemsArrayObj.length()>0)
							{
								menuItemsList=new ArrayList<MenuItem>();
								for(int j=0;j<itemsArrayObj.length();j++)
								{
									MenuItem menuItem=new MenuItem();
									JSONObject itemObj=itemsArrayObj.getJSONObject(j);
									menuItem.setItemCatagoryId(itemCatagory.getItemCatagoryId());
									menuItem.setItem_main_url(itemObj.has("file_url")?itemObj.getString("file_url"):null);
									menuItem.setItem_thumb_url(itemObj.has("thumb_url")?itemObj.getString("thumb_url"):null);
									menuItem.setItemDescription(itemObj.has("item_description")?itemObj.getString("item_description"):null);
									menuItem.setItemId(itemObj.has("item_id")?itemObj.getString("item_id"):null);
									menuItem.setItemName(itemObj.has("item_name")?itemObj.getString("item_name"):null);
									menuItem.setItemPrice(itemObj.has("price")?itemObj.getString("price"):null);
									menuItem.setItemType(itemObj.has("type")?itemObj.getString("type"):null);
									menuItemsList.add(menuItem);
								}
							}
							itemCatagory.setItemsList(menuItemsList);
						}
						menuItemCatagories.add(itemCatagory);
					}
				}
			}
		}
		catch(JSONException je)
		{
			je.printStackTrace();
		}
		return menuItemCatagories;
	}
}
