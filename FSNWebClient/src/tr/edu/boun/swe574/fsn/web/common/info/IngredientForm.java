package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

public class IngredientForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7160063503750525491L;
	private FoodForm food;
	private String amount;
	private String unit;
	
	public FoodForm getFood() {
		return food;
	}
	public void setFood(FoodForm food) {
		this.food = food;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}
