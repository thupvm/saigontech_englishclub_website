package vn.edu.saigontech.SGTEnglishClub.Responses;

public class LoginResponse {
	private String userName;
	private String token;
	public LoginResponse(String userName, String token) {
		super();
		this.userName = userName;
		this.token = token;
	}
	@Override
	public String toString() {
		return "LoginResponse []";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	

}
