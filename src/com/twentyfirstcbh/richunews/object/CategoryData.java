package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;
import java.util.List;

import com.twentyfirstcbh.richunews.Constant;

public class CategoryData extends BaseData implements Serializable {

	private static final long serialVersionUID = 7409879016555239637L;
	private List<Category> categoryList;
	public static final String CACHE_FILE_PATH = Constant.FILE_CACHE_PATH + "categorys";

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
