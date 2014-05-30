package tr.edu.boun.swe574.fsn.web.wicket.food.viewRecipe;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.food.recipeEntry.CRecipeEntry;
import edu.boun.swe574.fsn.common.client.network.RecipeInfo;

@AuthorizeInstantiation(value = { FsnRoles.USER })
public class ViewRecipe extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7761888071957256271L;


	public ViewRecipe(RecipeInfo recipeInfo, Long recipeId) {
		CRecipeEntry recipeEntry = new CRecipeEntry("recipeEntry", recipeInfo, recipeId);
		add(recipeEntry);
	}

}
