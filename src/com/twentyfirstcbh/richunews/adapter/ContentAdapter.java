package com.twentyfirstcbh.richunews.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.adapter.base.MainBaseAdapter;
import com.twentyfirstcbh.richunews.object.Photo;
import com.twentyfirstcbh.richunews.object.TextArticle;
import com.twentyfirstcbh.richunews.utils.ScreenUtils;

public class ContentAdapter extends MainBaseAdapter<TextArticle> {

	public ContentAdapter(Activity context, ArrayList<TextArticle> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.content_item, parent, false);

			holder = new ViewHolder();
			holder.categoryLL = (LinearLayout) convertView.findViewById(R.id.categoryLL);
			holder.categoryTV = (TextView) convertView.findViewById(R.id.categoryTV);
			holder.titleTV = (TextView) convertView.findViewById(R.id.titleTV);
			holder.descTV = (TextView) convertView.findViewById(R.id.descTV);
			holder.atlasRL = (RelativeLayout) convertView.findViewById(R.id.atlasRL);
			holder.dateTV = (TextView) convertView.findViewById(R.id.dateTV);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		TextArticle article = mList.get(position);
		holder.titleTV.setText(article.getTitle());
		holder.dateTV.setText(article.getUpdateTime());

		// set background
		if (position == 0) {
			holder.categoryLL.setVisibility(View.VISIBLE);
			holder.categoryLL.getLayoutParams().height = ScreenUtils.dpToPx(mContext, 70);
			convertView.setBackgroundResource(R.drawable.selector_first_article_bg);
		} else {
			holder.categoryLL.setVisibility(View.GONE);
			convertView.setBackgroundResource(R.drawable.selector_other_article_bg);
		}

		// set display view
		switch (article.getType()) {
		case TEXT:
			holder.descTV.setVisibility(View.VISIBLE);
			holder.atlasRL.setVisibility(View.GONE);

			holder.descTV.setText(article.getDesc());
			break;
		case PHOTO:
			holder.descTV.setVisibility(View.GONE);
			holder.atlasRL.setVisibility(View.VISIBLE);
			holder.atlasRL.removeAllViews();

			int screenW = (int) ScreenUtils.screenWidth();
			int perW = screenW / 3 - ScreenUtils.dpToPx(mContext, 18);
			ArrayList<Photo> photos = article.getPhotos();
			for (int i = 0; i < photos.size(); i++) {
				Photo currPhoto = photos.get(i);
				ImageView iv = new ImageView(mContext);
				RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(perW, perW);
				if (i == 0) {
					rlParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				} else if (i == 1) {
					rlParams.addRule(RelativeLayout.CENTER_IN_PARENT);
				} else if (i == 2) {
					rlParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				}
				ImageLoader.getInstance().displayImage(currPhoto.getOriginalPicUrl(), iv);
				holder.atlasRL.addView(iv, rlParams);
			}
			break;
		case VIDEO:
			holder.descTV.setVisibility(View.GONE);
			holder.atlasRL.setVisibility(View.VISIBLE);
			holder.atlasRL.removeAllViews();

			View videoView = mInflater.inflate(R.layout.content_video_item, parent, false);
			ImageView videoIV = (ImageView) videoView.findViewById(R.id.videoIV);
			ImageLoader.getInstance().displayImage(article.getThumbUrl(), videoIV);
			holder.atlasRL.addView(videoView);
			break;
		default:
			break;
		}

		return convertView;
	}

	static class ViewHolder {
		LinearLayout categoryLL;
		TextView categoryTV;
		TextView titleTV;
		TextView descTV;
		RelativeLayout atlasRL;
		TextView dateTV;
	}
}
