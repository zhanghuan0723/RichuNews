package com.twentyfirstcbh.richunews;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.twentyfirstcbh.richunews.db.SQLHelper;
import com.twentyfirstcbh.richunews.object.User;
import com.twentyfirstcbh.richunews.utils.ActivityMgrUtils;
import com.twentyfirstcbh.richunews.utils.FileIOUtil;
import com.twentyfirstcbh.richunews.utils.PreferenceUtils;

/**
 * Main application
 * 
 * @author Simon
 * 
 */
public class App extends Application {
	public static final String IMAGE_CACHE_PATH = "richunews/cache/images";
	private static App singleton;
	private static final String EXPIRES = "expires";
	private static final String SID_KEY = "sid";
	private static final String USER_NAME_KEY = "user_name";
	private static final String PUSH_NEWS_KEY = "push_news";
	private static final String NIGHT_MODE_KEY = "night_mode";
	private User user;
	private static Context mContext;
	private static ActivityMgrUtils activityManager;
	private static PreferenceUtils preferenceUtils;
	private static DisplayMetrics DM;
	private SQLHelper sqlHelper;

	@Override
	public void onCreate() {
		super.onCreate();

		init();
	}

	public static App getInstance() {
		return singleton;
	}

	public static Context getContext() {
		return mContext;
	}

	public static ActivityMgrUtils getActivityManager() {
		return activityManager;
	}

	public static PreferenceUtils getPreferenceUtils() {
		return preferenceUtils;
	}
	
    public static DisplayMetrics getDM() {
        return DM;
    }

	/**
	 * Intialize
	 */
	private void init() {
		singleton = this;
		mContext = this.getApplicationContext();

		activityManager = new ActivityMgrUtils();
		preferenceUtils = new PreferenceUtils(mContext);
		DM = getResources().getDisplayMetrics();

		// 创建图片缓存目录
		FileIOUtil.createImgCachePath();

		// 创建数据缓存目录
		FileIOUtil.createFileCachePath();

		initImageLoader(getApplicationContext());

		initUserData();
	}

	private void initUserData() {
		if (isLogin()) {
			if (null == preferenceUtils)
				preferenceUtils = new PreferenceUtils(this);

			if (FileIOUtil.fileISExists(User.CACHE_FILE_PATH)) {
				user = (User) FileIOUtil.readObjectFromFile(User.CACHE_FILE_PATH);
				user.setSid(preferenceUtils.getPreferenceStr(SID_KEY));
			} else {
				user = new User();
				user.setUserName(preferenceUtils.getPreferenceStr(USER_NAME_KEY));
				user.setSid(preferenceUtils.getPreferenceStr(SID_KEY));
			}
		} else {
			FileIOUtil.deleteFile(User.CACHE_FILE_PATH);
		}
	}

	public boolean isLogin() {
		if (null == preferenceUtils)
			preferenceUtils = new PreferenceUtils(this);
		long expires = preferenceUtils.getPreferenceLong(EXPIRES);
		long nowTime = System.currentTimeMillis();
		if (!TextUtils.isEmpty(preferenceUtils.getPreferenceStr(SID_KEY)) && expires > nowTime) {
			return true;
		} else {
			return false;
		}
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.

		// File cacheDir = StorageUtils.getCacheDirectory(context);
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, IMAGE_CACHE_PATH);

		// DisplayImageOptions defaultOptions = new
		// DisplayImageOptions.Builder()
		// .cacheInMemory(true)
		// .cacheOnDisc(true)
		// .build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
		// .writeDebugLogs() // Remove for release app
				.diskCache(new UnlimitedDiscCache(cacheDir)) // default
				// .defaultDisplayImageOptions(defaultOptions)
				.build();

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);

		// close log
		L.writeLogs(false);
	}

	public DisplayImageOptions getOptionsForProgramPhoto(int defImg) {
		return new DisplayImageOptions.Builder().showImageOnLoading(defImg) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(defImg) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(defImg) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.build();
	}

	public DisplayImageOptions getOptionsForProgramBigPhoto(int defImg) {
		return new DisplayImageOptions.Builder().showImageOnLoading(defImg) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(defImg) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(defImg) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.displayer(new RoundedBitmapDisplayer(30)) // 是否设置为圆角，弧度为多少
				.build();
	}
	
	public void setPushNews(boolean pushFlag) {
		if (null == preferenceUtils)
			preferenceUtils = new PreferenceUtils(this);
		preferenceUtils.savePreferenceBoolean(PUSH_NEWS_KEY, pushFlag);
	}
	
	public boolean isPushNews() {
		if (null == preferenceUtils)
			preferenceUtils = new PreferenceUtils(this);
		return preferenceUtils.getPreferenceBoolean(PUSH_NEWS_KEY);
	}
	
	public void setNightMode(boolean flag) {
		if (null == preferenceUtils)
			preferenceUtils = new PreferenceUtils(this);
		preferenceUtils.savePreferenceBoolean(NIGHT_MODE_KEY, flag);
	}

	public boolean isNightMode() {
		if (null == preferenceUtils)
			preferenceUtils = new PreferenceUtils(this);
		return preferenceUtils.getPreferenceBoolean(NIGHT_MODE_KEY);
	}
	
	/** 获取数据库Helper */
	public SQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new SQLHelper(singleton);
		return sqlHelper;
	}
	
	/** 摧毁应用进程时候调用 */
	public void onTerminate() {
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
	}

	public void clearAppCache() {
	}

}
