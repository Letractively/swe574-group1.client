package tr.edu.boun.swe574.fsn.web.common.ws;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.info.RecipeForm;
import tr.edu.boun.swe574.fsn.web.common.info.WebUser;
import tr.edu.boun.swe574.fsn.web.common.util.PropertyReader;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;
import tr.edu.boun.swe574.fsn.web.wicket.food.createRecipe.CreateRecipe;
import edu.boun.swe574.fsn.common.client.auth.AuthService;
import edu.boun.swe574.fsn.common.client.food.BaseServiceResponse;
import edu.boun.swe574.fsn.common.client.food.FoodsService;
import edu.boun.swe574.fsn.common.client.food.RecipeInfo;
import edu.boun.swe574.fsn.common.client.network.NetworkService;
import edu.boun.swe574.fsn.common.client.search.SearchService;

public class WSCaller {
	
	private final static Logger logger = Logger.getLogger(WSCaller.class);
	
	public static AuthService getAuthService() {
    	String authenticationWsURL = PropertyReader.getAuthenticationWsURL();
    	return StubCache.getInstance().getAuthStub(authenticationWsURL);
	}
	
	public static NetworkService getNetworkService() {
    	String networkWsURL = PropertyReader.getNetworkWsURL();
    	return StubCache.getInstance().getNetworkStub(networkWsURL);
	}
	
	public static FoodsService getFoodService() {
		String foodsWsURL = PropertyReader.getFoodsWsURL();
		return StubCache.getInstance().getFoodStub(foodsWsURL);
	}
	
	public static SearchService getSearchService() {
		String searchWsURL = PropertyReader.getSearchWsURL();
		return StubCache.getInstance().getSearchStub(searchWsURL);
	}
	
	public static BaseServiceResponse createRecipe(WebUser user, RecipeForm recipeForm) {
		RecipeInfo recipe = new RecipeInfo();
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(new Date());
		recipe.setCreateDate(new XMLGregorianCalendarImpl(gregorianCalendar));
		recipe.setDirections(recipeForm.getDirections());
		recipe.setOwnerName(user.getFirstName());
		recipe.setOwnerSurname(user.getLastName());
		recipe.setRating(0);
		recipe.setRecipeName(recipeForm.getTitle());
		
		List<IngredientForm> ingredientFormList = recipeForm.getIngredientFormList();
		for (IngredientForm ingredientForm : ingredientFormList) {
			recipe.getIngredientList().add(Validator.convertToIngredientInfo(ingredientForm));
		}
		
		BaseServiceResponse response = getFoodService().createRecipe(user.getToken(), recipe);
		
		if(logger.isDebugEnabled()) {
			logger.debug("Recipe creation result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("Recipe creation result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		return response;
	}

}