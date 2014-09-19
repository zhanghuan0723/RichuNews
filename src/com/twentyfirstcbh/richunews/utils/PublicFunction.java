package com.twentyfirstcbh.richunews.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.twentyfirstsq.sdk.device.Device;
import org.twentyfirstsq.sdk.network.WebUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;

import com.twentyfirstcbh.richunews.enums.TimeType;

public class PublicFunction {
	public static String getTimeFromNow(long time) {
		String updateTime = "";
		long currentTime = System.currentTimeMillis();
		long interval = currentTime - time;
		long minutes = interval / 60000;
		if (minutes < 60) {
			minutes = minutes == 0 ? 1 : minutes;
			updateTime = minutes + "分钟前";
		} else {
			minutes = minutes / 60;
			minutes = minutes == 0 ? 1 : minutes;
			if (minutes < 24) {
				updateTime = minutes + "小时前";
			} else {
				minutes = minutes / 24;
				minutes = minutes == 0 ? 1 : minutes;
				updateTime = minutes + "日前";
			}
		}
		return updateTime;
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String getPublishTime(TimeType type, long time) {
		if (time == 0)
			return "";
		Date date = new Date(time);
		SimpleDateFormat dateFormat;
		String timeStr = null;
		switch (type) {
		case DEFAULT:
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			timeStr = dateFormat.format(date);
			break;
		case DEFAULT_CHINESS:
			dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
			timeStr = dateFormat.format(date);
			break;
		case DEFAULT_SHORT:
			dateFormat = new SimpleDateFormat("MM-dd HH:mm");
			timeStr = dateFormat.format(date);
			break;
		case DEFAULT_CHINESS_SHORT:
			dateFormat = new SimpleDateFormat("MM月dd日 HH时mm分");
			timeStr = dateFormat.format(date);
			break;
		case FROMNOW:
			timeStr = PublicFunction.getTimeFromNow(time);
			break;
		}
		
		return timeStr;
	}
	
	/**
	 * 显示提示信息
	 * 
	 * @param context
	 * @param msg
	 *            要显示的内容
	 */
	public static void showMsg(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void sendFeedBack(Context context, String toMail, String mailSubject, String toName) {
		String phoneModel = Device.getMODEL(); // 手机的设备型号
		String phonePlat = Device.getPlatform(); // 手机的系统版本
		String networkType = Device.getNetworkType(context);
		if (WebUtil.isWifi(context))
			networkType = "WIFI";
		String versionName = getVersionName(context);
		versionName = "Android v" + versionName;
		Intent intent = new Intent(Intent.ACTION_SEND);
		String[] tos = { toMail }; // send to someone
		intent.putExtra(Intent.EXTRA_EMAIL, tos);
		intent.putExtra(Intent.EXTRA_TEXT, toName + "：" + "\n\n\n" + "-----------" + "\n" + "设备信息(请保留，用以帮助我们更好地诊断错误):" + "\n" + "设备型号:" + phoneModel + "\n" + "系统版本:" + phonePlat + "\n" + "使用网络:" + networkType);
		intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject + versionName + " 意见反馈");
		intent.setType("plain/text");
		Intent.createChooser(intent, "Choose Email Client");
		context.startActivity(intent);
	}
	
	public static String getVersionName(Context context) {
		try {
			String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
			return versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "unknown";
		}
	}
}
