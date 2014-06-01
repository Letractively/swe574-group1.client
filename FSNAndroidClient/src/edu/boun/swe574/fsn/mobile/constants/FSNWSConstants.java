package edu.boun.swe574.fsn.mobile.constants;

public interface FSNWSConstants {
	String NAMESPACE = "http://ws.backend.fsn.swe574.boun.edu/";
	String URL_AUTH_SERVICE = "http://swe.cmpe.boun.edu.tr:8080/FSN_SERVER/fsnws_auth?wsdl";
	String URL_NETWORK_SERVICE = "http://swe.cmpe.boun.edu.tr:8080/FSN_SERVER/fsnws_network?wsdl";
	String URL_SEARCH_SERVICE = "http://swe.cmpe.boun.edu.tr:8080/FSN_SERVER/fsnws_search?wsdl";
	String URL_FOOD_SERVICE = "swe.cmpe.boun.edu.tr:8080/FSN_SERVER/fsnws_food?wsdl";
	String AUTH_SERVICE_OPERATION_NAME_LOGIN = "login";
	String FOOD_SERVICE_OPERATION_NAME_GET_RECIPE = "getRecipe";
	String NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_SELF = "getProfileOfSelf";
	String NETWORK_SERVICE_OPERATION_NAME_GET_RECIPE_FEED = "getRecipeFeeds";
	String SEARCH_SERVICE_OPERATION_SEARCH_FOR_RECIPES = "searchForRecipes";

}
