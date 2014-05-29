package tr.edu.boun.swe574.fsn.web.wicket.profile.recipeFeeds;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.profile.recipeListEntry.CRecipeListEntry;

@AuthorizeInstantiation(value = { FsnRoles.USER })
public class RecipeFeeds extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7761888071957256271L;

	// private static Logger logger = Logger.getLogger(RecipeFeeds.class);
	
	

	public RecipeFeeds() {
		CRecipeListEntry recipeListEntry = new CRecipeListEntry("recipeListEntry");
		add(recipeListEntry);
	}

}
