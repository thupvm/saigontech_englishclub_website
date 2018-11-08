package vn.edu.saigontech.SGTEnglishClub.Requests;

public class loginInfo {
	private String username;
	private String password;
	public loginInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public loginInfo() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "loginInfo [username=" + username + ", password=" + password + "]";
	}
	
	

}
