package edu.boun.swe574.fsn.mobile;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.adapter.RecipeFeedAdapter;
import edu.boun.swe574.fsn.mobile.task.async.TaskGetRecipeFeed;
import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetRecipeFeed;

public class RecipeFeedFragment extends ListFragment {
	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static RecipeFeedFragment newInstance() {
		RecipeFeedFragment fragment = new RecipeFeedFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
		TaskGetRecipeFeed<MainActivity> task = new TaskGetRecipeFeed<MainActivity>((MainActivity) getActivity());
		task.execute();
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(R.string.title_home);
	}

	public void onRecipeFeedReceived(ResponseGetRecipeFeed result) {
		if (result != null && result.getRecipeList() != null && getActivity() != null) {
			RecipeFeedAdapter adapter = new RecipeFeedAdapter(getActivity().getApplicationContext(), result.getRecipeList());
			setListAdapter(adapter);
			ListView listView = getListView();
			listView.setTextFilterEnabled(true);

			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					RecipeInfo item = ((RecipeFeedAdapter) getListAdapter()).getValues().get(position);
					if (item != null) {
						MainActivity activity = ((MainActivity) getActivity());
						activity.setCurrentRecipeId(item.getRecipeId());
						activity.onNavigationDrawerItemSelected(-1);
					}
				}
			});

		}
	}
}