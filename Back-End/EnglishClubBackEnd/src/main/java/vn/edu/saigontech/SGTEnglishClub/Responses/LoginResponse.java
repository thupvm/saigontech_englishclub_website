package vn.edu.saigontech.SGTEnglishClub.Responses;

public class LoginResponse {
	private int adminID;
	private String fullName;
	private String accessToken;

	public LoginResponse(int adminID, String fullName, String accessToken) {
		super();
		this.adminID = adminID;
		this.fullName = fullName;
		this.accessToken = accessToken;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "LoginResponse [adminID=" + adminID + ", fullName=" + fullName + ", accessToken=" + accessToken + "]";
	}

}
