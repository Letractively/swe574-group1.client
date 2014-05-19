package edu.boun.swe574.fsn.mobile.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;
import edu.boun.swe574.fsn.mobile.ws.request.BaseRequest;
import edu.boun.swe574.fsn.mobile.ws.request.RequestLogIn;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfileOfSelf;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseLogin;

public abstract class FSNServiceUtil {

	private static Object callWebService(String url, String methodName, SoapObject request) {
		try {
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE httpTransport = new HttpTransportSE(url);
			httpTransport.call(FSNWSConstants.NAMESPACE + methodName, envelope);
			return envelope.bodyIn;
		} catch (Exception e) {
			return null;
		}
	}

	public static ResponseLogin login(RequestLogIn request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_AUTH_SERVICE, FSNWSConstants.AUTH_SERVICE_OPERATION_NAME_LOGIN, request.toSoapObject());
		if (soapResponse instanceof SoapObject) {
			return new ResponseLogin((SoapObject) soapResponse);
		}
		return null;
	}

	public static ResponseGetProfileOfSelf getProfileOfSelf(BaseRequest request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_AUTH_SERVICE, FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_SELF, request.toSoapObject());
		if (soapResponse instanceof SoapObject) {
			return new ResponseGetProfileOfSelf((SoapObject) soapResponse);
		}
		return null;
	}
}
