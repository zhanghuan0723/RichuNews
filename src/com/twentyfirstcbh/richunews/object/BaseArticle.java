package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;

import com.twentyfirstcbh.richunews.enums.ArticleType;
import com.twentyfirstcbh.richunews.enums.TimeType;
import com.twentyfirstcbh.richunews.utils.PublicFunction;

public class BaseArticle implements Serializable {

	private static final long serialVersionUID = -7426866305625481267L;

	/**
	 * 文章ID
	 */
	private int id;
	
	/**
	 * 栏目ID
	 */
	private int categoryId;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 摘要
	 */
	private String desc;
	
	/**
	 * 缩略图
	 */
	private String thumbUrl;
	
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 发布时间
	 */
	private long publishTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 文章来源
	 */
	private String source;
	
	private ArticleType type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishTime(TimeType type) {
		return PublicFunction.getPublishTime(type, publishTime);
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public ArticleType getType() {
		return type;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}
	
}
