package edu.boun.swe574.fsn.mobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;

public class RecipeFeedAdapter extends ArrayAdapter<RecipeInfo> {
	private final Context context;
	private final List<RecipeInfo> values;

	public RecipeFeedAdapter(Context context, List<RecipeInfo> values) {
		super(context, R.layout.listitem_feed, values.toArray(new RecipeInfo[values.size()]));
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.listitem_feed, parent, false);

		TextView textViewRecipeName = AndroidUtil.getView(rowView, R.id.textViewRecipeName);
		TextView textViewRecipeOwner = AndroidUtil.getView(rowView, R.id.textViewRecipeOwner);

		RecipeInfo recipe = values.get(position);
		textViewRecipeName.setText(recipe.getRecipeName());
		textViewRecipeOwner.setText("by " + recipe.getOwnerName() + " " + recipe.getOwnerSurname());

		return rowView;
	}

	public List<RecipeInfo> getValues() {
		return values;
	}
}