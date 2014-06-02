package edu.boun.swe574.fsn.mobile.ws.response;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;

public class BaseResponse {

	public BaseResponse(SoapObject object) {
		if (object != null) {
			this.resultCode = AndroidUtil.convertSoapObjectToPrimitive(object, "return.resultCode", Integer.class);
			this.errorCode = AndroidUtil.convertSoapObjectToPrimitive(object, "return.errorCode", Integer.class);
		}
	}

	private Integer errorCode;
	private Integer resultCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

}
