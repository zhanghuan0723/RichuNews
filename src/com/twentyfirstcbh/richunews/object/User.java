package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;

import com.twentyfirstcbh.richunews.Constant;

public class User implements Serializable {

	private static final long serialVersionUID = -8979344799194633058L;
	private String userName;
	private String nickname;
	private String photoUrl;
	private long expires;
	private String sid;
	public static final String CACHE_FILE_PATH = Constant.FILE_CACHE_PATH + "user";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public static String getCacheFilePath() {
		return CACHE_FILE_PATH;
	}

}
