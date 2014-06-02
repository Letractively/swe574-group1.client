package edu.boun.swe574.fsn.mobile;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.boun.swe574.fsn.mobile.R;


public class CreateRecipeFragment extends Fragment {

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static CreateRecipeFragment newInstance() {
		CreateRecipeFragment fragment = new CreateRecipeFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_create_recipe, container, false);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(R.string.title_recipe_create);
	}
	
}