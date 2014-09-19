package com.twentyfirstcbh.richunews.activity.base;

import android.content.Intent;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.twentyfirstcbh.richunews.App;
import com.twentyfirstcbh.richunews.R;

public class BaseSlidingActivity extends SlidingFragmentActivity {
	
	protected SlidingMenu slidingMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 添至栈
		App.getActivityManager().pushActivity(this);

		// customize the SlidingMenu
		slidingMenu = getSlidingMenu();
		slidingMenu.setFadeDegree(0.35f);                                // 设置滑动时的渐变程度
		slidingMenu.setShadowDrawable(R.drawable.shadow);                // 设置阴影图片
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);             // 设置阴影图片的宽度
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);      // 设置划出时主页面显示的剩余宽度
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); // 设置要使菜单滑动，触碰屏幕的范围
	}

	// 跳转
	public void forwardActivity(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);

		// 退出栈
		App.getActivityManager().popActivity(this);
	}

	// 返回
	public void back() {
		App.getActivityManager().popActivity(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		App.getActivityManager().popActivity(this);
	}
}
