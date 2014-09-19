package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;

import com.twentyfirstcbh.richunews.enums.TimeType;
import com.twentyfirstcbh.richunews.utils.PublicFunction;

public class Comment implements Serializable {

	private static final long serialVersionUID = -2584549190277820447L;
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 评论时间（时间戳形式）
	 */
	private long publishTime;
	/**
	 * 赞（顶）的数量
	 */
	private int praiseCount;
	/**
	 * 评论者的名称
	 */
	private String userName;
	/**
	 * 评论者头像
	 */
	private String userPhotoUrl;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/*
	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}*/

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long time) {
		this.publishTime = time;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhotoUrl() {
		return userPhotoUrl;
	}

	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}
	
	public String getPublishTime(TimeType type) {
		return PublicFunction.getPublishTime(type, publishTime);
	}

}
