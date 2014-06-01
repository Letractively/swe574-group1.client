package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.FSNServiceUtil;
import edu.boun.swe574.fsn.mobile.ws.request.RequestGetRecipe;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;

public class TaskGetRecipe<T extends Activity & ITaskListener> extends AsyncTask<Long, Void, BaseResponse> {

	private T executor;
	private ProgressDialog progressDialog;

	/**
	 * @param executor the activity and the listener which executes this task.
	 */
	public TaskGetRecipe(T executor) {
		if (executor != null) {
			this.executor = executor;
			this.progressDialog = new ProgressDialog(executor);
		} else {
			throw new IllegalArgumentException("Parameter activity cannot be null");
		}
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Getting recipe information...");
		progressDialog.show();
	}

	@Override
	protected BaseResponse doInBackground(Long... args) {
		if (args != null && args.length == 1 && args[0] != null && args[0] > 0) {
			RequestGetRecipe request = new RequestGetRecipe();
			request.setToken(FSNUserContext.getInstance(this.executor).getToken());
			request.setRecipeId(args[0]);
			return FSNServiceUtil.getRecipe(request);
		}
		return null;
	}

	@Override
	protected void onPostExecute(BaseResponse result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.GET_RECIPE, result);
	}
}
