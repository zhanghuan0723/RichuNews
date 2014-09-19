package com.twentyfirstcbh.richunews.dao;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.twentyfirstcbh.richunews.object.Category;

public interface ChannelDaoInface {
	public boolean addCache(Category item);

	public boolean deleteCache(String whereClause, String[] whereArgs);

	public boolean updateCache(ContentValues values, String whereClause,
			String[] whereArgs);

	public Map<String, String> viewCache(String selection,
			String[] selectionArgs);

	public List<Map<String, String>> listCache(String selection,
			String[] selectionArgs);

	public void clearFeedTable();
}
