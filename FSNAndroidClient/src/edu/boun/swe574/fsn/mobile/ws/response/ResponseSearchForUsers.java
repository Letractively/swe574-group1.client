package edu.boun.swe574.fsn.mobile.ws.response;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import edu.boun.swe574.fsn.mobile.util.AndroidUtil;
import edu.boun.swe574.fsn.mobile.util.UserInfo;

public class ResponseSearchForUsers extends BaseResponse {
	private List<UserInfo> userList;

	public ResponseSearchForUsers(SoapObject object) {
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
