package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.FSNServiceUtil;
import edu.boun.swe574.fsn.mobile.ws.request.BaseRequest;
import edu.boun.swe574.fsn.mobile.ws.request.RequestGetProfile;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfile;

public class TaskGetProfile<T extends Activity & ITaskListener> extends AsyncTask<Long, Void, ResponseGetProfile> {

	private T executor;
	private ProgressDialog progressDialog;

	/**
	 * @param executor the activity and the listener which executes this task.
	 */
	public TaskGetProfile(T executor) {
		if (executor != null) {
			this.executor = executor;
			this.progressDialog = new ProgressDialog(executor);
		} else {
			throw new IllegalArgumentException("Parameter activity cannot be null");
		}
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Getting profile information...");
		progressDialog.show();
	}

	@Override
	protected ResponseGetProfile doInBackground(Long... args) {
		if (args == null || args.length == 0) {
			BaseRequest request = new BaseRequest();
			request.setToken(FSNUserContext.getInstance(this.executor).getToken());
			return FSNServiceUtil.getProfileOfSelf(request);
		} else {
			RequestGetProfile request = new RequestGetProfile();
			request.setToken(FSNUserContext.getInstance(this.executor).getToken());
			request.setUserId(args[0]);
			return FSNServiceUtil.getProfileOfOther(request);
		}
	}

	@Override
	protected void onPostExecute(ResponseGetProfile result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.GET_PROFILE, result);
	}
}
