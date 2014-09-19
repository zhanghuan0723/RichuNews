package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;

import com.twentyfirstcbh.richunews.enums.CategoryType;

public class Category implements Serializable {

	private static final long serialVersionUID = -5517225428600092041L;
	private int id;
	private String name;
	private String apiUrl;
	private String link;
	private CategoryType type;
	/** 
	 * 栏目在整体中的排序顺序  rank
	 *  */
	public Integer orderId;
	/** 
	 * 栏目是否选中
	 *  */
	public Integer selected;

	public Category(int id, String name, String apiUrl, String link,
			CategoryType type, Integer orderId, Integer selected) {
		super();
		this.id = id;
		this.name = name;
		this.apiUrl = apiUrl;
		this.link = link;
		this.type = type;
		this.orderId = orderId;
		this.selected = selected;
	}
	
	

	public Category(int id, String name, Integer orderId, Integer selected) {
		super();
		this.id = id;
		this.name = name;
		this.orderId = orderId;
		this.selected = selected;
	}



	public Category() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String toString() {
		return "Category [id=" + this.id + ", name=" + this.name
				+ ", selected=" + this.selected + "]";
	}
	
}
