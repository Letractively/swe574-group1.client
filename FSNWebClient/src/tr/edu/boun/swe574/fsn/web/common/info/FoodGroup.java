package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodGroup implements Serializable {
	
	private static String SPACE = "&nbsp;";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1614987349878143464L;
	
	private String categoryName;
	private List<FoodForm> ingredientList;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<FoodForm> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(List<FoodForm> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	public void addToIngredientList(FoodForm i) {
		if(ingredientList == null) {
			ingredientList = new ArrayList<FoodForm>();
		}
		ingredientList.add(i);
	}
	
	public static List<FoodGroup> categorize(List<FoodForm> ingredientList) {

		List<FoodGroup> groups = new ArrayList<FoodGroup>();
		
		FoodGroup group;
		if(ingredientList != null) {
			Map<String, FoodGroup> categoryHash = new HashMap<String, FoodGroup>();
			for (FoodForm ingredient : ingredientList) {
				if(categoryHash.containsKey(ingredient.getCategoryName())) {
					categoryHash.get(ingredient.getCategoryName()).addToIngredientList(ingredient);
				} else {
					group = new FoodGroup();
					group.setCategoryName(ingredient.getCategoryName());
					group.addToIngredientList(ingredient);
					categoryHash.put(ingredient.getCategoryName(), group);
				}
			}
			groups.addAll(categoryHash.values());
		}
		return groups;
	}
	
	public static String makeIngredientString(FoodGroup group) {
		StringBuilder sb = new StringBuilder();
		List<FoodForm> list = group.getIngredientList();
		
		if(list != null) {
			for (FoodForm ingredient : list) {
				sb.append(ingredient.getFoodName()).append(SPACE);
			}
		}
		return sb.toString();
	}

}
