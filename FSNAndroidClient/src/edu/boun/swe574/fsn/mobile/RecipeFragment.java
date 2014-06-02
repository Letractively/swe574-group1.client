package edu.boun.swe574.fsn.mobile;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.boun.swe574.fsn.mobile.R;
import edu.boun.swe574.fsn.mobile.task.async.TaskGetRecipe;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.ws.dto.IngredientInfo;
import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetRecipe;

public class RecipeFragment extends Fragment {

	private long recipeId;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static RecipeFragment newInstance(long recipeId) {
		RecipeFragment fragment = new RecipeFragment();
		fragment.recipeId = recipeId;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
		TaskGetRecipe<MainActivity> task = new TaskGetRecipe<MainActivity>((MainActivity) getActivity());
		task.execute(recipeId);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(R.string.title_recipe);
	}

	@SuppressLint("SimpleDateFormat")
	public void onRecipeReceived(ResponseGetRecipe result) {
		if (result != null) {
			TextView recipeHeader = AndroidUtil.getView(getActivity(), R.id.textViewRecipeHeader);
			TextView recipeFooter = AndroidUtil.getView(getActivity(), R.id.textViewRecipeFooter);
			TextView directions = AndroidUtil.getView(getActivity(), R.id.textViewDirectionsValue);
			TextView ingredients = AndroidUtil.getView(getActivity(), R.id.textViewIngredientsValue);

			RecipeInfo recipe = result.getRecipe();
			if (recipe != null) {
				recipeHeader.setText(result.getRecipe().getRecipeName());
				recipeFooter.setText("by " + recipe.getOwnerName() + " " + recipe.getOwnerSurname() + " at " + new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(recipe.getCreateDate()));
				directions.setText(recipe.getDirections());
				if (recipe.getIngredientList() != null) {
					String ingredientvalue = "";
					for (IngredientInfo ingredient : recipe.getIngredientList()) {
						ingredientvalue += ingredient.getAmount() + " " + ingredient.getUnit() + " " + ingredient.getFood().getFoodName() + "\r\n";
					}
					ingredients.setText(ingredientvalue);
				}
			}

		}
	}

}