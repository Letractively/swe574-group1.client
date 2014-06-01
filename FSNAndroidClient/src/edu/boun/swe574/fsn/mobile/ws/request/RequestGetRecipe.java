package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;

public class RequestGetRecipe extends BaseRequest {

	private Long recipeId;

	public SoapObject toSoapObject() {
		SoapObject soapRequest = super.toSoapObject(FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_GET_RECIPE);
		soapRequest.addProperty("recipeId", this.recipeId);
		return soapRequest;
	}
}
