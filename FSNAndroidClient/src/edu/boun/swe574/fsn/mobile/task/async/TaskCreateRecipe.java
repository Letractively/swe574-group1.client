package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.FSNServiceUtil;
import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;
import edu.boun.swe574.fsn.mobile.ws.request.RequestCreateRecipe;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;

public class TaskCreateRecipe<T extends Activity & ITaskListener> extends AsyncTask<RecipeInfo, Void, BaseResponse> {

	private T executor;
	private ProgressDialog progressDialog;

	/**
	 * @param executor the activity and the listener which executes this task.
	 */
	public TaskCreateRecipe(T executor) {
		if (executor != null) {
			this.executor = executor;
			this.progressDialog = new ProgressDialog(executor);
		} else {
			throw new IllegalArgumentException("Parameter activity cannot be null");
		}
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Creating recipe...");
		progressDialog.show();
	}

	@Override
	protected BaseResponse doInBackground(RecipeInfo... args) {
		// TODO: Pass arguments some other & proper way
		if (args != null && args.length == 1) {
			RequestCreateRecipe request = new RequestCreateRecipe(args[0]);
			request.setToken(FSNUserContext.getInstance(this.executor).getToken());
			return FSNServiceUtil.createRecipe(request);
		} else {
			return null;
		}
	}

	@Override
	protected void onPostExecute(BaseResponse result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.CREATE_RECIPE, result);
	}
}
