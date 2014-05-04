package edu.boun.swe574.fsn.mobile.util;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

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

	@SuppressWarnings("unchecked")
	public static <T> T getSoapObjectProperty(SoapObject object, String propertyPath, Class<T> clas) {
		Object property = null;
		try {
			if (object != null && StringUtil.hasText(propertyPath)) {
				String[] path = propertyPath.split("\\.");
				for (int i = 0; i < path.length; i++) {
					String name = path[i];
					if (StringUtil.hasText(name) && object.hasProperty(name)) {
						property = object.getProperty(name);
						if (property instanceof SoapObject) {
							if (i + 1 < path.length) {
								return getSoapObjectProperty((SoapObject) property, propertyPath.replaceFirst(name.concat("."), ""), clas);
							}
						} else if (property instanceof SoapPrimitive) {
							String stringValue = object.getPropertyAsString(name);
							Object value = null;
							if (clas == String.class) {
								value = stringValue;
							} else if (clas == Integer.class && StringUtil.hasText(stringValue) && stringValue.matches("\\d+")) {
								value = Integer.parseInt(stringValue);
							} else if (clas == Long.class && StringUtil.hasText(stringValue) && stringValue.matches("\\d+")) {
								value = Long.parseLong(stringValue);
							} else if (clas == Double.class && StringUtil.hasText(stringValue) && stringValue.matches("[\\d\\.]+")) {
								value = Double.parseDouble(stringValue);
							}
							return (T) value;
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
