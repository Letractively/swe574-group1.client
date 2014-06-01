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

import edu.boun.swe574.fsn.mobile.adapter.UserSearchAdapter;
import edu.boun.swe574.fsn.mobile.task.async.TaskSearchUsers;
import edu.boun.swe574.fsn.mobile.util.ResponseSearchForUsers;
import edu.boun.swe574.fsn.mobile.util.UserInfo;

public class SearchUsersFragment extends ListFragment {

	private String query;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 * 
	 * @param query
	 */
	public static SearchUsersFragment newInstance(String query) {
		SearchUsersFragment fragment = new SearchUsersFragment();
		fragment.query = query;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_users, container, false);
		TaskSearchUsers<MainActivity> task = new TaskSearchUsers<MainActivity>((MainActivity) getActivity());
		task.execute(this.query);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(R.string.title_home);
	}

	public void onSearchResultReceived(ResponseSearchForUsers result) {
		if (result != null && result.getUserList() != null) {
			UserSearchAdapter adapter = new UserSearchAdapter(getActivity().getApplicationContext(), result.getUserList());
			setListAdapter(adapter);
			ListView listView = getListView();
			listView.setTextFilterEnabled(true);

			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					UserInfo item = ((UserSearchAdapter) getListAdapter()).getValues().get(position);
					if (item != null) {
						MainActivity activity = ((MainActivity) getActivity());
						activity.setCurrentUserId(item.getUserId());
						activity.onNavigationDrawerItemSelected(-2);
					}
				}
			});

		}
	}
}