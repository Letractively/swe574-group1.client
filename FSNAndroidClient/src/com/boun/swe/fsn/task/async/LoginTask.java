package com.boun.swe.fsn.task.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.boun.swe.fsn.task.ITaskListener;
import com.boun.swe.fsn.task.TaskResultType;

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
		try {
			if (args != null && args.length == 2) {
				if (args[0] != null && !args[0].isEmpty()) {
					if (args[1] != null && args[1].length() == 3) {
						Thread.sleep(3000); // TODO do the real authorization
						return true;
					}
				}
			}
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		this.progressDialog.dismiss();
		this.executor.onTaskComplete(TaskResultType.LOGIN, result);
	}
}
