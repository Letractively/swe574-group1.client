package edu.boun.swe574.fsn.mobile.ws.dto;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class FoodInfo {
	private String categoryName;
	private Long foodId;
	private String foodName;

	public FoodInfo(SoapObject object) {
		if (object != null) {
			this.categoryName = AndroidUtil.getSoapObjectProperty(object, "categoryName", String.class);
			this.foodId = AndroidUtil.getSoapObjectProperty(object, "foodId", Long.class);
			this.foodName = AndroidUtil.getSoapObjectProperty(object, "foodName", String.class);
		}
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

}
