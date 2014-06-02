package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;

public class RequestSearchForUsers extends BaseRequest {
	private String queryString;

	public SoapObject toSoapObject() {
		SoapObject soapRequest = super.toSoapObject(FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_GET_RECIPE);
		soapRequest.addProperty("queryString", this.queryString);
		return soapRequest;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
