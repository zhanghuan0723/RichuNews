package com.twentyfirstcbh.richunews.object;

import java.util.ArrayList;

public class PhotoArticle extends BaseArticle {

	private static final long serialVersionUID = 9039776051303218989L;
	private ArrayList<Photo> photoList;

	public ArrayList<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ArrayList<Photo> photoList) {
		this.photoList = photoList;
	}
	
}
