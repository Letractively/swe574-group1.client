package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

public class FoodForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5486235813024070870L;
	
    private String categoryName;
    private long id;
    private String foodName;
    
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getId() {
		return id;
	}
	public void setId(long ingredientId) {
		this.id = ingredientId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof FoodForm) {
			if(this.id == ((FoodForm)anObject).getId()) {
				return true;
			}
		}
		return false;
	}

    
}
