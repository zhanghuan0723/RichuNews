package com.twentyfirstcbh.richunews.widget;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.object.Photo;

/**
 * 图集容器
 * 
 * @author Simon
 * 
 */
public class AtlasContainerView extends LinearLayout {

	private Context mContext;
	private ArrayList<ArrayList<Photo>> mAtlasItemList;

	public AtlasContainerView(Context context) {
		super(context);

		initViews();
	}

	public AtlasContainerView(Context context, ArrayList<ArrayList<Photo>> atlasItemList) {
		super(context);
		this.mContext = context;
		this.mAtlasItemList = atlasItemList;

		initViews();
	}

	private void initViews() {
		setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setLayoutParams(params);

		RelativeLayout rl = new RelativeLayout(mContext);
		LayoutParams rlParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		rlParams.bottomMargin = 20;
		rl.setLayoutParams(rlParams);

		ImageView iv = new ImageView(mContext);
		RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ivParams.leftMargin = 20;
		ivParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		iv.setLayoutParams(ivParams);
		iv.setImageResource(R.drawable.atlas_back);
		rl.addView(iv);

		TextView tv = new TextView(mContext);
		RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tvParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		tv.setLayoutParams(tvParams);
		tv.setText(mContext.getString(R.string.recommend_atlas));
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(23.0f);
		rl.addView(tv);

		addView(rl);

		for (int i = 0; i < mAtlasItemList.size(); i++) {
			AtlasItemView atlasItem = new AtlasItemView(mContext, mAtlasItemList.get(i));
			addView(atlasItem);
		}
	}

}
