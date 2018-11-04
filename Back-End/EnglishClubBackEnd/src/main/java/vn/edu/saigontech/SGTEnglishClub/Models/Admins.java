package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admins", catalog = "sgt_englishclub")
public class Admins implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private String role;
	private boolean status;
	private Set<News> newses = new HashSet<News>(0);
	private Set<Materials> materialses = new HashSet<Materials>(0);
	private Set<Tips> tipses = new HashSet<Tips>(0);
	private Set<Clips> clipses = new HashSet<Clips>(0);

	public Admins() {
		super();
	}

	public Admins(String username, String password, String fullname, String role, boolean status) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
	}

	public Admins(Integer id, String username, String password, String fullname, String role, boolean status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
	}

	public Admins(String username, String password, String fullname, String role, boolean status, Set<News> newses,
			Set<Materials> materialses, Set<Tips> tipses, Set<Clips> clipses) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.newses = newses;
		this.materialses = materialses;
		this.tipses = tipses;
		this.clipses = clipses;
	}

	public Admins(Integer id, String username, String password, String fullname, String role, boolean status,
			Set<News> newses, Set<Materials> materialses, Set<Tips> tipses, Set<Clips> clipses) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.newses = newses;
		this.materialses = materialses;
		this.tipses = tipses;
		this.clipses = clipses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "USERNAME", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "FULLNAME", nullable = false)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	@Column(name = "ROLE", nullable = false, length = 10)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "STATUS", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admins")
	public Set<News> getNewses() {
		return this.newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admins")
	public Set<Materials> getMaterialses() {
		return this.materialses;
	}

	public void setMaterialses(Set<Materials> materialses) {
		this.materialses = materialses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admins")
	public Set<Tips> getTipses() {
		return this.tipses;
	}

	public void setTipses(Set<Tips> tipses) {
		this.tipses = tipses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admins")
	public Set<Clips> getClipses() {
		return this.clipses;
	}

	public void setClipses(Set<Clips> clipses) {
		this.clipses = clipses;
	}

	@Override
	public String toString() {
		return "Admins [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", role=" + role + ", status=" + status + ", newses=" + newses + ", materialses=" + materialses
				+ ", tipses=" + tipses + ", clipses=" + clipses + "]";
	}
	
	

}
