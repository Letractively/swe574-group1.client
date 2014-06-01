package tr.edu.boun.swe574.fsn.web.common.ws;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.info.RecipeForm;
import tr.edu.boun.swe574.fsn.web.common.info.UserInfoForm;
import tr.edu.boun.swe574.fsn.web.common.info.WebUser;
import tr.edu.boun.swe574.fsn.web.common.util.PropertyReader;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import edu.boun.swe574.fsn.common.client.auth.AuthService;
import edu.boun.swe574.fsn.common.client.food.BaseServiceResponse;
import edu.boun.swe574.fsn.common.client.food.CreateNewVersionOfRecipeResponse;
import edu.boun.swe574.fsn.common.client.food.CreateRecipeResponse;
import edu.boun.swe574.fsn.common.client.food.FoodInfo;
import edu.boun.swe574.fsn.common.client.food.FoodsService;
import edu.boun.swe574.fsn.common.client.food.IngredientInfo;
import edu.boun.swe574.fsn.common.client.food.RecipeInfo;
import edu.boun.swe574.fsn.common.client.network.FoodList;
import edu.boun.swe574.fsn.common.client.network.NetworkService;
import edu.boun.swe574.fsn.common.client.search.GetRecipeFeedsResponse;
import edu.boun.swe574.fsn.common.client.search.LongArray;
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
	
	/**
	 * Call createRecipeService
	 * @param user
	 * @param recipeForm
	 * @return
	 */
	public static CreateRecipeResponse createRecipe(WebUser user, RecipeForm recipeForm) {
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
		
		CreateRecipeResponse response = getFoodService().createRecipe(user.getToken(), recipe);
		
		if(logger.isDebugEnabled()) {
			logger.debug("Recipe creation result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("Recipe creation result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		return response;
	}
	
	public static edu.boun.swe574.fsn.common.client.network.BaseServiceResponse addToBL(String token, List<FoodInfo> ingredientsSelected) {
		
		FoodList foodList = new FoodList(); 
		edu.boun.swe574.fsn.common.client.network.FoodInfo food;
		for (FoodInfo foodInfo : ingredientsSelected) {
			food = new edu.boun.swe574.fsn.common.client.network.FoodInfo();
			food.setFoodId(foodInfo.getFoodId());
			food.setFoodName(foodInfo.getFoodName());
			foodList.getList().add(food);
		}
		edu.boun.swe574.fsn.common.client.network.BaseServiceResponse response = getNetworkService().addToBlacklist(token, foodList);
		
		if(logger.isDebugEnabled()) {
			logger.debug("addToBL service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("addToBL service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		return response;
	}
	
	public static edu.boun.swe574.fsn.common.client.network.BaseServiceResponse updateProfile(String token, UserInfoForm form) {
		String dateString = null;
		if(form.getBirthdate() != null) {
			dateString = DateUtil.getDateString(form.getBirthdate(), DateUtil.DATE_FORMAT_MM_DD_YYYY);
		}
		
		edu.boun.swe574.fsn.common.client.network.BaseServiceResponse response = getNetworkService().editProfile(token, form.getLocation(), dateString, form.getProfileMessage());
		
		if(logger.isDebugEnabled()) {
			logger.debug("editProfile service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("editProfile service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		return response;
	}
	
	public static edu.boun.swe574.fsn.common.client.network.BaseServiceResponse deleteFromBL(String token, FoodForm food) {
		FoodList foodList = new FoodList(); 
		edu.boun.swe574.fsn.common.client.network.FoodInfo foodInfo = new edu.boun.swe574.fsn.common.client.network.FoodInfo();
		foodInfo.setFoodId(food.getId());
		foodInfo.setFoodName(food.getFoodName());
		foodList.getList().add(foodInfo);
		edu.boun.swe574.fsn.common.client.network.BaseServiceResponse response = getNetworkService().removeFromBlacklist(token, foodList);
		
		if(logger.isDebugEnabled()) {
			logger.debug("removeFromBlacklist service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("removeFromBlacklist service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		return response;
	}
	
	public static edu.boun.swe574.fsn.common.client.network.BaseServiceResponse updatePhoto(String token, byte[] image) {
		edu.boun.swe574.fsn.common.client.network.BaseServiceResponse response = getNetworkService().updatePhoto(token, image);
		
		if(logger.isDebugEnabled()) {
			logger.debug("updatePhoto service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("updatePhoto service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		return response;
	}
	
	public static edu.boun.swe574.fsn.common.client.network.BaseServiceResponse deletePhoto(String token) {
		edu.boun.swe574.fsn.common.client.network.BaseServiceResponse response = getNetworkService().deletePhoto(token);
		
		if(logger.isDebugEnabled()) {
			logger.debug("deletePhoto service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("deletePhoto service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		
		

		return response;
	}
	
	public static CreateNewVersionOfRecipeResponse createNewVersionOfRecipe(WebUser user, RecipeForm recipeForm, long parentRecipeId) {
		RecipeInfo recipe = new RecipeInfo();
		
		try {
			recipe.setCreateDate(DateUtil.getXMLDate());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		
		recipe.setDirections(recipeForm.getDirections());
		
		recipe.setOwnerName(user.getFirstName());
		recipe.setOwnerSurname(user.getLastName());
		recipe.setRating(0);
		recipe.setRecipeName(recipeForm.getTitle());
		
		List<IngredientForm> ingredientFormList = recipeForm.getIngredientFormList();
		
		if(ingredientFormList != null) {
			for (IngredientForm ingredientForm : ingredientFormList) {
				IngredientInfo ingredient = Validator.convertToIngredientInfo(ingredientForm);
				recipe.getIngredientList().add(ingredient);
			}
		}
		
		CreateNewVersionOfRecipeResponse response = getFoodService().createNewVersionOfRecipe(user.getToken(), recipe, parentRecipeId, recipeForm.getRevisionNote());
		if(logger.isDebugEnabled()) {
			logger.debug("createNewVersionOfRecipe service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		}
		System.out.println("createNewVersionOfRecipe service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		return response;
	}
	
	public static GetRecipeFeedsResponse searchRecipes(String token, List<FoodInfo> ingredientsSelected) {
		LongArray array = new LongArray();
		for (FoodInfo foodInfo : ingredientsSelected) {
			array.getItem().add(foodInfo.getFoodId());
		}
		System.out.println("Calling searchForRecipes. Parameters-> Token:" + token + " foodIds:" + array.getItem());
		GetRecipeFeedsResponse response = getSearchService().searchForRecipes(token, array);
		System.out.println("searchForRecipes service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
		return response;
	}
}