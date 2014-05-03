package edu.boun.swe574.fsn.mobile.context;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class FSNUserContext {

	// Keys
	private static final String KEY_IS_LOGGED_IN = "KEY_IS_LOGGED_IN";
	private static final String KEY_EMAIL = "KEY_EMAIL";
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

	public boolean isLoggedIn() {
		return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
	}

	public void setEmail(String userName) {
		Editor editor = sharedPreferences.edit();
		editor.putString(KEY_EMAIL, userName);
		editor.commit();
	}

	public void setLoggedIn(boolean loggedIn) {
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(KEY_IS_LOGGED_IN, loggedIn);
		editor.commit();
	}
}
