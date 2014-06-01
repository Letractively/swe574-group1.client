package edu.boun.swe574.fsn.mobile;

import java.util.ArrayList;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.boun.swe.foodsocialnetwork.R;

import edu.boun.swe574.fsn.mobile.context.FSNUserContext;
import edu.boun.swe574.fsn.mobile.task.ITaskListener;
import edu.boun.swe574.fsn.mobile.task.TaskResultType;
import edu.boun.swe574.fsn.mobile.task.async.TaskCreateRecipe;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.util.ResponseGetRecipe;
import edu.boun.swe574.fsn.mobile.util.ResponseSearchForUsers;
import edu.boun.swe574.fsn.mobile.ws.dto.FoodInfo;
import edu.boun.swe574.fsn.mobile.ws.dto.IngredientInfo;
import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfile;
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
	private RecipeFeedFragment fragmenNewsfeed;
	private RecipeFragment fragmentRecipe;
	private SearchUsersFragment fragmentSearchUsers;
	private CreateRecipeFragment fragmentCreateRecipe;

	private long currentRecipeId;
	private long currentUserId;

	/****************************************** LIFECYLCE **********************************************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (AndroidUtil.checkLoggedIn(this)) { // is logged in

			// Get the intent, verify the action and get the query
			Intent intent = getIntent();
			if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
				String query = intent.getStringExtra(SearchManager.QUERY);
				this.fragmentSearchUsers = SearchUsersFragment.newInstance(query);
				getFragmentManager().beginTransaction().replace(R.id.container, this.fragmentSearchUsers).commit();
			}
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
		case -2:
			this.fragmentProfile = ProfileFragment.newInstance(this.currentUserId);
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmentProfile).commit();
			break;
		case -1:
			this.fragmentRecipe = RecipeFragment.newInstance(currentRecipeId);
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmentRecipe).commit();
			break;
		case 0:
			this.fragmenNewsfeed = RecipeFeedFragment.newInstance();
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmenNewsfeed).commit();
			break;
		case 1:
			this.fragmentProfile = ProfileFragment.newInstance(-1);
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmentProfile).commit();
			break;
		case 2:
			onSearchRequested();
			break;
		case 3:
			this.fragmentCreateRecipe = CreateRecipeFragment.newInstance();
			fragmentManager.beginTransaction().replace(R.id.container, this.fragmentCreateRecipe).commit();
			break;		
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
	public void onButtonCreateRecipeClicked(View view) {
		try{
			EditText titleBox = AndroidUtil.getView(this, R.id.titleBox);
			EditText amountBox1 = AndroidUtil.getView(this, R.id.amountBox1);
			EditText unitBox1 = AndroidUtil.getView(this, R.id.unitBox1);
			EditText foodBox1 = AndroidUtil.getView(this, R.id.foodBox1);
			EditText directionsBox = AndroidUtil.getView(this, R.id.directionsBox);
			if (AndroidUtil.hasText(titleBox) && AndroidUtil.hasText(amountBox1) && AndroidUtil.hasText(unitBox1) 
					&& AndroidUtil.hasText(foodBox1) && AndroidUtil.hasText(directionsBox)) {
				RecipeInfo recipe = new RecipeInfo(null);
				recipe.setCreateDate(new Date());
				recipe.setDirections(directionsBox.getText().toString());
				recipe.setRecipeName(titleBox.getText().toString());
				recipe.setIngredientList(new ArrayList<IngredientInfo>());
				IngredientInfo ingredientInfo = new IngredientInfo(null);
				ingredientInfo.setAmount(Double.parseDouble(amountBox1.getText().toString()));
				ingredientInfo.setUnit(unitBox1.getText().toString());
				FoodInfo food = new FoodInfo(null);
				food.setFoodId(Long.parseLong(foodBox1.getText().toString()));
				ingredientInfo.setFood(food );
				
				
				new TaskCreateRecipe<MainActivity>(this).execute(recipe					);
			}
		}catch(Exception e){
			
		}
	}
	/****************************************** OTHER ********************************************/

	public void onSectionAttached(int titleId) {
		title = getString(titleId);
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(title);
	}

	@Override
	public <T> void onTaskComplete(TaskResultType type, T result) {
		if (type == TaskResultType.GET_PROFILE) {
			this.fragmentProfile.onProfileInformationReceived((ResponseGetProfile) result);
		} else if (type == TaskResultType.GET_RECIPE_FEEDS) {
			this.fragmenNewsfeed.onRecipeFeedReceived((ResponseGetRecipeFeed) result);
		} else if (type == TaskResultType.GET_RECIPE) {
			this.fragmentRecipe.onRecipeReceived((ResponseGetRecipe) result);
		} else if (type == TaskResultType.SEARCH_FOR_USERS) {
			this.fragmentSearchUsers.onSearchResultReceived((ResponseSearchForUsers) result);
		}
	}

	/****************************************** GETTER & SETTERS ********************************************/

	public long getCurrentRecipeId() {
		return currentRecipeId;
	}

	public void setCurrentRecipeId(long currentRecipeId) {
		this.currentRecipeId = currentRecipeId;
	}

	public long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(long currentUserId) {
		this.currentUserId = currentUserId;
	}

}
