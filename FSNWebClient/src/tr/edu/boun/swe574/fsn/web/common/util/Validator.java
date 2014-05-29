package tr.edu.boun.swe574.fsn.web.common.util;

import java.util.ArrayList;
import java.util.List;

import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import edu.boun.swe574.fsn.common.client.food.FoodInfo;
import edu.boun.swe574.fsn.common.client.food.IngredientInfo;
import edu.boun.swe574.fsn.common.client.network.RecipeInfo;

public class Validator {

	private static final String UNCATEGORIZED = "Uncategorized";

	public static String fixNullCategory(String category) {
		return category == null ? UNCATEGORIZED : category;
	}

	public static IngredientForm convertToIngredientForm(IngredientInfo ing) {
		IngredientForm form = new IngredientForm();
		form.setAmount(ing.getAmount());
		form.setUnit(ing.getUnit());
		form.setFood(convertToFoodForm(ing.getFood()));
		return form;
	}
	
	public static IngredientForm convertToIngredientForm(edu.boun.swe574.fsn.common.client.network.IngredientInfo ing) {
		IngredientForm form = new IngredientForm();
		form.setAmount(ing.getAmount());
		form.setUnit(ing.getUnit());
		form.setFood(convertToFoodForm(ing.getFood()));
		return form;
	}
	
	public static List<IngredientForm> convertToIngredientFormList(List<IngredientInfo> ingredientInfoList) {
		List<IngredientForm> formList = new ArrayList<IngredientForm>();
		
		if(ingredientInfoList != null) {
			for (IngredientInfo ingredientInfo : ingredientInfoList) {
				formList.add(convertToIngredientForm(ingredientInfo));
			}
		}
		
		return formList;
	}
	
	public static List<IngredientForm> convertToIngredientFormArr(List<edu.boun.swe574.fsn.common.client.network.IngredientInfo> ingredientInfoList) {
		List<IngredientForm> formList = new ArrayList<IngredientForm>();
		
		if(ingredientInfoList != null) {
			for (edu.boun.swe574.fsn.common.client.network.IngredientInfo ingredientInfo : ingredientInfoList) {
				formList.add(convertToIngredientForm(ingredientInfo));
			}
		}
		
		return formList;
	}
	
	public static IngredientInfo convertToIngredientInfo(IngredientForm form) {
		IngredientInfo info = new IngredientInfo();
		
		info.setAmount(form.getAmount());
		info.setUnit(form.getUnit());
		info.setFood(convertToFoodInfo(form.getFood()));
		
		return info;
	}

	public static FoodForm convertToFoodForm(FoodInfo food) {
		FoodForm form = new FoodForm();
		form.setId(food.getFoodId());
		form.setFoodName(food.getFoodName());
		return form;
	}
	
	public static FoodForm convertToFoodForm(edu.boun.swe574.fsn.common.client.network.FoodInfo food) {
		FoodForm form = new FoodForm();
		form.setId(food.getFoodId());
		form.setFoodName(food.getFoodName());
		return form;
	}
	
	public static FoodInfo convertToFoodInfo(FoodForm food) {
		FoodInfo info = new FoodInfo();
		info.setFoodId(food.getId());
		info.setFoodName(food.getFoodName());
		return info;
	}

	public static List<FoodForm> convertToFoodFormList(List<FoodInfo> list) {
		List<FoodForm> formList = new ArrayList<FoodForm>();
		if (list != null) {
			for (FoodInfo info : list) {
				formList.add(convertToFoodForm(info));
			}
		}
		return formList;
	}
	
	public static edu.boun.swe574.fsn.common.client.network.RecipeInfo convertRecipeInfo(edu.boun.swe574.fsn.common.client.food.RecipeInfo info) {
		edu.boun.swe574.fsn.common.client.network.RecipeInfo networkRecipe = new RecipeInfo();
		networkRecipe.setCreateDate(info.getCreateDate());
		networkRecipe.setDirections(info.getDirections());
		networkRecipe.setOwnerName(info.getOwnerName());
		networkRecipe.setOwnerSurname(info.getOwnerSurname());
		networkRecipe.setRating(info.getRating());
		networkRecipe.setRecipeId(info.getRecipeId());
		networkRecipe.setRecipeName(info.getRecipeName());
		networkRecipe.getIngredientList();
		
		return networkRecipe;
	}
}
