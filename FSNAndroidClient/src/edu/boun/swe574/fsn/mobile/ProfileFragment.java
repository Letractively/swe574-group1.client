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

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.task.async.TaskGetProfile;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfile;

public class ProfileFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private long currentUserId = -1;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ProfileFragment newInstance(long currentUserId) {
		ProfileFragment fragment = new ProfileFragment();
		fragment.currentUserId = currentUserId;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
		TaskGetProfile<MainActivity> task = new TaskGetProfile<MainActivity>((MainActivity) getActivity());
		if (currentUserId > 0) {
			task.execute(this.currentUserId);
		} else {
			task.execute();
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(R.string.title_profile);
	}

	@SuppressLint("SimpleDateFormat")
	public void onProfileInformationReceived(ResponseGetProfile result) {
		TextView textViewName = AndroidUtil.getView(getActivity(), R.id.textViewName);
		TextView textViewEmail = AndroidUtil.getView(getActivity(), R.id.textViewEmail);
		TextView textViewLocation = AndroidUtil.getView(getActivity(), R.id.textViewLocation);
		TextView textViewDateOfBirth = AndroidUtil.getView(getActivity(), R.id.textViewDateOfBirth);
		TextView textViewProfileMessage = AndroidUtil.getView(getActivity(), R.id.textViewProfileMessage);
		if (result != null) {
			ResponseGetProfile response = (ResponseGetProfile) result;
			textViewName.setText(response.getName() + " " + response.getSurname());
			textViewEmail.setText("");
			textViewLocation.setText(response.getLocation());
			textViewDateOfBirth.setText(response.getDateOfBirth() != null ? new SimpleDateFormat("MM/dd/yyyy").format(response.getDateOfBirth()) : "N/A");
			textViewProfileMessage.setText(response.getProfileMessage());
		}
	}
}