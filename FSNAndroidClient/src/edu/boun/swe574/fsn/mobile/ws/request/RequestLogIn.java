package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;

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
		SoapObject soapRequest = new SoapObject(FSNWSConstants.NAMESPACE, FSNWSConstants.AUTH_SERVICE_OPERATION_NAME_LOGIN);
		soapRequest.addProperty("email", this.email);
		soapRequest.addProperty("password", this.password);
		return soapRequest;
	}
}
