package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.FSNServiceUtil;
import edu.boun.swe574.fsn.mobile.util.StringUtil;
import edu.boun.swe574.fsn.mobile.ws.request.RequestLogIn;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseLogin;

public class LoginTask<T extends Activity & ITaskListener> extends AsyncTask<String, Void, Boolean> {

	private T executor;
	private ProgressDialog progressDialog;

	/**
	 * @param executor the activity and the listener which executes this task.
	 */
	public LoginTask(T executor) {
		if (executor != null) {
			this.executor = executor;
			this.progressDialog = new ProgressDialog(executor);
		} else {
			throw new IllegalArgumentException("Parameter activity cannot be null");
		}
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Logging in...");
		progressDialog.show();
	}

	@Override
	protected Boolean doInBackground(String... args) {
		if (args != null && args.length == 2) {
			if (StringUtil.hasText(args[0]) && StringUtil.hasText(args[1])) {
				RequestLogIn request = new RequestLogIn(args[0], args[1]);
				ResponseLogin response = FSNServiceUtil.login(request);
				if (response != null && response.getResultCode() == 0 && StringUtil.hasText(response.getToken())) {
					FSNUserContext.getInstance(this.executor).setEmail(request.getEmail());
					FSNUserContext.getInstance(this.executor).setLoggedIn(true);
					FSNUserContext.getInstance(this.executor).setToken(response.getToken());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.LOGIN, result);
	}
}
