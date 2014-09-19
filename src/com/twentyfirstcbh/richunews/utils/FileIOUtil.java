package com.twentyfirstcbh.richunews.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.text.DecimalFormat;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.graphics.Bitmap;

import com.twentyfirstcbh.richunews.Constant;

public class FileIOUtil {

	public static long getFileSize(File f) {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	public static String formetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
			if (fileSizeString.indexOf(".") == 0) {
				fileSizeString = "0" + fileSizeString;
			}
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			flag = file.delete();
		}
		return flag;
	}

	public static void saveObject2File(Object object, String path) {
		if (null != object && null != path) {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
				oos.writeObject(object);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	public static Object readObjectFromFile(String path) {
		if (null != path) {
			File file = new File(path);
			if (!file.exists())
				return null;

			ObjectInputStream ois = null;

			try {
				ois = new ObjectInputStream(new FileInputStream(file));
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
				return null;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			Object obj = null;
			try {
				obj = ois.readObject();
				ois.close();
			} catch (OptionalDataException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return obj;
		}
		return null;
	}

	public static boolean fileISExists(String path) {
		if (null == path)
			return false;

		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public static void saveFile(Context context, String fileName, String content) {
		if (null != fileName && fileName.length() > 0 && null != content) {
			try {
				OutputStream os = context.openFileOutput(fileName, Context.MODE_PRIVATE);
				OutputStreamWriter osw = new OutputStreamWriter(os);
				osw.write(content);
				osw.close();
				os.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
		}
	}

	public static String loadLocalFile(Context context, String fileName) {
		try {
			InputStream inputStream = context.openFileInput(fileName);
			return convertInputStreamToString(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertInputStreamToString(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		try {
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
			return baos.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 创建APP的根目录
	 * 
	 * @return
	 */
	public static boolean createAppPath() {
		if (Constant.SDCARD_IS_EXIST) {
			File file = new File(Constant.APP_PATH);
			if (!file.exists()) {
				return file.mkdir();
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean createCachePath() {
		if (createAppPath()) {
			File file = new File(Constant.CACHE_PATH);
			if (!file.exists()) {
				return file.mkdir();
			}
			return true;
		}
		return false;
	}

	public static boolean createImgCachePath() {
		if (createCachePath()) {
			File file = new File(Constant.IMG_CACHE_PATH);
			if (!file.exists()) {
				if (file.mkdir()) {
					File file1 = new File(Constant.IMG_CACHE_NOMEDIA_PATH);
					if (!file1.exists()) {
						return file1.mkdir();
					}
					return true;
				} else {
					return false;
				}
			} else {
				File file1 = new File(Constant.IMG_CACHE_NOMEDIA_PATH);
				if (!file1.exists()) {
					return file1.mkdir();
				}
				return true;
			}
		}
		return false;
	}

	public static boolean createFileCachePath() {
		if (createCachePath()) {
			File file = new File(Constant.FILE_CACHE_PATH);
			if (!file.exists()) {
				return file.mkdir();
			}
			return true;
		}
		return false;
	}

	/**
	 * 保存图片
	 * 
	 * @param photoPath
	 * @param mBitmap
	 */
	public static boolean saveBitmap2jpg(String photoPath, Bitmap mBitmap) {
		File f = new File(photoPath);
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean result = mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static byte[] readImage(String path) {
		byte[] tmp = null;
		try {
			tmp = readLocalFileData(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public static byte[] readLocalFileData(String path) throws Exception {
		if (null != path) {
			ByteArrayBuffer buffer = null;
			File file = new File(path);
			if (!file.exists()) {
				return null;
			}
			InputStream inputstream = new FileInputStream(file);
			buffer = new ByteArrayBuffer(1024);
			byte[] tmp = new byte[1024];
			int len;
			while (((len = inputstream.read(tmp)) != -1)) {
				buffer.append(tmp, 0, len);
			}
			inputstream.close();
			return buffer.toByteArray();
		} else {
			return null;
		}
	}

	public static String getAssetString(String asset, Context context) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(asset)));
			String line = null;
			StringBuilder builder = new StringBuilder();
			while (null != (line = bufferedReader.readLine())) {
				builder.append(line).append("\n");
			}
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bufferedReader = null;
		}
		return "";
	}
}
