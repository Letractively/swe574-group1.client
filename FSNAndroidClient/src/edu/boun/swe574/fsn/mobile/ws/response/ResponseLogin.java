package edu.boun.swe574.fsn.mobile.ws.response;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class ResponseLogin extends BaseResponse {
	private String name;
	private String surname;
	private String token;

	public ResponseLogin(SoapObject object) {
		super(object);
		if (object != null) {
			this.token = AndroidUtil.getSoapObjectProperty(object, "return.token", String.class);
			this.name = AndroidUtil.getSoapObjectProperty(object, "return.name", String.class);
			this.surname = AndroidUtil.getSoapObjectProperty(object, "return.surname", String.class);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
