package com.twentyfirstcbh.richunews.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import cn.sharesdk.framework.ShareSDK;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;

public class Start extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		ShareSDK.initSDK(this);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				forwardActivity(Main.class);
				this.cancel();
			}
		}, 2000);
	}

}
