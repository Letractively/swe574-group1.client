package tr.edu.boun.swe574.fsn.web.common.util;

import java.util.ArrayList;
import java.util.List;

import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import edu.boun.swe574.fsn.common.client.food.FoodInfo;
import edu.boun.swe574.fsn.common.client.food.IngredientInfo;

public class Validator {

	private static final String UNCATEGORIZED = "Uncategorized";

	public static String fixNullCategory(String category) {
		return category == null ? UNCATEGORIZED : category;
	}

	public static IngredientForm convertToIngredientForm(IngredientInfo ing) {
		IngredientForm form = new IngredientForm();

		return null;
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
}
