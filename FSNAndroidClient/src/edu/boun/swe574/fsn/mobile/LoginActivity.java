package edu.boun.swe574.fsn.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.task.async.LoginTask;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class LoginActivity extends Activity implements ITaskListener {
	/****************************************** LIFECYLCE **********************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	@Override
	public <T> void onTaskComplete(TaskResultType type, T result) {
		if (type == TaskResultType.LOGIN) {
			afterLoginTaskCompleted((Boolean) result);
		}
	}

	/****************************************** ACTIONS ********************************************/

	public void onButtonLoginClicked(View view) {
		EditText email = AndroidUtil.getView(this, R.id.editTextEmail);
		EditText password = AndroidUtil.getView(this, R.id.editTextPassword);
		if (AndroidUtil.hasText(email) && AndroidUtil.hasText(password)) {
			new LoginTask<LoginActivity>(this).execute(email.getText().toString(), password.getText().toString());
		}
	}

	public void onTextViewSignUpClicked(View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://swe.cmpe.boun.edu.tr:8080/FSN_WEB/register")));
	}

	public void onTextViewHelpClicked(View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://swe.cmpe.boun.edu.tr:8080/FSN_WEB/register")));
	}

	/****************************************** OTHER ********************************************/

	private void afterLoginTaskCompleted(Boolean result) {
		if (Boolean.TRUE.equals(result)) {
			EditText email = AndroidUtil.getView(this, R.id.editTextEmail);
			FSNUserContext.getInstance(getApplicationContext()).setLoggedIn(true);
			FSNUserContext.getInstance(getApplicationContext()).setEmail(email.getText().toString());
			startActivity(new Intent(this, MainActivity.class));
		} else {

		}
	}

}
