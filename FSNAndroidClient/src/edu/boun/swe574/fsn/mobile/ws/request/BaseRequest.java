package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;

public class BaseRequest {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SoapObject toSoapObject(String methodName) {
		SoapObject soapRequest = new SoapObject(FSNWSConstants.NAMESPACE, methodName);
		soapRequest.addProperty("token", this.token);
		return soapRequest;
	}

}
