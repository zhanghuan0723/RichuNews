package com.twentyfirstcbh.richunews.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Simon on 2014/6/5.
 */
public class ActivityMgrUtils {
	private Stack<Activity> activityStack;

	public ActivityMgrUtils() {
	}

	// 将当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			// 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
		Activity activity = null;
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	// 退出栈中所有Activity
	public void popAllActivity() {
		while (!activityStack.empty()) {
			popActivity(activityStack.pop());
		}
		System.exit(0);
	}
}
