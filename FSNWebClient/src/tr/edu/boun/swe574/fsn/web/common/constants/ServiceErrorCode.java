package tr.edu.boun.swe574.fsn.web.common.constants;

/**
	101	Missing mandatory parameter
	102	Email is already in use
	103	Invalid email or password
	104	Invalid token
	105	Token expired
	106	Wrong date format
	107	User not found
	108	Recipe not found
	109	Rate value is out of the range
	500	Internal server error
*/
public enum ServiceErrorCode {

	SUCCESS					(0, "Success"),
	MISSING_PARAM 			(101, "Missing mandatory parameter"),
	EMAIL_IN_USE			(102, "Email is already in use"),
	INVALID_EMAIL_PWD		(103, "Invalid email or password"),
	TOKEN_INVALID			(104, "Invalid token"),
	TOKEN_EXPIRED			(105, "Token expired"),
	WRONG_DATE_FORMAT		(106, "Wrong date format"),
	USER_NOT_FOUND			(107, "User not found"),
	RECIPE_NOT_FOUND		(108, "Recipe not found"),
	RATE_VALUE_INVALID		(109, "Rate value is out of the range"),
	INTERNAL_SERVER_ERROR 	(500, "Internal server error");
	
	
	private final int code;
	private final String description;
	
	ServiceErrorCode(int code, String description){
		this.code = code;
		this.description = description;
	}
	
	public int getCode(){
		return this.code;
	}

	public String getDescription() {
		return description;
	}
	
	public static ServiceErrorCode valueOf(int code) {
		ServiceErrorCode[] values = ServiceErrorCode.values();
		for (ServiceErrorCode serviceErrorCode : values) {
			if(serviceErrorCode.getCode() == code) {
				return serviceErrorCode;
			}
		}
		return null;
	}
	
}
