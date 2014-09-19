package com.twentyfirstcbh.richunews.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.twentyfirstcbh.richunews.App;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.adapter.ContentAdapter;
import com.twentyfirstcbh.richunews.fragment.base.BaseFragment;
import com.twentyfirstcbh.richunews.object.Category;
import com.twentyfirstcbh.richunews.object.TextArticle;
import com.twentyfirstcbh.richunews.utils.FileIOUtil;
import com.twentyfirstcbh.richunews.utils.JsonUtil;
import com.twentyfirstcbh.richunews.utils.ScreenUtils;
import com.twentyfirstcbh.richunews.widget.NoScrollListView;

/**
 * 分类Fragment
 * 
 * @author Simon
 * 
 */
public class CategoryFragment extends BaseFragment {
	private static final String CATEGORY = "category";
	private Category currCategory;

	private ScrollView sv;
	private ImageView topIV;
	private NoScrollListView lv;

	private ContentAdapter contentAdapter;
	private ArrayList<TextArticle> articles;

	public static CategoryFragment newInstance(Category category) {
		CategoryFragment fragment = new CategoryFragment();
		Bundle args = new Bundle();
		args.putSerializable(CATEGORY, category);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			currCategory = (Category) args.getSerializable(CATEGORY);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.content_view, container, false);
		sv = (ScrollView) v.findViewById(R.id.sv);
		sv.smoothScrollTo(0, 0);
		topIV = (ImageView) v.findViewById(R.id.topIV);
		lv = (NoScrollListView) v.findViewById(R.id.lv);
		int dp_78 = ScreenUtils.dpToPx(mActivity, 78);
		RelativeLayout.LayoutParams lvParams = (LayoutParams) lv.getLayoutParams();
		lvParams.topMargin = -dp_78;
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	private void initData() {
		// request data
		String contentJson = FileIOUtil.getAssetString("content.json", mActivity);
		articles = JsonUtil.getHeadlineData(contentJson);

		ImageLoader.getInstance().displayImage(articles.get(0).getThumbUrl(), topIV,
				App.getInstance().getOptionsForProgramPhoto(R.drawable.content_top_image_default));
		contentAdapter = new ContentAdapter(mActivity, articles);
		lv.setAdapter(contentAdapter);
	}

}
