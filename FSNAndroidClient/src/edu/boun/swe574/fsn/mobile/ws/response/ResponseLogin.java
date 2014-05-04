package edu.boun.swe574.fsn.mobile.ws.response;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class ResponseLogin extends BaseResponse {

	public ResponseLogin(SoapObject object) {
		super(object);
		if (object != null) {
			this.token = AndroidUtil.getSoapObjectProperty(object, "return.token", String.class);
		}
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
