package com.example.hoteltap.database;

@SuppressWarnings("hiding")
public interface DbQueryCallback<Object> {
	
	void onQueryExecuted(int requestCode,Object object);

}
