package com.twentyfirstcbh.richunews.adapter;

import java.util.ArrayList;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.twentyfirstcbh.richunews.fragment.CategoryFragment;
import com.twentyfirstcbh.richunews.fragment.base.BaseFragment;
import com.twentyfirstcbh.richunews.object.Category;

public class ContentPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Category> mCategoryList;
	/** 要删除的position */
	public int remove_position = -1;

	public ContentPagerAdapter(FragmentManager fm, ArrayList<Category> categoryList) {
		super(fm);
		this.mCategoryList = categoryList;
	}

	@Override
	public BaseFragment getItem(int position) {
		return CategoryFragment.newInstance(mCategoryList.get(position));
	}

	@Override
	public int getCount() {
		return mCategoryList.size();
	}

	/** 设置删除的position */
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	/** 删除频道列表 */
	public void remove() {
		mCategoryList.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}
}
