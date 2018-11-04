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
	
	

}
