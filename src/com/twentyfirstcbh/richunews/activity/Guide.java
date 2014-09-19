package com.twentyfirstcbh.richunews.activity;

import android.os.Bundle;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;

/**
 * 引导页
 * 
 * @author Simon
 * 
 */
public class Guide extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		init();
	}

	private void init() {
		boolean isFirstStartApp = true; // App.getPreferenceUtils().getPreferenceBoolean(PreferenceUtils.KEY_IS_FIRST_START_APP);
		if (!isFirstStartApp) {
			// TODO 引导视图
		} else {
			forwardActivity(Start.class);
		}
	}

}
