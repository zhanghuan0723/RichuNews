package com.twentyfirstcbh.richunews.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.twentyfirstcbh.richunews.App;
import com.twentyfirstcbh.richunews.Constant;
import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;
import com.twentyfirstcbh.richunews.utils.FileIOUtil;
import com.twentyfirstcbh.richunews.utils.PublicFunction;
import com.twentyfirstcbh.richunews.widget.SwitchButton;


public class Setting extends BaseActivity implements OnClickListener {
	
	private SwitchButton pushNewsCheckbox, nightModeCheckbox;
	private TextView pushNewsStatusView, nightModeView, cacheSizeView;
	private RelativeLayout shareLayout, clearCacheLayout, feedbackLayout, appRecommendLayout;
	private long cacheSize;
	
	private static final int DELETING = 1;
	private static final int DELETE_COMPLETED = 2;
	private static final int DELETE_NOTHING = 3;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DELETE_NOTHING:
				showMsg("已无缓存数据");
				// closeProgressDialog();
				break;
			case DELETING:
				String fileSize = (String) msg.obj;
				cacheSizeView.setText(fileSize);
				break;
			case DELETE_COMPLETED:
				showMsg("删除成功");
				// setProgressDialogDone("删除成功");
				// displayCacheSize();
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		init();
	}
	
	private void init() {
		pushNewsCheckbox = (SwitchButton) findViewById(R.id.pushNewsCheckbox);
		pushNewsStatusView = (TextView) findViewById(R.id.pushNewsStatusView);
		nightModeCheckbox = (SwitchButton) findViewById(R.id.nightModeCheckbox);
		nightModeView = (TextView) findViewById(R.id.nightModeView);
		
		pushNewsCheckbox.setChecked(App.getInstance().isPushNews());
		if (App.getInstance().isPushNews()) {
			pushNewsStatusView.setText("开");
		} else {
			pushNewsStatusView.setText("关");
		}
		pushNewsCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				setPushNews(isChecked);
			}
			
		});
		
		nightModeCheckbox.setChecked(App.getInstance().isNightMode());
		if (App.getInstance().isNightMode()) {
			nightModeView.setText("开");
		} else {
			nightModeView.setText("关");
		}
		nightModeCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				setNightMode(isChecked);
			}
			
		});
		
		shareLayout = (RelativeLayout) findViewById(R.id.shareLayout);
		shareLayout.setOnClickListener(this);
		clearCacheLayout = (RelativeLayout) findViewById(R.id.clearCacheLayout);
		clearCacheLayout.setOnClickListener(this);
		cacheSizeView = (TextView) findViewById(R.id.cacheSizeView);
		displayCacheSize();
		
		feedbackLayout = (RelativeLayout) findViewById(R.id.feedbackLayout);
		feedbackLayout.setOnClickListener(this);
		
		appRecommendLayout = (RelativeLayout) findViewById(R.id.appRecommendLayout);
		appRecommendLayout.setOnClickListener(this);
	}
	
	private void displayCacheSize() {
		
		GetCacheFileSizeThread getCacheFileSize = new GetCacheFileSizeThread();
		getCacheFileSize.start();
	}
	
	private void setPushNews(boolean flag) {
		App.getInstance().setPushNews(flag);
		if (flag) {
			pushNewsStatusView.setText("开");
		} else {
			pushNewsStatusView.setText("关");
		}
	}
	
	private void setNightMode(boolean flag) {
		App.getInstance().setNightMode(flag);
		if (flag) {
			nightModeView.setText("开");
		} else {
			nightModeView.setText("关");
		}
	}
	
	private void showClearCacheDialog() {
		new AlertDialog.Builder(this).setTitle("提示").setMessage("您确定要清除缓存吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				clearCache();
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	
	private void clearCache() {
		// showProgressDialog("正在删除缓存数据，请稍候");
		List<String> fileList = new ArrayList<String>();
		
		// 获取要保留的启动广告图片
		/*
		if (FileIOUtil.fileISExists(Constant.AD_PATH + Constant.OPENING_AD_FILE_NAME)) {
			OpeningAd openingAd = (OpeningAd) FileIOUtil.readObjectFromFile(Constant.AD_PATH + Constant.OPENING_AD_FILE_NAME);
			if (null != openingAd) {
				long endTime = openingAd.getEndTime();
				long nowTime = System.currentTimeMillis();
				if (nowTime <= endTime) {
					String adFileName = openingAd.getImageUrl().substring(openingAd.getImageUrl().lastIndexOf("/") + 1);
					fileList.add(adFileName);
					fileList.add(Constant.OPENING_AD_FILE_NAME);
				}
			}
		}
		
		// 获取要保留的栏目banner图片
		if (FileIOUtil.fileISExists(MenuList.CACHE_FILE_PATH)) {
			MenuList menuData = (MenuList) FileIOUtil.readObjectFromFile(MenuList.CACHE_FILE_PATH);
			if (null != menuData && null != menuData.getList()) {
				int size = menuData.getList().size();
				for (int i = 0; i < size; i++) {
					String bannerUrl = menuData.getList().get(i).getBannerUrl();
					if (null != bannerUrl) {
						if (bannerUrl.substring(bannerUrl.lastIndexOf(".") + 1).toLowerCase(Locale.CHINA).equals("gif")) {
							String fileName = bannerUrl.substring(bannerUrl.lastIndexOf("/") + 1);
							fileList.add(fileName);
						} else {
							String fileName = String.valueOf(bannerUrl.hashCode());
							fileList.add(fileName);
						}
					}
					if (null != menuData.getList().get(i).getChildList() && menuData.getList().get(i).getChildList().size() > 0) {
						int size1 = menuData.getList().get(i).getChildList().size();
						for (int j = 0; j < size1; j++) {
							String bannerUrl1 = menuData.getList().get(i).getChildList().get(j).getBannerUrl();
							if (null == bannerUrl1) {
								continue;
							} else {
								if (bannerUrl1.substring(bannerUrl1.lastIndexOf(".") + 1).toLowerCase(Locale.CHINA).equals("gif")) {
									String fileName = bannerUrl1.substring(bannerUrl1.lastIndexOf("/") + 1);
									fileList.add(fileName);
								} else {
									String fileName = String.valueOf(bannerUrl1.hashCode());
									fileList.add(fileName);
								}
							}
						}
					}
				}
			}
		}*/
		
		ClearCacheThread clearThread = new ClearCacheThread(handler, fileList);
		clearThread.start();
		
	}
	
	private void updateDeleteStatus(Handler mHandler, int status, long fileSize) {
		String fileSizeStr = FileIOUtil.formetFileSize(fileSize);
		Message msg = new Message();
		msg.what = status;
		msg.obj = fileSizeStr;
		mHandler.sendMessage(msg);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.shareLayout:
			
			break;
		case R.id.clearCacheLayout:
			showClearCacheDialog();
			break;
		case R.id.feedbackLayout:
			PublicFunction.sendFeedBack(this, Constant.FEEDBACK_MAIL, "日出新闻客户端", "21新媒体");
			break;
		case R.id.appRecommendLayout:
			
			break;
		}
	}
	
	private class GetCacheFileSizeThread extends Thread {
		
		@Override
		public void run() {
			
			if (Constant.SDCARD_IS_EXIST) {
				File file = new File(Constant.APP_PATH);
				cacheSize = FileIOUtil.getFileSize(file);
			}
			final String sizeFormat = FileIOUtil.formetFileSize(cacheSize);
			handler.post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					cacheSizeView.setText(sizeFormat);
				}
			});
			
		}
	}
	
	private class ClearCacheThread extends Thread {
		private Handler mHandler;
		private List<String> fileList;
		public ClearCacheThread(Handler handler, List<String> fileList) {
			this.mHandler = handler;
			this.fileList = fileList;
		}
		@Override
		public void run() {
			if (cacheSize > 0) {
				/*
				// 删除广告缓存目录
				File file = new File(Constant.AD_PATH);
				File[] files = file.listFiles();
				if (null != files) {
					for (int i = 0; i < files.length; i++) {
						// 删除子文件
						if (files[i].isFile() && !fileInList(files[i].getName(), fileList)) {
							long fileSize = files[i].length();
							if (FileIOUtil.deleteFile(files[i].getAbsolutePath())) {
								cacheSize = cacheSize - fileSize;
								updateDeleteStatus(mHandler, DELETING, cacheSize);
							}
						}
					}
				}
				
				// 删除图片缓存目录
				file = new File(Constant.IMG_CACHE_PATH);
				files = file.listFiles();
				if (null != files) {
					for (int i = 0; i < files.length; i++) {
						// 删除子文件
						if (files[i].isFile() && !fileInList(files[i].getName(), fileList)) {
							long fileSize = files[i].length();
							if (FileIOUtil.deleteFile(files[i].getAbsolutePath())) {
								cacheSize = cacheSize - fileSize;
								updateDeleteStatus(mHandler, DELETING, cacheSize);
							}
						}
					}
				}
				
				// 删除数字报文章缓存
				List<Newspaper> newspaperList = MyApplication.getInstance().getDBManager().getNewspaperList();
				if (null != newspaperList && newspaperList.size() > 0) {
					int size = newspaperList.size();
					for (int i = 0; i < size; i++) {
						long fileSize = FileIOUtil.deleteNewspaperCache(newspaperList.get(i).getDate());
						if (fileSize > 0) {
							cacheSize = cacheSize - fileSize;
							updateDeleteStatus(mHandler, DELETING, cacheSize);
						}
					}
					MyApplication.getInstance().getDBManager().setNewspaperDownloaded(false);
				}*/
				// 发送删除完成的消息
				updateDeleteStatus(mHandler, DELETE_COMPLETED, cacheSize);
			} else {
				updateDeleteStatus(mHandler, DELETE_NOTHING, cacheSize);
			}
		}
	}
}
