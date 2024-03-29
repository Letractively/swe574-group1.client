package edu.boun.swe574.fsn.mobile.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import edu.boun.swe574.fsn.mobile.constants.FSNWSConstants;
import edu.boun.swe574.fsn.mobile.ws.request.BaseRequest;
import edu.boun.swe574.fsn.mobile.ws.request.RequestCreateRecipe;
import edu.boun.swe574.fsn.mobile.ws.request.RequestGetProfile;
import edu.boun.swe574.fsn.mobile.ws.request.RequestGetRecipe;
import edu.boun.swe574.fsn.mobile.ws.request.RequestLogIn;
import edu.boun.swe574.fsn.mobile.ws.request.RequestSearchForUsers;
import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetProfile;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetRecipe;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseGetRecipeFeed;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseLogin;
import edu.boun.swe574.fsn.mobile.ws.response.ResponseSearchForUsers;

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

	public static ResponseGetProfile getProfileOfSelf(BaseRequest request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_NETWORK_SERVICE, FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_SELF, request.toSoapObject(FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_SELF));
		if (soapResponse instanceof SoapObject) {
			return new ResponseGetProfile((SoapObject) soapResponse);
		}
		return null;
	}

	public static ResponseGetProfile getProfileOfOther(RequestGetProfile request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_NETWORK_SERVICE, FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_OTHER, request.toSoapObject(FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_PROFILE_OF_OTHER));
		if (soapResponse instanceof SoapObject) {
			return new ResponseGetProfile((SoapObject) soapResponse);
		}
		return null;
	}

	public static ResponseSearchForUsers searchForUsers(RequestSearchForUsers request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_NETWORK_SERVICE, FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_SEARCH_FOR_USERS, request.toSoapObject(FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_SEARCH_FOR_USERS));
		if (soapResponse instanceof SoapObject) {
			return new ResponseSearchForUsers((SoapObject) soapResponse);
		}
		return null;
	}

	public static ResponseGetRecipeFeed getRecipeFeed(BaseRequest request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_NETWORK_SERVICE, FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_RECIPE_FEED, request.toSoapObject(FSNWSConstants.NETWORK_SERVICE_OPERATION_NAME_GET_RECIPE_FEED));
		if (soapResponse instanceof SoapObject) {
			return new ResponseGetRecipeFeed((SoapObject) soapResponse);
		}
		return null;
	}

	public static ResponseGetRecipe getRecipe(RequestGetRecipe request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_FOOD_SERVICE, FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_GET_RECIPE, request.toSoapObject());
		if (soapResponse instanceof SoapObject) {
			return new ResponseGetRecipe((SoapObject) soapResponse);
		}
		return null;
	}

	public static BaseResponse createRecipe(RequestCreateRecipe request) {
		Object soapResponse = callWebService(FSNWSConstants.URL_FOOD_SERVICE, FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_CREATE_RECIPE, request.toSoapObject(FSNWSConstants.FOOD_SERVICE_OPERATION_NAME_CREATE_RECIPE));
		if (soapResponse instanceof SoapObject) {
			return new BaseResponse((SoapObject) soapResponse);

		}
		return null;
	}
}
