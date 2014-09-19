package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;

public class Photo implements Serializable {

	private static final long serialVersionUID = -2600611187826716693L;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 最小尺寸图片
	 */
	private String smallPicUrl;

	/**
	 * 中等尺寸图片
	 */
	private String middlePicUrl;
	/**
	 * 大尺寸图片
	 */
	private String largePicUrl;
	/**
	 * 原图
	 */
	private String originalPicUrl;
	/**
	 * 点击
	 */
	private String linkUrl;
	/**
	 * 描述
	 */
	private String desc;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSmallPicUrl() {
		return smallPicUrl;
	}

	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}

	public String getMiddlePicUrl() {
		return middlePicUrl;
	}

	public void setMiddlePicUrl(String middlePicUrl) {
		this.middlePicUrl = middlePicUrl;
	}

	public String getLargePicUrl() {
		return largePicUrl;
	}

	public void setLargePicUrl(String largePicUrl) {
		this.largePicUrl = largePicUrl;
	}

	public String getOriginalPicUrl() {
		return originalPicUrl;
	}

	public void setOriginalPicUrl(String originalPicUrl) {
		this.originalPicUrl = originalPicUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
