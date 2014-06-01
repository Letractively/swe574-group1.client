package edu.boun.swe574.fsn.mobile.ws.response;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class ResponseGetProfile extends BaseResponse {

	private Date dateOfBirth;
	private boolean followed;
	private String location;
	private String name;
	private String profileMessage;
	private String surname;

	public ResponseGetProfile(SoapObject object) {
		super(object);
		if (object != null) {
			this.dateOfBirth = AndroidUtil.convertSoapObjectToPrimitive(object, "return.dateOfBirt", Date.class);
			this.location = AndroidUtil.convertSoapObjectToPrimitive(object, "return.location", String.class);
			this.profileMessage = AndroidUtil.convertSoapObjectToPrimitive(object, "return.profileMessage", String.class);
			this.name = AndroidUtil.convertSoapObjectToPrimitive(object, "return.name", String.class);
			this.surname = AndroidUtil.convertSoapObjectToPrimitive(object, "return.surname", String.class);
			this.followed = AndroidUtil.convertSoapObjectToPrimitive(object, "return.followed", Boolean.class);
		}
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isFollowed() {
		return followed;
	}

	public void setFollowed(boolean followed) {
		this.followed = followed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

}
