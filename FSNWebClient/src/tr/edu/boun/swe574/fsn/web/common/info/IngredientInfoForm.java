package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

public class IngredientInfoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5486235813024070870L;
	
    private String categoryName;
    private long ingredientId;
    private String ingredientName;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
    
}
