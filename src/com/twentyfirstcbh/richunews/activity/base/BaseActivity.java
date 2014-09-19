package com.twentyfirstcbh.richunews.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.ShareSDK;

import com.twentyfirstcbh.richunews.App;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.share.onekeyshare.OnekeyShare;
import com.twentyfirstcbh.richunews.utils.PublicFunction;

/**
 * Activity基类
 * 
 * @author Simon
 * 
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 添至栈
		App.getActivityManager().pushActivity(this);
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

	public void showMsg(String msg) {
		PublicFunction.showMsg(this, msg);
	}

	public void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/assets/dongwu-ico200.jpg");
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

}
