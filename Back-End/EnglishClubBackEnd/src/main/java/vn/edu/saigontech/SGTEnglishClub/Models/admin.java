package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "admin")
public class admin {
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String userName;
	
	@Column(nullable = false, length = 50)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;
	
	private String email;
	
	@Column(nullable = false, unique = true, length = 50)
	private String phoneNumber;
	
	@Column(nullable=false, length=11)
	private String role;
	
	@Column
	private boolean status;
	
	@OneToMany(mappedBy="admin")
	private List<ematerials> ematerials;
	
	@OneToMany(mappedBy="admin")
	private List<video> video;
	
	@OneToMany(mappedBy="admin")
	private List<news> news;
	
	@OneToMany(mappedBy="admin")
	private List<tips> tips;
	
	public admin() {
		super();
	}

	public admin(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			String role, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.ematerials> ematerials,
			List<vn.edu.saigontech.SGTEnglishClub.Models.video> video,
			List<vn.edu.saigontech.SGTEnglishClub.Models.news> news,
			List<vn.edu.saigontech.SGTEnglishClub.Models.tips> tips) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
		this.ematerials = ematerials;
		this.video = video;
		this.news = news;
		this.tips = tips;
	}

	public admin(int id, String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, String role, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.ematerials> ematerials,
			List<vn.edu.saigontech.SGTEnglishClub.Models.video> video,
			List<vn.edu.saigontech.SGTEnglishClub.Models.news> news,
			List<vn.edu.saigontech.SGTEnglishClub.Models.tips> tips) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
		this.ematerials = ematerials;
		this.video = video;
		this.news = news;
		this.tips = tips;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<ematerials> getEmaterials() {
		return ematerials;
	}

	public void setEmaterials(List<ematerials> ematerials) {
		this.ematerials = ematerials;
	}

	public List<video> getVideo() {
		return video;
	}

	public void setVideo(List<video> video) {
		this.video = video;
	}

	public List<news> getNews() {
		return news;
	}

	public void setNews(List<news> news) {
		this.news = news;
	}

	public List<tips> getTips() {
		return tips;
	}

	public void setTips(List<tips> tips) {
		this.tips = tips;
	}

	@Override
	public String toString() {
		return "admin [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role
				+ ", status=" + status + ", ematerials=" + ematerials + ", video=" + video + ", news=" + news
				+ ", tips=" + tips + "]";
	}
	
	

}
