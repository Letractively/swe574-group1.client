package edu.boun.swe574.fsn.mobile.ws.dto;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;
import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class IngredientInfo extends BaseDTO {
	private Double amount;
	private String unit;
	private FoodInfo food;

	public IngredientInfo(SoapObject object) {
		super(object);
		if (object != null) {
			this.amount = AndroidUtil.convertSoapObjectToPrimitive(object, "amount", Double.class);
			this.unit = AndroidUtil.convertSoapObjectToPrimitive(object, "unit", String.class);
			this.food = new FoodInfo(AndroidUtil.convertSoapObjectToPrimitive(object, "food", SoapObject.class));
		}
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public FoodInfo getFood() {
		return food;
	}

	public void setFood(FoodInfo food) {
		this.food = food;
	}

	public SoapObject toSoapObject(String methodName) {
		SoapObject soapRequest = new SoapObject(FSNWSConstants.NAMESPACE, methodName);
		soapRequest.addProperty("amount", this.amount);
		soapRequest.addProperty("unit", this.unit);
		soapRequest.addProperty("amount", this.food.toSoapObject(methodName));
		return soapRequest;
	}
}
