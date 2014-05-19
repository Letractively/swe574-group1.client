package edu.boun.swe574.fsn.mobile.context;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class FSNUserContext {

	// Keys
	private static final String KEY_IS_LOGGED_IN = "KEY_IS_LOGGED_IN";
	private static final String KEY_EMAIL = "KEY_EMAIL";
	private static final String KEY_TOKEN = "KEY_TOKEN";
	private static final String KEY_NAME = "KEY_NAME";
	private static final String KEY_SURNAME = "KEY_SURNAME";
	// Private statics
	private static FSNUserContext fsmContext = null;
	private static String SHARED_PREFERENCES_NAME = "com.boun.swe.foodsocialnetwork.USER_PREFERENCES";
	// Preferences
	private SharedPreferences sharedPreferences;

	private FSNUserContext(Context context) {
		this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0); // 0 - for private mode
	}

	public static FSNUserContext getInstance(Context context) {
		return fsmContext == null ? fsmContext = new FSNUserContext(context) : fsmContext;
	}

	public String getUserEmail() {
		return sharedPreferences.getString(KEY_EMAIL, null);
	}

	public String getUserName() {
		return sharedPreferences.getString(KEY_NAME, null);
	}

	public String getUserSurname() {
		return sharedPreferences.getString(KEY_SURNAME, null);
	}

	public String getToken() {
		return sharedPreferences.getString(KEY_TOKEN, null);
	}

	public boolean isLoggedIn() {
		return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
	}

	public void setEmail(String userName) {
		set(KEY_EMAIL, userName);
	}

	public void setLoggedIn(boolean loggedIn) {
		set(KEY_IS_LOGGED_IN, loggedIn);
	}

	public void setToken(String token) {
		set(KEY_TOKEN, token);
	}

	public void setUserName(String token) {
		set(KEY_NAME, token);
	}

	public void setUserSurname(String token) {
		set(KEY_SURNAME, token);
	}

	private <T> void set(String key, T value) {
		Editor editor = sharedPreferences.edit();
		if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);
		} else if (value instanceof String) {
			editor.putString(key, (String) value);
		}
		editor.commit();
	}
}
