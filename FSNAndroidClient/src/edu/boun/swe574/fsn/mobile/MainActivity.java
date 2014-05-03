package edu.boun.swe574.fsn.mobile;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.context.FSNUserContext;

public class MainActivity extends Activity implements FragmentNavigationDrawer.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private FragmentNavigationDrawer fragmentNavigationDrawer;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private String title;

	/****************************************** LIFECYLCE **********************************************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FSNUserContext fsnContext = FSNUserContext.getInstance(getApplicationContext());
		if (fsnContext != null) {
			if (fsnContext.isLoggedIn()) { // is logged in
				title = String.valueOf(getTitle());
				// String userName = fsnContext.getUserEmail();
				fragmentNavigationDrawer = (FragmentNavigationDrawer) getFragmentManager().findFragmentById(R.id.navigation_drawer);
				// Set up the drawer.
				fragmentNavigationDrawer.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
				return;
			}
		}
		startActivity(new Intent(this, LoginActivity.class));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// // update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, FragmentPlaceHolder.newInstance(position + 1)).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (fragmentNavigationDrawer != null && !fragmentNavigationDrawer.isDrawerOpen()) {
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
			title = getString(R.string.title_section1);
			break;
		case 2:
			title = getString(R.string.title_section2);
			break;
		case 3:
			title = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(title);
	}

}
