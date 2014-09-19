package com.twentyfirstcbh.richunews.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.twentyfirstcbh.richunews.App;
import com.twentyfirstcbh.richunews.Constant;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseSlidingActivity;
import com.twentyfirstcbh.richunews.fragment.ContentFragment;
import com.twentyfirstcbh.richunews.fragment.LeftMenuFragment;
import com.twentyfirstcbh.richunews.listener.ContentPageChangeListener;
import com.twentyfirstcbh.richunews.listener.SlidingMenuListener;
import com.twentyfirstcbh.richunews.object.Category;
import com.twentyfirstcbh.richunews.object.ChannelManage;

/**
 * 主页面
 * 
 * @author Simon
 * 
 */
public class Main extends BaseSlidingActivity implements ContentPageChangeListener {
	public ContentFragment mContent;
	public LeftMenuFragment lMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContent = ContentFragment.newInstance(getCategoryList());
		mContent.setContentPageChangeListener(this);
		mContent.setSlidingMenuListener(new SlidingMenuListener() {

			@Override
			public void setSlidingMenu(int type) {
				switch (type) {
				case Constant.LEFT_MENU: // 显示左侧边栏
					getSlidingMenu().showMenu();
					break;
				case Constant.CONTENT_MENU: // 显示主体内容
					getSlidingMenu().showContent();
					break;
				}
			}
		});

		lMenu = LeftMenuFragment.newInstance();
		lMenu.setSlidingMenuListener(new SlidingMenuListener() {

			@Override
			public void setSlidingMenu(int type) {
				switch (type) {
				case Constant.LEFT_MENU: // 显示左侧边栏
					getSlidingMenu().showMenu();
					break;
				case Constant.CONTENT_MENU: // 显示主体内容
					getSlidingMenu().showContent();
					break;
				}
			}
		});

		// Content View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();

		// Left Menu View
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.left_menu_frame, lMenu).commit();

	}

	private ArrayList<Category> getCategoryList() {
		ArrayList<Category> categories = ((ArrayList<Category>) ChannelManage.getManage(App.getInstance().getSQLHelper()).getUserChannel());
		return categories;
	}

	@Override
	public void touchModeChange(int position) {
		// 改变SlidingMenu的TouchMode，第一页全屏，其它页边缘
		if (position == 0) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		ShareSDK.stopSDK(this);
	}

	// ----------------------------------------------------- 定义一个变量，来标识是否退出 begin
	private static boolean isExit = false;

	static class mHandler extends Handler {
		WeakReference<Main> mActivity;

		mHandler(Main activity) {
			mActivity = new WeakReference<Main>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (slidingMenu.isMenuShowing()) {
				slidingMenu.showContent();
			} else {
				exit();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(Main.this, R.string.exit_app, Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			new mHandler(this).sendEmptyMessageDelayed(0, 2000);
		} else {
			App.getActivityManager().popAllActivity();
		}
	}
	// ----------------------------------------------------- 定义一个变量，来标识是否退出 end

}
