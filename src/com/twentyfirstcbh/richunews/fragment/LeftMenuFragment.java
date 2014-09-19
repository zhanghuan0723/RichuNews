package com.twentyfirstcbh.richunews.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twentyfirstcbh.richunews.App;
import com.twentyfirstcbh.richunews.Constant;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.Guide;
import com.twentyfirstcbh.richunews.activity.Main;
import com.twentyfirstcbh.richunews.adapter.DragAdapter;
import com.twentyfirstcbh.richunews.adapter.OtherAdapter;
import com.twentyfirstcbh.richunews.fragment.base.BaseFragment;
import com.twentyfirstcbh.richunews.listener.SlidingMenuListener;
import com.twentyfirstcbh.richunews.object.Category;
import com.twentyfirstcbh.richunews.object.ChannelManage;
import com.twentyfirstcbh.richunews.widget.DragGrid;
import com.twentyfirstcbh.richunews.widget.OtherGridView;

public class LeftMenuFragment extends BaseFragment implements OnClickListener, OnItemClickListener {
	private View view;
	private GridView gridView;
	private ImageButton addColumn, setting, cancel;
	private Button addOk;
	private LinearLayout ll;
	/** 用户栏目的GRIDVIEW */
	private DragGrid userGridView;
	/** 其它栏目的GRIDVIEW */
	private OtherGridView otherGridView;
	/** 用户栏目列表 */
	private ArrayList<Category> userChannelList = new ArrayList<Category>();
	/** 其它栏目列表 */
	private ArrayList<Category> otherChannelList = new ArrayList<Category>();
	private DragAdapter userAdapter;
	/** 其它栏目对应的适配器 */
	private OtherAdapter otherAdapter;
	/** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
	boolean isMove = false;
	private View fv;
	private LinearLayout ll2;
	private SlidingMenuListener sml;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				gridView.setVisibility(View.VISIBLE);
				ll.setVisibility(View.GONE);
				addColumn.setVisibility(View.VISIBLE);
				addOk.setVisibility(View.GONE);
				fv.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.VISIBLE);
				cancel.setVisibility(View.GONE);
				addOk.setClickable(true);

				// 重新加载底部栏
				((Main) (getActivity() == null ? new Main() : getActivity())).mContent.initSubHeadData(userAdapter.getChannnelLst());
				break;
			}
		};
	};

	public static LeftMenuFragment newInstance() {
		LeftMenuFragment fragment = new LeftMenuFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.left_menu_list, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
	}

	// 初始化界面
	private void initView() {
		ll = (LinearLayout) view.findViewById(R.id.ll);
		userGridView = (DragGrid) view.findViewById(R.id.userGridView);
		otherGridView = (OtherGridView) view.findViewById(R.id.otherGridView);
		fv = view.findViewById(R.id.first_line);
		ll2 = (LinearLayout) view.findViewById(R.id.ll2);
		setting = (ImageButton) view.findViewById(R.id.setting_btn);
		setting.setOnClickListener(this);
		cancel = (ImageButton) view.findViewById(R.id.cancel_btn);
		cancel.setOnClickListener(this);
		addColumn = (ImageButton) view.findViewById(R.id.add_column);
		addColumn.setOnClickListener(this);
		addOk = (Button) view.findViewById(R.id.add_ok);
		addOk.setOnClickListener(this);
		gridView = (GridView) view.findViewById(R.id.GridView);

		// 设置GRIDVIEW的ITEM的点击监听
		gridView.setOnItemClickListener(this);
		otherGridView.setOnItemClickListener(this);
		userGridView.setOnItemClickListener(this);
	}

	// 初始化数据
	private void initData() {
		userChannelList = ((ArrayList<Category>) ChannelManage.getManage(App.getInstance().getSQLHelper()).getUserChannel());
		otherChannelList = ((ArrayList<Category>) ChannelManage.getManage(App.getInstance().getSQLHelper()).getOtherChannel());
		gridView.setAdapter(new DragAdapter(getActivity(), userChannelList));
		userAdapter = new DragAdapter(getActivity(), userChannelList);
		userGridView.setAdapter(userAdapter);

		gridView.setAdapter(userAdapter);

		otherAdapter = new OtherAdapter(getActivity(), otherChannelList);
		otherGridView.setAdapter(otherAdapter);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.add_column:
			gridView.setVisibility(View.GONE);
			ll.setVisibility(View.VISIBLE);
			addColumn.setVisibility(View.GONE);
			addOk.setVisibility(View.VISIBLE);
			fv.setVisibility(View.GONE);
			ll2.setVisibility(View.GONE);
			cancel.setVisibility(View.VISIBLE);
			break;
		case R.id.add_ok:
			addOk.setClickable(false);
			new Thread(new Runnable() {

				@Override
				public void run() {
					saveChannel();
					handler.sendEmptyMessage(0);
				}
			}).start();
			break;
		case R.id.setting_btn:
			Intent intent = new Intent(getActivity(), Guide.class);
			startActivity(intent);
			break;
		case R.id.cancel_btn:
			initData();
			gridView.setVisibility(View.VISIBLE);
			ll.setVisibility(View.GONE);
			addColumn.setVisibility(View.VISIBLE);
			addOk.setVisibility(View.GONE);
			fv.setVisibility(View.VISIBLE);
			ll2.setVisibility(View.VISIBLE);
			cancel.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		// 如果点击的时候，之前动画还没结束，那么就让点击事件无效
		if (isMove) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.GridView:
			((Main) (getActivity() == null ? new Main() : getActivity())).mContent.mViewPager.setCurrentItem(arg2);
			sml.setSlidingMenu(Constant.CONTENT_MENU);
			break;
		case R.id.userGridView:
			// position为 0 的不可以进行任何操作
			if (arg2 != 0) {
				final ImageView moveImageView = getView(arg1);
				if (moveImageView != null) {
					TextView newTextView = (TextView) arg1.findViewById(R.id.text_item);
					final int[] startLocation = new int[2];
					newTextView.getLocationInWindow(startLocation);
					final Category channel = ((DragAdapter) arg0.getAdapter()).getItem(arg2);// 获取点击的频道内容
					otherAdapter.setVisible(false);
					// 添加到最后一个
					otherAdapter.addItem(channel);

					((Main) (getActivity() == null ? new Main() : getActivity())).mContent.categoryList.remove(channel);
					((Main) (getActivity() == null ? new Main() : getActivity())).mContent.pagerAdapter.notifyDataSetChanged();
					new Handler().postDelayed(new Runnable() {
						public void run() {
							try {
								int[] endLocation = new int[2];
								// 获取终点的坐标
								otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
								MoveAnim(moveImageView, startLocation, endLocation, channel, userGridView);
								userAdapter.setRemove(arg2);
							} catch (Exception localException) {
								localException.printStackTrace();
							}
						}
					}, 50L);
				}
			}
			break;
		case R.id.otherGridView:
			final ImageView moveImageView = getView(arg1);
			if (moveImageView != null) {
				TextView newTextView = (TextView) arg1.findViewById(R.id.text_item);
				final int[] startLocation = new int[2];
				newTextView.getLocationInWindow(startLocation);
				final Category channel = ((OtherAdapter) arg0.getAdapter()).getItem(arg2);
				userAdapter.setVisible(false);
				// 添加到最后一个
				userAdapter.addItem(channel);

				((Main) (getActivity() == null ? new Main() : getActivity())).mContent.categoryList.add(channel);
				((Main) (getActivity() == null ? new Main() : getActivity())).mContent.pagerAdapter.notifyDataSetChanged();
				new Handler().postDelayed(new Runnable() {
					public void run() {
						try {
							int[] endLocation = new int[2];
							// 获取终点的坐标
							userGridView.getChildAt(userGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
							MoveAnim(moveImageView, startLocation, endLocation, channel, otherGridView);
							otherAdapter.setRemove(arg2);
						} catch (Exception localException) {
						}
					}
				}, 50L);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 点击ITEM移动动画
	 * 
	 * @param moveView
	 * @param startLocation
	 * @param endLocation
	 * @param moveChannel
	 * @param clickGridView
	 */
	private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final Category moveChannel, final GridView clickGridView) {
		int[] initLocation = new int[2];
		// 获取传递过来的VIEW的坐标
		moveView.getLocationInWindow(initLocation);
		// 得到要移动的VIEW,并放入对应的容器中
		final ViewGroup moveViewGroup = getMoveViewGroup();
		final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
		// 创建移动动画
		TranslateAnimation moveAnimation = new TranslateAnimation(startLocation[0], endLocation[0], startLocation[1], endLocation[1]);
		moveAnimation.setDuration(300L);// 动画时间
		// 动画配置
		AnimationSet moveAnimationSet = new AnimationSet(true);
		moveAnimationSet.setFillAfter(false);// 动画效果执行完毕后，View对象不保留在终止的位置
		moveAnimationSet.addAnimation(moveAnimation);
		mMoveView.startAnimation(moveAnimationSet);
		moveAnimationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				isMove = true;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				moveViewGroup.removeView(mMoveView);
				// instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
				if (clickGridView instanceof DragGrid) {
					otherAdapter.setVisible(true);
					otherAdapter.notifyDataSetChanged();
					userAdapter.remove();
				} else {
					userAdapter.setVisible(true);
					userAdapter.notifyDataSetChanged();
					otherAdapter.remove();
				}
				isMove = false;
			}
		});
	}

	/**
	 * 获取移动的VIEW，放入对应ViewGroup布局容器
	 * 
	 * @param viewGroup
	 * @param view
	 * @param initLocation
	 * @return
	 */
	private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
		int x = initLocation[0];
		int y = initLocation[1];
		viewGroup.addView(view);
		LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mLayoutParams.leftMargin = x;
		mLayoutParams.topMargin = y;
		view.setLayoutParams(mLayoutParams);
		return view;
	}

	/**
	 * 创建移动的ITEM对应的ViewGroup布局容器
	 */
	private ViewGroup getMoveViewGroup() {
		ViewGroup moveViewGroup = (ViewGroup) getActivity().getWindow().getDecorView();
		LinearLayout moveLinearLayout = new LinearLayout(getActivity());
		moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		moveViewGroup.addView(moveLinearLayout);
		return moveLinearLayout;
	}

	/**
	 * 获取点击的Item的对应View，
	 * 
	 * @param view
	 * @return
	 */
	private ImageView getView(View view) {
		view.destroyDrawingCache();
		view.setDrawingCacheEnabled(true);
		Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
		view.setDrawingCacheEnabled(false);
		ImageView iv = new ImageView(getActivity());
		iv.setImageBitmap(cache);
		return iv;
	}

	/** 保存选择后数据库的设置 */
	private void saveChannel() {
		ChannelManage.getManage(App.getInstance().getSQLHelper()).deleteAllChannel();
		ChannelManage.getManage(App.getInstance().getSQLHelper()).saveUserChannel(userAdapter.getChannnelLst());
		ChannelManage.getManage(App.getInstance().getSQLHelper()).saveOtherChannel(otherAdapter.getChannnelLst());
	}

	public void setSlidingMenuListener(SlidingMenuListener listener) {
		this.sml = listener;
	}
}
