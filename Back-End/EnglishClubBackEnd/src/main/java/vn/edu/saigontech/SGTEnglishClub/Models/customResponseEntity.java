package vn.edu.saigontech.SGTEnglishClub.Models;

public class customResponseEntity {
	private int errorCode;
	private String message;
	private Object data;
	
	public customResponseEntity() {
		super();
	}
	public customResponseEntity(int errorCode, String message, Object data) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.data = data;
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
	@Override
	public String toString() {
		return "customResponseEntity [errorCode=" + errorCode + ", message=" + message + ", data=" + data + "]";
	}
	
	

}
