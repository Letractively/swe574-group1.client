package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;
import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;

public class RequestCreateRecipe extends BaseRequest {
	private RecipeInfo recipeInfo;

	public RequestCreateRecipe(RecipeInfo recipeInfo) {
		this.recipeInfo = recipeInfo;
	}

	public SoapObject toSoapObject() {
		SoapObject soapRequest = super.toSoapObject(FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_GET_RECIPE);
		soapRequest.addProperty("recipeInfo", this.recipeInfo.toSoapObject(FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_GET_RECIPE));
		return soapRequest;
	}
}
