package edu.boun.swe574.fsn.mobile.ws.request;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;

public class RequestGetProfile extends BaseRequest {

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public SoapObject toSoapObject() {
		SoapObject soapRequest = super.toSoapObject(FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_OTHER);
		soapRequest.addProperty("userId", this.userId);
		return soapRequest;
	}

}
