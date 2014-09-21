package com.twentyfirstcbh.richunews.adapter;

import java.util.ArrayList;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.twentyfirstcbh.richunews.fragment.CategoryFragment;
import com.twentyfirstcbh.richunews.fragment.base.BaseFragment;
import com.twentyfirstcbh.richunews.object.Category;

public class ContentPagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<Category> mCategoryList;
	/** 要删除的position */
	public int remove_position = -1;

	public ContentPagerAdapter(FragmentManager fm, ArrayList<Category> categoryList) {
		super(fm);
		this.mCategoryList = categoryList;
	}

	@Override
	public BaseFragment getItem(int position) {
		CategoryFragment fragment = CategoryFragment.newInstance(mCategoryList.get(position));
		return fragment;
	}

	@Override
	public int getCount() {
		return mCategoryList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
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
