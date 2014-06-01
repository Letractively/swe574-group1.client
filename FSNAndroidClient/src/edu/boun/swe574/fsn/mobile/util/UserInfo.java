package edu.boun.swe574.fsn.mobile.util;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.ws.dto.BaseDTO;

public class UserInfo extends BaseDTO {

	private String email;
	private String name;
	private String profileMessage;
	private String surname;
	private long userId;

	public UserInfo(SoapObject object) {
		super(object);
		if (object != null) {
			this.email = AndroidUtil.convertSoapObjectToPrimitive(object, "email", String.class);
			this.name = AndroidUtil.convertSoapObjectToPrimitive(object, "name", String.class);
			this.profileMessage = AndroidUtil.convertSoapObjectToPrimitive(object, "profileMessage", String.class);
			this.surname = AndroidUtil.convertSoapObjectToPrimitive(object, "surname", String.class);
			this.userId = AndroidUtil.convertSoapObjectToPrimitive(object, "userId", Long.class);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileMessage() {
		return profileMessage;
	}

	public void setProfileMessage(String profileMessage) {
		this.profileMessage = profileMessage;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
