package edu.boun.swe574.fsn.mobile.ws.dto;

import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class RecipeInfo {
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
			this.createDate = AndroidUtil.getSoapObjectProperty(object, "return.createDate", Date.class);
			this.directions = AndroidUtil.getSoapObjectProperty(object, "return.directions", String.class);
			this.ownRating = AndroidUtil.getSoapObjectProperty(object, "return.ownRating", Integer.class);
			this.ownerName = AndroidUtil.getSoapObjectProperty(object, "return.ownerName", String.class);
			this.ownerSurname = AndroidUtil.getSoapObjectProperty(object, "return.ownerSurname", String.class);
			this.rating = AndroidUtil.getSoapObjectProperty(object, "return.rating", Integer.class);
			this.recipeId = AndroidUtil.getSoapObjectProperty(object, "return.recipeId", Long.class);
			this.recipeName = AndroidUtil.getSoapObjectProperty(object, "return.recipeName", String.class);

			AndroidUtil.getSoapObjectProperty(object, "return.food", List.class);
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
