package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

public class RequestLogIn extends BaseRequest {
	private String email;
	private String password;

	public RequestLogIn(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SoapObject toSoapObject() {
		SoapObject soapRequest = super.toSoapObject();
		soapRequest.addProperty("email", this.email);
		soapRequest.addProperty("password", this.password);
		return soapRequest;
	}
}
