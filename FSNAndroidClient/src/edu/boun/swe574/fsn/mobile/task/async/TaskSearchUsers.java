package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.FSNServiceUtil;
import edu.boun.swe574.fsn.mobile.util.StringUtil;
import edu.boun.swe574.fsn.mobile.ws.request.RequestSearchForUsers;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;

public class TaskSearchUsers<T extends Activity & ITaskListener> extends AsyncTask<String, Void, BaseResponse> {

	private T executor;
	private ProgressDialog progressDialog;

	/**
	 * @param executor the activity and the listener which executes this task.
	 */
	public TaskSearchUsers(T executor) {
		if (executor != null) {
			this.executor = executor;
			this.progressDialog = new ProgressDialog(executor);
		} else {
			throw new IllegalArgumentException("Parameter activity cannot be null");
		}
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Searching for users...");
		progressDialog.show();
	}

	@Override
	protected BaseResponse doInBackground(String... args) {
		if (args != null && args.length == 1 && StringUtil.hasText(args[0]) && args[0].length() > 2) {
			RequestSearchForUsers request = new RequestSearchForUsers();
			request.setToken(FSNUserContext.getInstance(this.executor).getToken());
			request.setQueryString(args[0]);
			return FSNServiceUtil.searchForUsers(request);
		}
		return null;
	}

	@Override
	protected void onPostExecute(BaseResponse result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.SEARCH_FOR_USERS, result);
	}
}
