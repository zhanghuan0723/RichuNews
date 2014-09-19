package com.twentyfirstcbh.richunews.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.twentyfirstcbh.richunews.App;

public class ScreenUtils {

	public static float density() {
		return App.getDM().density;
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @return
	 */
	public static float screenWidth() {
		return App.getDM().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @return
	 */
	public static float screenHeight() {
		return App.getDM().heightPixels;
	}

	/**
	 * dp转化为px
	 * 
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dpToPx(Context context, float dp) {
		if (context == null) {
			return -1;
		}
		return (int) (dp * density() + 0.5f);
	}

	/**
	 * px转化为dp
	 * 
	 * @param context
	 * @param px
	 * @return
	 */
	public static int pxToDp(Context context, float px) {
		if (context == null) {
			return -1;
		}
		return (int) (px / density() + 0.5f);
	}

	public static float dpToPxInt(Context context, float dp) {
		return (int) (dpToPx(context, dp) + 0.5f);
	}

	public static float pxToDpCeilInt(Context context, float px) {
		return (int) (pxToDp(context, px) + 0.5f);
	}

	public static String getDpiStr() {
		int dpi = App.getDM().densityDpi;
		String dpiStr = "hdpi";
		if (dpi < 160) {
			dpiStr = "ldpi";
		} else if (dpi >= 160 && dpi < 240) {
			dpiStr = "mdpi";
		}
		return dpiStr;
	}

	/**
	 * 隐藏键盘
	 * 
	 * @param activity
	 */
	public static void hide(Activity activity, EditText input) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
	}

}
