package com.example.hoteltap.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

	public static void showToastMessage(Context context,String message)
	{
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public static boolean checkZero(String string)
	{
		try
		{
			int a=Integer.parseInt(string);
			if(a>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
