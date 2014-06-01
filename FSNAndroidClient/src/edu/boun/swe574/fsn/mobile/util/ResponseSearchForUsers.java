package edu.boun.swe574.fsn.mobile.util;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.ws.response.BaseResponse;

public class ResponseSearchForUsers extends BaseResponse {
	private List<UserInfo> userList;

	protected ResponseSearchForUsers(SoapObject object) {
		super(object);
		if (object != null) {
			this.userList = AndroidUtil.convertSoapObjectToList(object, "return.userList", UserInfo.class);
		}
	}

	public List<UserInfo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfo> userList) {
		this.userList = userList;
	}

}
