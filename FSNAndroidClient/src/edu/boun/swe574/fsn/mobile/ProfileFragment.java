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
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.async.ShowProfileTask;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfileOfSelf;

public class ProfileFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private boolean self;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ProfileFragment newInstance(int sectionNumber, boolean self) {
		ProfileFragment fragment = new ProfileFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		fragment.self = self;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
		ShowProfileTask<MainActivity> task = new ShowProfileTask<MainActivity>((MainActivity) getActivity());
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

	@SuppressLint("SimpleDateFormat")
	public void onProfileInformationReceived(BaseResponse result) {
		TextView textViewName = AndroidUtil.getView(getActivity(), R.id.textViewName);
		TextView textViewEmail = AndroidUtil.getView(getActivity(), R.id.textViewEmail);
		TextView textViewLocation = AndroidUtil.getView(getActivity(), R.id.textViewLocation);
		TextView textViewDateOfBirth = AndroidUtil.getView(getActivity(), R.id.textViewDateOfBirth);
		TextView textViewProfileMessage = AndroidUtil.getView(getActivity(), R.id.textViewProfileMessage);
		if (self && result != null && result instanceof ResponseGetProfileOfSelf) {
			ResponseGetProfileOfSelf response = (ResponseGetProfileOfSelf) result;
			FSNUserContext context = FSNUserContext.getInstance(getActivity());
			textViewName.setText(context.getUserName() + " " + context.getUserSurname());
			textViewEmail.setText(context.getUserEmail());
			textViewLocation.setText(response.getLocation());
			textViewDateOfBirth.setText(response.getDateOfBirt() != null ? new SimpleDateFormat("MM/dd/yyyy").format(response.getDateOfBirt()) : "N/A");
			textViewProfileMessage.setText(response.getLocation());
		} else {
			// TODO profiles of other people
		}
	}
}