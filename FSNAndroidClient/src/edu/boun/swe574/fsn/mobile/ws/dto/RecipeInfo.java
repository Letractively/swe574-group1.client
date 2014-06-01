package edu.boun.swe574.fsn.mobile.ws.dto;

import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class RecipeInfo extends BaseDTO {
	private List<IngredientInfo> ingredientList;
	private Date createDate;
	private String directions;
	private Integer ownRating;
	private String ownerName;
	private String ownerSurname;
	private Integer rating;
	private Long recipeId;
	private String recipeName;

	public RecipeInfo(SoapObject object) {
		super(object);
		if (object != null) {
			this.createDate = AndroidUtil.convertSoapObjectToPrimitive(object, "createDate", Date.class);
			this.directions = AndroidUtil.convertSoapObjectToPrimitive(object, "directions", String.class);
			this.ownRating = AndroidUtil.convertSoapObjectToPrimitive(object, "ownRating", Integer.class);
			this.ownerName = AndroidUtil.convertSoapObjectToPrimitive(object, "ownerName", String.class);
			this.ownerSurname = AndroidUtil.convertSoapObjectToPrimitive(object, "ownerSurname", String.class);
			this.rating = AndroidUtil.convertSoapObjectToPrimitive(object, "rating", Integer.class);
			this.recipeId = AndroidUtil.convertSoapObjectToPrimitive(object, "recipeId", Long.class);
			this.recipeName = AndroidUtil.convertSoapObjectToPrimitive(object, "recipeName", String.class);
			this.ingredientList = AndroidUtil.convertSoapObjectToList(object, "ingredientList", IngredientInfo.class);
		}
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public List<IngredientInfo> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<IngredientInfo> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public int getOwnRating() {
		return ownRating;
	}

	public void setOwnRating(int ownRating) {
		this.ownRating = ownRating;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSurname() {
		return ownerSurname;
	}

	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public SoapObject toSoapObject(String methodName) {
		SoapObject soapRequest = new SoapObject(FSNWSConstants.NAMESPACE, methodName);
		soapRequest.addProperty("createDate", this.createDate);
		soapRequest.addProperty("directions", this.directions);
		soapRequest.addProperty("ownRating", this.ownRating);
		soapRequest.addProperty("ownerName", this.ownerName);
		soapRequest.addProperty("ownerSurname", this.ownerSurname);
		soapRequest.addProperty("rating", this.rating);
		soapRequest.addProperty("recipeId", this.recipeId);
		soapRequest.addProperty("recipeName", this.recipeName);
		if (this.ingredientList != null) {
			for (IngredientInfo ingredient : this.ingredientList) {
				if (ingredient != null) {
					soapRequest.addProperty("ingredientList", ingredient.toSoapObject(methodName));
				}
			}
		}
		return soapRequest;
	}
}
