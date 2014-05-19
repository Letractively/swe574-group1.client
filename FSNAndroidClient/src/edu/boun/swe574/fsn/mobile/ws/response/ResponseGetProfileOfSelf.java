package edu.boun.swe574.fsn.mobile.ws.response;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class ResponseGetProfileOfSelf extends BaseResponse {

	private Date dateOfBirt;
	private String location;
	private String profileMessage;

	// private String image;
	// private String ingredientBlackList;

	public ResponseGetProfileOfSelf(SoapObject object) {
		super(object);
		if (object != null) {
			this.dateOfBirt = AndroidUtil.getSoapObjectProperty(object, "return.dateOfBirt", Date.class);
			this.location = AndroidUtil.getSoapObjectProperty(object, "return.location", String.class);
			this.profileMessage = AndroidUtil.getSoapObjectProperty(object, "return.profileMessage", String.class);
		}
	}

	public Date getDateOfBirt() {
		return dateOfBirt;
	}

	public void setDateOfBirt(Date dateOfBirt) {
		this.dateOfBirt = dateOfBirt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfileMessage() {
		return profileMessage;
	}

	public void setProfileMessage(String profileMessage) {
		this.profileMessage = profileMessage;
	}

}
