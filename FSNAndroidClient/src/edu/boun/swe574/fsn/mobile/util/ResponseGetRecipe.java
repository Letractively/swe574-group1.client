package edu.boun.swe574.fsn.mobile.util;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.ws.dto.RecipeInfo;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;

public class ResponseGetRecipe extends BaseResponse {
	protected RecipeInfo recipe;

	protected ResponseGetRecipe(SoapObject object) {
		super(object);
		if (object != null) {
			this.recipe = new RecipeInfo(AndroidUtil.convertSoapObjectToPrimitive(object, "return.recipe", SoapObject.class));
		}
	}

	public RecipeInfo getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeInfo recipe) {
		this.recipe = recipe;
	}

}
