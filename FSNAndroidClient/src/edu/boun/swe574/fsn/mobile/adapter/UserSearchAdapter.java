package edu.boun.swe574.fsn.mobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.boun.swe574.fsn.mobile.R;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.util.StringUtil;
import edu.boun.swe574.fsn.mobile.util.UserInfo;

public class UserSearchAdapter extends ArrayAdapter<UserInfo> {
	private final Context context;
	private final List<UserInfo> values;

	public UserSearchAdapter(Context context, List<UserInfo> values) {
		super(context, R.layout.listitem_feed, values.toArray(new UserInfo[values.size()]));
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.listitem_user, parent, false);

		TextView textViewUserSearchUserName = AndroidUtil.getView(rowView, R.id.textViewUserSearchUserName);
		TextView textViewUserSearchProfileMessage = AndroidUtil.getView(rowView, R.id.textViewUserSearchProfileMessage);

		UserInfo recipe = values.get(position);
		textViewUserSearchUserName.setText(recipe.getName() + " " + recipe.getSurname());
		textViewUserSearchProfileMessage.setText(StringUtil.hasText(recipe.getProfileMessage()) ? recipe.getProfileMessage() : "");

		return rowView;
	}

	public List<UserInfo> getValues() {
		return values;
	}
}