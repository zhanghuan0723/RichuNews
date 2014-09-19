package com.twentyfirstcbh.richunews.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twentyfirstcbh.richunews.object.TextArticle;

public class JsonUtil {

	public static String string2Json(String s) {
		s = s.replace("\\b", "");
		s = s.replace("\\f", "");
		s = s.replace("\\n", "<br />");
		s = s.replace("\\r", "<br />");
		s = s.replace("\\t", "");
		return s;
	}

	public static ArrayList<TextArticle> getHeadlineData(String resJson) {
		ArrayList<TextArticle> headlineList;
		try {
			JSONObject resJO = new JSONObject(resJson);
			JSONArray headlineJA = resJO.getJSONArray("headline");

			headlineList = new ArrayList<TextArticle>();
			for (int i = 0; i < headlineJA.length(); i++) {
				JSONObject headlineJO = headlineJA.getJSONObject(i);
				TextArticle textArticle = getHeadlineArticle(headlineJO);
				headlineList.add(textArticle);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return headlineList;
	}

	private static TextArticle getHeadlineArticle(JSONObject jo) {
		TextArticle article;
		try {
			int articleId = Integer.parseInt(jo.getString("id"));
			String title = jo.getString("title");
			String desc = jo.getString("description");
			String thumb = jo.getString("thumb");
			String updateTime = jo.getString("updatetime");

			article = new TextArticle();
			article.setId(articleId);
			article.setTitle(title);
			article.setDesc(desc);
			article.setThumbUrl(thumb);
			article.setUpdateTime(updateTime);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return article;
	}
}
