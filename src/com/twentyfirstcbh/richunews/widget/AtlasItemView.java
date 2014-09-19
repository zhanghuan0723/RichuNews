package com.twentyfirstcbh.richunews.widget;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.twentyfirstcbh.richunews.Constant;
import com.twentyfirstcbh.richunews.object.Photo;

/**
 * 图集Item（每行）
 * 
 * @author Simon
 * 
 */
public class AtlasItemView extends LinearLayout {

	private Context mContext;
	private ArrayList<Photo> mAtlasItem;

	public AtlasItemView(Context context) {
		super(context);

		initViews();
	}

	public AtlasItemView(Context context, ArrayList<Photo> atlasItem) {
		super(context);
		this.mContext = context;
		this.mAtlasItem = atlasItem;

		initViews();
	}

	private void initViews() {
		setOrientation(LinearLayout.HORIZONTAL);
		setBaselineAligned(false);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.bottomMargin = 5;
		setLayoutParams(params);

		for (int i = 0; i < mAtlasItem.size(); i++) {
			final Photo atlasItem = mAtlasItem.get(i);

			Constant.rl_id++;
			Constant.iv_id++;
			RelativeLayout rl = new RelativeLayout(mContext);
			rl.setId(Constant.rl_id);
			LinearLayout.LayoutParams rlParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.5f);
			if (i == 1)
				rlParams.leftMargin = 5;
			rl.setLayoutParams(rlParams);

			// IV
			ImageView iv = new ImageView(mContext);
			iv.setId(Constant.iv_id);
			RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 300);
			ivParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
			ivParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			iv.setLayoutParams(ivParams);
			iv.setScaleType(ScaleType.FIT_XY);

			// display image
			ImageLoader.getInstance().displayImage(atlasItem.getOriginalPicUrl(), iv);
			rl.addView(iv);

			// TV
			TextView tv = new TextView(mContext);
			RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tvParams.addRule(RelativeLayout.ALIGN_BOTTOM, iv.getId());
			tvParams.addRule(RelativeLayout.ALIGN_RIGHT, iv.getId());
			tvParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
			tv.setLayoutParams(tvParams);
			tv.setText(atlasItem.getTitle());
			tv.setBackgroundColor(0x80000000);
			tv.setTextColor(getResources().getColor(android.R.color.white));
			tv.setTextSize(17);
			rl.addView(tv);

			rl.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, atlasItem.getLinkUrl(), Toast.LENGTH_SHORT).show();
				}
			});
			addView(rl);
		}
	}
}
