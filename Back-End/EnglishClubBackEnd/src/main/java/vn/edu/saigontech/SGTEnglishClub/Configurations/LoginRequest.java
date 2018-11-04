package vn.edu.saigontech.SGTEnglishClub.Configurations;

public class LoginRequest {
	private String username;
	private String password;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Username : " + String.valueOf(username) + "][password :" + String.valueOf(password) + "]";
	}

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public LoginRequest() {
		this("", "");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LoginRequest that = (LoginRequest) o;

		if (password != null ? !password.equals(that.password) : that.password != null)
			return false;
		if (username != null ? !username.equals(that.username) : that.username != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
}
