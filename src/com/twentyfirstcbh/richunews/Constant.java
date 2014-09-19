package com.twentyfirstcbh.richunews;

import java.io.File;

import android.os.Environment;

public class Constant {
	/** 是否存在SD卡 */
	public static final boolean SDCARD_IS_EXIST = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

	/** APP的根目录 **/
	public static final String APP_PATH = Environment.getExternalStorageDirectory() + File.separator + "richunews" + File.separator;

	/** 缓存目录 */
	public static final String CACHE_PATH = APP_PATH + "cache" + File.separator;

	/** 图片缓存目录 */
	public static final String IMG_CACHE_PATH = CACHE_PATH + "images" + File.separator;

	public static final String IMG_CACHE_NOMEDIA_PATH = IMG_CACHE_PATH + ".nomedia" + File.separator;

	/** 文件缓存 */
	public static final String FILE_CACHE_PATH = CACHE_PATH + "data" + File.separator;

	/** APP检查更新的时间间隔 */
	public static final long APP_UPDATE_INTEVAL = 1000 * 3600 * 24 * 5;

	public static final String FEEDBACK_MAIL = "feedback@21cbh.com";

	public static final int USER_PHOTO_ASPECT_X = 1; // 剪切头像的比例
	public static final int USER_PHOTO_ASPECT_Y = 1;
	public static final int USER_PHOTO_WIDTH = 200; // 头像宽度
	public static final int USER_PHOTO_HEIGHT = 200; // 头像高度

	public static int rl_id = 1;
	public static int iv_id = 1;

	public static final int LEFT_MENU = 1; // 左侧边栏
	public static final int CONTENT_MENU = 2; // 内容栏

	/** 内容页接口 */
	public static final String CONTENT_URL = "http://appd.21sq.org/headline/getList";
}
