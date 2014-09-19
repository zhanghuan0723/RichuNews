package com.twentyfirstcbh.richunews.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class PreferenceUtils {
	private Context mContext;
	private static final int PRIVATE_MODE = ContextWrapper.MODE_PRIVATE;
	private SharedPreferences preferences;
	private static final String PREF = "richuPreference";

	public PreferenceUtils(Context context) {
		mContext = context;
		preferences = new ContextWrapper(mContext).getSharedPreferences(PREF, PRIVATE_MODE);
	}

	public boolean savePreferenceLong(String PrefKey, long PrefValue) {
		try {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putLong(PrefKey, PrefValue);
			editor.commit();
			return true;
		} catch (Exception ee) {
			return false;
		}
	}

	public boolean savePreferenceInt(String PrefKey, Integer PrefValue) {
		try {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putInt(PrefKey, PrefValue);
			editor.commit();
			return true;
		} catch (Exception ee) {
			return false;
		}
	}

	public boolean savePreferenceBoolean(String PrefKey, boolean PrefValue) {
		try {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean(PrefKey, PrefValue);
			editor.commit();
			return true;
		} catch (Exception ee) {
			return false;
		}
	}

	public boolean savePreferenceStr(String PrefKey, String PrefValue) {
		try {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString(PrefKey, PrefValue);
			editor.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public long getPreferenceLong(String RefKey) {
		try {
			return preferences.getLong(RefKey, 0);
		} catch (Exception ee) {
			return 0;
		}
	}

	public Integer getPreferenceInt(String RefKey) {
		try {
			return preferences.getInt(RefKey, 0);
		} catch (Exception ee) {
			return 0;
		}
	}

	public boolean getPreferenceBoolean(String RefKey) {
		try {
			return preferences.getBoolean(RefKey, false);
		} catch (Exception ee) {
			return false;
		}
	}

	public String getPreferenceStr(String RefKey) {
		try {
			return preferences.getString(RefKey, "");
		} catch (Exception ee) {
			return "";
		}
	}

}