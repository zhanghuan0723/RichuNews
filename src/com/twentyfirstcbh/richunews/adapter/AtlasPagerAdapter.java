package com.twentyfirstcbh.richunews.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.twentyfirstcbh.richunews.object.Photo;
import com.twentyfirstcbh.richunews.object.PhotoArticle;
import com.twentyfirstcbh.richunews.widget.AtlasContainerView;
import com.twentyfirstcbh.richunews.widget.photoview.scrollerproxy.PhotoView;

/**
 * 图集PagerAdapter
 * 
 * @author Simon
 * 
 */
public class AtlasPagerAdapter extends PagerAdapter {

	private Context mContext;
	private ArrayList<PhotoArticle> mData;

	public AtlasPagerAdapter(Context context, ArrayList<PhotoArticle> data) {
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		View view = null;
		if (position != mData.size() - 1) {
			PhotoView photoView = new PhotoView(mContext);
			// display image
			ImageLoader.getInstance().displayImage(mData.get(position).getThumbUrl(), photoView);

			view = photoView;
		} else {
			ArrayList<Photo> atlasItems = mData.get(position).getPhotoList();
			ArrayList<ArrayList<Photo>> atlasItemList = new ArrayList<ArrayList<Photo>>();
			ArrayList<Photo> items = new ArrayList<Photo>();
			for (int i = 0; i < atlasItems.size(); i++) {
				Photo item = atlasItems.get(i);
				if ((i + 1) % 3 == 0) {
					items.add(item);
					atlasItemList.add(items);
					items = new ArrayList<Photo>();
				} else {
					items.add(item);
					if (items.size() == 2) {
						atlasItemList.add(items);
						items = new ArrayList<Photo>();
					}
				}
			}
			AtlasContainerView atlasView = new AtlasContainerView(mContext, atlasItemList);

			view = atlasView;
		}
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}
