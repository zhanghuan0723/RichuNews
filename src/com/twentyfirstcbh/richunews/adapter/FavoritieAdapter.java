package com.twentyfirstcbh.richunews.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.adapter.base.MainBaseAdapter;
import com.twentyfirstcbh.richunews.object.TextArticle;

public class FavoritieAdapter extends MainBaseAdapter<TextArticle> {

	public FavoritieAdapter(Activity context, ArrayList<TextArticle> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.favoritie_item, null);

			holder = new Holder();
			holder.titleTV = (TextView) convertView.findViewById(R.id.titleTV);
			holder.dateTV = (TextView) convertView.findViewById(R.id.dateTV);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		TextArticle article = mList.get(position);
		holder.titleTV.setText(article.getTitle());
		holder.dateTV.setText(article.getUpdateTime());

		return convertView;
	}

	static class Holder {
		TextView titleTV;
		TextView dateTV;
	}
}
