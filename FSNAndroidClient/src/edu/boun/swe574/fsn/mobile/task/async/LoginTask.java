package edu.boun.swe574.fsn.mobile.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.StringUtil;

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
				String email = args[0];
				// String password = StringUtil.toMD5(args[1]);
				String password = args[1];
				// LoginResponse response = FSNAuthService.login(email, password);
				// if (response != null && response.getResultCode() == 0 && StringUtil.hasText(response.getToken())) {
				// FSNUserContext.getInstance(this.executor).setEmail(email);
				// FSNUserContext.getInstance(this.executor).setLoggedIn(true);
				// FSNUserContext.getInstance(this.executor).setToken(response.getToken());
				// }
				return true;
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
