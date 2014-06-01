package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.FSNServiceUtil;
import edu.boun.swe574.fsn.mobile.ws.request.BaseRequest;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;

public class TaskGetRecipeFeed<T extends Activity & ITaskListener> extends AsyncTask<String, Void, BaseResponse> {

	private T executor;
	private ProgressDialog progressDialog;

	/**
	 * @param executor the activity and the listener which executes this task.
	 */
	public TaskGetRecipeFeed(T executor) {
		if (executor != null) {
			this.executor = executor;
			this.progressDialog = new ProgressDialog(executor);
		} else {
			throw new IllegalArgumentException("Parameter activity cannot be null");
		}
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Getting recipe feeds...");
		progressDialog.show();
	}

	@Override
	protected BaseResponse doInBackground(String... args) {
		BaseRequest request = new BaseRequest();
		request.setToken(FSNUserContext.getInstance(this.executor).getToken());
		return FSNServiceUtil.getRecipeFeed(request);
	}

	@Override
	protected void onPostExecute(BaseResponse result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.GET_RECIPE_FEEDS, result);
	}
}
