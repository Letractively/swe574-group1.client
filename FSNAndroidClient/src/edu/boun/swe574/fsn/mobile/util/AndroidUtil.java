package edu.boun.swe574.fsn.mobile.util;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class AndroidUtil {

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(Activity activity, int id) {
		return (T) activity.findViewById(id);
	}

	public static <T extends TextView> boolean hasText(T view) {
		return view != null && view.getText() != null && StringUtil.hasText(view.getText().toString());
	}

}
