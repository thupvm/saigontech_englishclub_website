package vn.edu.saigontech.SGTEnglishClub.Responses;

public class LoginResponseStandard {
	private String username;
	private String accessToken;
	public LoginResponseStandard(String username, String accessToken) {
		super();
		this.username = username;
		this.accessToken = accessToken;
	}
	public LoginResponseStandard() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	@Override
	public String toString() {
		return "LoginResponseStandard [username=" + username + ", accessToken=" + accessToken + "]";
	}
	

}
