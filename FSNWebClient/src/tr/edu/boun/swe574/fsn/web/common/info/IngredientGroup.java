package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientGroup implements Serializable {
	
	private static String SPACE = "&nbsp;";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1614987349878143464L;
	
	private String categoryName;
	private List<IngredientInfoForm> ingredientList;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<IngredientInfoForm> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(List<IngredientInfoForm> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	public void addToIngredientList(IngredientInfoForm i) {
		if(ingredientList == null) {
			ingredientList = new ArrayList<IngredientInfoForm>();
		}
		ingredientList.add(i);
	}
	
	public static List<IngredientGroup> categorize(List<IngredientInfoForm> ingredientList) {

		List<IngredientGroup> groups = new ArrayList<IngredientGroup>();
		
		IngredientGroup group;
		if(ingredientList != null) {
			Map<String, IngredientGroup> categoryHash = new HashMap<String, IngredientGroup>();
			for (IngredientInfoForm ingredient : ingredientList) {
				if(categoryHash.containsKey(ingredient.getCategoryName())) {
					categoryHash.get(ingredient.getCategoryName()).addToIngredientList(ingredient);
				} else {
					group = new IngredientGroup();
					group.setCategoryName(ingredient.getCategoryName());
					group.addToIngredientList(ingredient);
					categoryHash.put(ingredient.getCategoryName(), group);
				}
			}
			groups.addAll(categoryHash.values());
		}
		return groups;
	}
	
	public static String makeIngredientString(IngredientGroup group) {
		StringBuilder sb = new StringBuilder();
		List<IngredientInfoForm> list = group.getIngredientList();
		
		if(list != null) {
			for (IngredientInfoForm ingredient : list) {
				sb.append(ingredient.getIngredientName()).append(SPACE);
			}
		}
		return sb.toString();
	}

}
