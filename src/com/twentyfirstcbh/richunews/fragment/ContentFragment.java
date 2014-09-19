package com.twentyfirstcbh.richunews.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.twentyfirstcbh.richunews.Constant;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.adapter.ContentPagerAdapter;
import com.twentyfirstcbh.richunews.fragment.base.BaseFragment;
import com.twentyfirstcbh.richunews.listener.ContentPageChangeListener;
import com.twentyfirstcbh.richunews.listener.SlidingMenuListener;
import com.twentyfirstcbh.richunews.object.Category;
import com.twentyfirstcbh.richunews.utils.ScreenUtils;

/**
 * 内容页Fragment
 * 
 * @author Simon
 * 
 */
public class ContentFragment extends BaseFragment implements OnPageChangeListener {
	public static final String CATEGORY_LIST = "category_list";

	/** 二级栏目 */
	private HorizontalScrollView subHeadView;
	private ImageView subHeadIndicator, subHeadImg;
	private RadioGroup subHeadContent;
	private String[] subHeadTitle;
	private int indicatorWidth;

	/** 栏目集合 */
	public ViewPager mViewPager;
	public ArrayList<Category> categoryList;

	private ContentPageChangeListener mListener;
	private SlidingMenuListener sml;
	/** 距左边的距离 */
	private int currentIndicatorLeft;
	public ContentPagerAdapter pagerAdapter;

	public static ContentFragment newInstance(ArrayList<Category> categoryList) {
		ContentFragment fragment = new ContentFragment();
		Bundle args = new Bundle();
		args.putSerializable(CATEGORY_LIST, categoryList);
		fragment.setArguments(args);
		return fragment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			categoryList = (ArrayList<Category>) args.getSerializable(CATEGORY_LIST);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main, container, false);
		mViewPager = (ViewPager) v.findViewById(R.id.mainVP);
		pagerAdapter = new ContentPagerAdapter(getActivity().getSupportFragmentManager(), categoryList);
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.setOnPageChangeListener(this);

		subHeadView = (HorizontalScrollView) v.findViewById(R.id.subHeadView);
		subHeadImg = (ImageView) v.findViewById(R.id.left_btn);
		subHeadContent = (RadioGroup) v.findViewById(R.id.subHead_content);
		subHeadIndicator = (ImageView) v.findViewById(R.id.subHead_indicator);
		initRadioGroupListener();
		subHeadImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sml.setSlidingMenu(Constant.LEFT_MENU);
			}
		});
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initSubHeadData(categoryList);
	}

	/**
	 * 初始化二级栏目的标题
	 */
	public void initSubHeadData(List<Category> list) {
		if (list != null && list.size() > 0) {
			subHeadTitle = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				subHeadTitle[i] = list.get(i).getName();
			}
			if (null != subHeadTitle && subHeadTitle.length > 0) {
				subHeadView.setVisibility(View.VISIBLE);
				indicatorWidth = subHeadTitle.length >= 4 ? (int) ScreenUtils.screenWidth() / 4 : (int) ScreenUtils.screenWidth()
						/ subHeadTitle.length;

				LayoutParams cursor_Params = subHeadIndicator.getLayoutParams();
				cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
				subHeadIndicator.setLayoutParams(cursor_Params);
				subHeadIndicator.setBackgroundResource(R.color.red);
				subHeadContent.removeAllViews();
				for (int i = 0; i < subHeadTitle.length; i++) {
					RadioButton rb = (RadioButton) mInflater.inflate(R.layout.radiogroup_item, null);
					rb.setId(i);
					rb.setText(subHeadTitle[i]);
					rb.setSingleLine(true);
					rb.setLayoutParams(new LinearLayout.LayoutParams(indicatorWidth, LayoutParams.MATCH_PARENT));
					subHeadContent.addView(rb);
				}
			} else {
				subHeadView.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 二级菜单点击
	 */
	private void initRadioGroupListener() {
		subHeadContent.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (subHeadContent.getChildAt(checkedId) != null) {
					TranslateAnimation animation = new TranslateAnimation(((RadioButton) subHeadContent.getChildAt(checkedId)).getLeft(),
							((RadioButton) subHeadContent.getChildAt(checkedId)).getLeft(), 0f, 0f);
					animation.setInterpolator(new LinearInterpolator());
					animation.setDuration(100);
					animation.setFillAfter(true);
					// 执行位移动画
					subHeadIndicator.startAnimation(animation);

					// 位移到的位置
					if (subHeadTitle != null && subHeadTitle.length > 4) {
						subHeadView.smoothScrollTo((checkedId > 1 ? ((RadioButton) subHeadContent.getChildAt(checkedId)).getLeft() : 0)
								- ((RadioButton) subHeadContent.getChildAt(2)).getLeft(), 0);
					}
					// childShowNumber = checkedId;
					// viewPager.setCurrentItem(checkedId);
					// listView.setVisibility(View.GONE);
					// loadingContainer.setVisibility(View.VISIBLE);
					// String catName = categorys.get(currentTabId).getChildCateList().get(checkedId).getCateName();
					// StatService.onEvent(getActivity()==null?mainActivity:getActivity(), "click_" + catName, "点击" + catName);
					mViewPager.setCurrentItem(checkedId);
				}
			}
		});
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		mListener.touchModeChange(position);

		TranslateAnimation animation = new TranslateAnimation(((RadioButton) subHeadContent.getChildAt(position)).getLeft(),
				((RadioButton) subHeadContent.getChildAt(position)).getLeft(), 0f, 0f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(100);
		animation.setFillAfter(true);
		// 执行位移动画
		subHeadIndicator.startAnimation(animation);

		// 记录当前 下标的距最左侧的距离
		currentIndicatorLeft = ((RadioButton) subHeadContent.getChildAt(position)).getLeft();

		// 位移到的位置
		if (subHeadTitle != null && subHeadTitle.length > 4) {
			subHeadView.smoothScrollTo((position > 1 ? currentIndicatorLeft : 0) - ((RadioButton) subHeadContent.getChildAt(1)).getLeft(),
					0);
		}
	}

	public void setContentPageChangeListener(ContentPageChangeListener listener) {
		this.mListener = listener;
	}

	public void setSlidingMenuListener(SlidingMenuListener listener) {
		this.sml = listener;
	}

}
