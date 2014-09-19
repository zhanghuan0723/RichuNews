package com.twentyfirstcbh.richunews.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.adapter.base.MainBaseAdapter;
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
			holder.digestTV = (TextView) convertView.findViewById(R.id.digestTV);
			holder.atlasRL = (RelativeLayout) convertView.findViewById(R.id.atlasRL);
			holder.dateTV = (TextView) convertView.findViewById(R.id.dateTV);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		TextArticle article = mList.get(position);
		holder.titleTV.setText(article.getTitle());
		holder.digestTV.setText(article.getDesc());
		holder.dateTV.setText(article.getUpdateTime());

		if (position == 0) {
			holder.categoryLL.setVisibility(View.VISIBLE);
			holder.categoryLL.getLayoutParams().height = ScreenUtils.dpToPx(mContext, 70);
			convertView.setBackgroundResource(R.drawable.selector_first_article_bg);
		} else {
			holder.categoryLL.setVisibility(View.GONE);
			convertView.setBackgroundResource(R.drawable.selector_other_article_bg);
		}

		return convertView;
	}

	static class ViewHolder {
		LinearLayout categoryLL;
		TextView categoryTV;
		TextView titleTV;
		TextView digestTV;
		RelativeLayout atlasRL;
		TextView dateTV;
	}
}
