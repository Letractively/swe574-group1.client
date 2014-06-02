package edu.boun.swe574.fsn.mobile.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import edu.boun.swe574.fsn.mobile.LoginActivity;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.ws.dto.BaseDTO;

public class AndroidUtil {

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(Activity activity, int id) {
		return (T) activity.findViewById(id);
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(View view, int id) {
		return (T) view.findViewById(id);
	}

	public static <T extends TextView> boolean hasText(T view) {
		return view != null && view.getText() != null && StringUtil.hasText(view.getText().toString());
	}

	public static boolean checkLoggedIn(Activity activity) {
		FSNUserContext fsnContext = FSNUserContext.getInstance(activity.getApplicationContext());
		if (fsnContext != null && !fsnContext.isLoggedIn()) {
			activity.startActivity(new Intent(activity, LoginActivity.class));
			return false;
		}
		return true;
	}

	@SuppressLint({ "UseValueOf", "DefaultLocale", "SimpleDateFormat" })
	@SuppressWarnings("unchecked")
	public static <T> T convertSoapObjectToPrimitive(SoapObject object, String propertyPath, Class<T> clas) {
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
								return convertSoapObjectToPrimitive((SoapObject) property, propertyPath.replaceFirst(name.concat("."), ""), clas);
							} else {
								return (T) property;
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
							} else if (clas == Date.class) {
								value = new SimpleDateFormat("yyyy-MM-dd").parse(stringValue);
							} else if (clas == Boolean.class) {
								value = new Boolean(stringValue.toLowerCase());
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

	public static <T extends BaseDTO> List<T> convertSoapObjectToList(SoapObject object, String propertyPath, Class<T> clas) {
		List<T> list = new ArrayList<T>();
		SoapObject property = object;
		try {
			if (object != null && StringUtil.hasText(propertyPath)) {
				if (propertyPath.contains(".")) {
					property = convertSoapObjectToPrimitive(object, propertyPath.substring(0, propertyPath.lastIndexOf(".")), SoapObject.class);
				}
				String propertyName = propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
				if (property != null && StringUtil.hasText(propertyName)) {
					for (int i = 0; i < property.getPropertyCount(); i++) {
						PropertyInfo propertyInfo = new PropertyInfo();
						property.getPropertyInfo(i, propertyInfo);
						Object itemObject = property.getProperty(i);
						if (itemObject != null && itemObject instanceof SoapObject && propertyName.equals(propertyInfo.getName())) {
							list.add(clas.getConstructor(SoapObject.class).newInstance(itemObject));
						}
					}
				}
			}
		} catch (Exception e) {
			return list;
		}
		return list;
	}

	public static void showToastShort(Context applicationContext, String string) {
		Toast toast = Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT);
		toast.show();
	}

	public static void showToastlong(Context applicationContext, String string) {
		Toast toast = Toast.makeText(applicationContext, string, Toast.LENGTH_LONG);
		toast.show();
	}

}
