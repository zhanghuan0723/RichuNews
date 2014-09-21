package com.twentyfirstcbh.richunews.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twentyfirstcbh.richunews.enums.ArticleType;
import com.twentyfirstcbh.richunews.object.Photo;
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
			JSONArray headlineJA = resJO.getJSONArray("list");

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
			String catname = jo.getString("catname");
			String type = jo.getString("type");
			String title = jo.getString("title");
			String thumb = jo.getString("thumb");
			String thumbdesc = jo.getString("thumbdesc");
			String desc = jo.getString("desc");
			String origurl = jo.getString("origurl");
			String inputtime = jo.getString("inputtime");
			String photo = jo.getString("photo");

			article = new TextArticle();
			article.setId(articleId);
			if ("article".equals(type)) {
				article.setType(ArticleType.TEXT);
			} else if ("image".equals(type)) {
				article.setType(ArticleType.PHOTO);
			} else if ("video".equals(type)) {
				article.setType(ArticleType.VIDEO);
			}
			article.setTitle(title);
			article.setThumbUrl(thumb);
			article.setThumbDesc(thumbdesc);
			article.setDesc(desc);
			article.setUpdateTime(inputtime);
			article.setWebUrl(origurl);
			if (photo.length() > 0) {
				ArrayList<Photo> photoList = new ArrayList<Photo>();
				JSONArray photoJA = new JSONArray(photo);
				for (int i = 0; i < photoJA.length(); i++) {
					JSONObject photoJO = photoJA.getJSONObject(i);
					Photo photoObj = new Photo();
					photoObj.setOriginalPicUrl(photoJO.getString("thumb"));
					photoObj.setDesc(photoJO.getString("desc"));
					photoList.add(photoObj);
				}
				article.setPhotos(photoList);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return article;
	}
}
