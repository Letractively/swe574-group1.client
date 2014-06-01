package edu.boun.swe574.fsn.mobile;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfileOfSelf;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetRecipeFeed;

public class MainActivity extends Activity implements ITaskListener, NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment navigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private String title;
	private ProfileFragment fragmentProfile;
	private NewsfeedFragment fragmenNewsfeed;

	/****************************************** LIFECYLCE **********************************************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (AndroidUtil.checkLoggedIn(this)) { // is logged in
			title = String.valueOf(getTitle());
			// String userName = fsnContext.getUserEmail();
			navigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
			// Set up the drawer.
			navigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
			return;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidUtil.checkLoggedIn(this);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// // update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		switch (position) {
		case 0:
			this.fragmenNewsfeed = NewsfeedFragment.newInstance(position + 1, true);
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmenNewsfeed).commit();
			break;
		case 1:
			this.fragmentProfile = ProfileFragment.newInstance(position + 1, true);
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmentProfile).commit();
			break;
		case 2:
		default:
			FSNUserContext.getInstance(getApplicationContext()).setLoggedIn(false);
			FSNUserContext.getInstance(getApplicationContext()).setEmail(null);
			FSNUserContext.getInstance(getApplicationContext()).setToken(null);
			AndroidUtil.checkLoggedIn(this);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (navigationDrawerFragment != null && !navigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/****************************************** ACTIONS ********************************************/

	/****************************************** OTHER ********************************************/

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			title = getString(R.string.title_home);
			break;
		case 2:
			title = getString(R.string.title_profile);
			break;
		case 3:
			title = getString(R.string.title_logout);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(title);
	}

	@Override
	public <T> void onTaskComplete(TaskResultType type, T result) {
		if (type == TaskResultType.GET_PROFILE_OF_SELF) {
			this.fragmentProfile.onProfileInformationReceived((ResponseGetProfileOfSelf) result);
		} else if (type == TaskResultType.GET_RECIPE_FEEDS) {
			// ResponseGetRecipeFeed response = new ResponseGetRecipeFeed(null);
			// response.setRecipeList(new ArrayList<RecipeInfo>());
			// RecipeInfo recipeInfo = new RecipeInfo(null);
			// recipeInfo.setRecipeName("Test Recipe 1");
			// response.getRecipeList().add(recipeInfo );
			// RecipeInfo recipeInfo2 = new RecipeInfo(null);
			// recipeInfo2.setRecipeName("Test Recipe 2");
			// response.getRecipeList().add(recipeInfo2 );
			this.fragmenNewsfeed.onRecipeFeedReceived((ResponseGetRecipeFeed) result);
		}
	}

}
