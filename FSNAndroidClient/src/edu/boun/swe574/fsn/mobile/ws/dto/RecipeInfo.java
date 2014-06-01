package edu.boun.swe574.fsn.mobile.ws.dto;

import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

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
		if (object != null) {
			this.createDate = AndroidUtil.convertSoapObjectToPrimitive(object, "return.createDate", Date.class);
			this.directions = AndroidUtil.convertSoapObjectToPrimitive(object, "return.directions", String.class);
			this.ownRating = AndroidUtil.convertSoapObjectToPrimitive(object, "return.ownRating", Integer.class);
			this.ownerName = AndroidUtil.convertSoapObjectToPrimitive(object, "return.ownerName", String.class);
			this.ownerSurname = AndroidUtil.convertSoapObjectToPrimitive(object, "return.ownerSurname", String.class);
			this.rating = AndroidUtil.convertSoapObjectToPrimitive(object, "return.rating", Integer.class);
			this.recipeId = AndroidUtil.convertSoapObjectToPrimitive(object, "return.recipeId", Long.class);
			this.recipeName = AndroidUtil.convertSoapObjectToPrimitive(object, "return.recipeName", String.class);
			this.ingredientList = AndroidUtil.convertSoapObjectToList(object, "return.xxx", IngredientInfo.class);
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
}
