package tr.edu.boun.swe574.fsn.web.common.constants;

public enum ResultCode {
	SUCCESS(0),
	FAILURE(1);
	
	private int code;
	
	private ResultCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
