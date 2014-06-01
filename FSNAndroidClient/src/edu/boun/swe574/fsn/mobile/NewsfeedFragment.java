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
import android.widget.TextView;
import android.widget.Toast;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.adapter.ListItemFeedAdapter;
import edu.boun.swe574.fsn.mobile.task.async.TaskGetRecipeFeed;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetRecipeFeed;

public class NewsfeedFragment extends ListFragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private boolean self;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static NewsfeedFragment newInstance(int sectionNumber, boolean self) {
		NewsfeedFragment fragment = new NewsfeedFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		fragment.self = self;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
		TaskGetRecipeFeed<MainActivity> task = new TaskGetRecipeFeed<MainActivity>((MainActivity) getActivity());
		if (self) {
			task.execute();
		} else {
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}

	public void onRecipeFeedReceived(ResponseGetRecipeFeed result) {
		if (result != null && result.getRecipeList() != null) {
			ListItemFeedAdapter adapter = new ListItemFeedAdapter(getActivity().getApplicationContext(), result.getRecipeList());
			setListAdapter(adapter);
			ListView listView = getListView();
			listView.setTextFilterEnabled(true);

			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// When clicked, show a toast with the TextView text
					Toast.makeText(getActivity().getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				}
			});

		}
	}
}