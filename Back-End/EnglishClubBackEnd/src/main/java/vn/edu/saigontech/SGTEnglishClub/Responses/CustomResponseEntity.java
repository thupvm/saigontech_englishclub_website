package vn.edu.saigontech.SGTEnglishClub.Responses;

public class CustomResponseEntity {
	private int errorCode;
	private String message;
	private Object data;
	public CustomResponseEntity(int errorCode, String message, Object data) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.data = data;
	}
	public CustomResponseEntity() {
		super();
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static CustomResponseEntity getOKResponse(String message, Object data) {
		CustomResponseEntity res = new CustomResponseEntity(0, message, data);
		return res;
	}
	
	public static CustomResponseEntity getDatabaseErrorResponse() {
		CustomResponseEntity res = new CustomResponseEntity(1, "Database error", null);
		return res;
	}
	
	public static CustomResponseEntity getWrongUsernamePassword() {
		CustomResponseEntity res = new CustomResponseEntity(2, "Wrong username and password", null);
		return res;
	}
	
	public static CustomResponseEntity getExpiredTimeResponse() {
		CustomResponseEntity res = new CustomResponseEntity(3, "Log-in timeout", null);
		return res;
	}
	
	public static CustomResponseEntity getHackerWarningResponse() {
		CustomResponseEntity res = new CustomResponseEntity(4, "Are you hacker?", null);
		return res;
	}
	
	public static CustomResponseEntity getAccessDeniedResponse() {
		CustomResponseEntity res = new CustomResponseEntity(5, "Access Denied", null);
		return res;
	}
	
	
	
	

}
