package vn.edu.saigontech.SGTEnglishClub.Responses;

public class LoginResponse {
	private int adminID;
	private String token;
	public LoginResponse(int adminID, String token) {
		super();
		this.adminID = adminID;
		this.token = token;
	}
	public LoginResponse() {
		super();
	}
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
