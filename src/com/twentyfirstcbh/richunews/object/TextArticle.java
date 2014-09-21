package com.twentyfirstcbh.richunews.object;

import java.util.ArrayList;
import java.util.List;

public class TextArticle extends BaseArticle {

	private static final long serialVersionUID = 4970471700975925386L;

	private String content;

	/**
	 * 相关文章列表
	 */
	private List<TextArticle> relatedArticles;
	
	/**
	 * 文章的网页地址
	 */
	private String webUrl;
	
	/**
	 * 文章缓存文件名
	 */
	private String cacheFileName;
	
	/**
	 * 评论数
	 */
	private int commentCount;
	
	/**
	 * 图集
	 */
	private ArrayList<Photo> photos;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<TextArticle> getRelatedArticles() {
		return relatedArticles;
	}

	public void setRelatedArticles(List<TextArticle> relatedArticles) {
		this.relatedArticles = relatedArticles;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getCacheFileName() {
		return cacheFileName;
	}

	public void setCacheFileName(String cacheFileName) {
		this.cacheFileName = cacheFileName;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public ArrayList<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

}
