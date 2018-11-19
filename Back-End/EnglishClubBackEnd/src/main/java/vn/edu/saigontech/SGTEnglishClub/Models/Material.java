package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "material", catalog = "sgt_englishclub")
public class Material implements java.io.Serializable {
	private Integer id;
	private Admin admin;
	private Materialtype materialtype;
	private String title;
	private String titlepicture;
	private String content;
	private Date postdate;
	private boolean status;
	private Set<File> files = new HashSet<File>(0);

	public Material() {
	}

	public Material(Admin admin, Materialtype materialtype, String title, boolean status) {
		this.admin = admin;
		this.materialtype = materialtype;
		this.title = title;
		this.status = status;
	}

	public Material(Admin admin, Materialtype materialtype, String title, String titlepicture, String content,
			Date postdate, boolean status, Set<File> files) {
		this.admin = admin;
		this.materialtype = materialtype;
		this.title = title;
		this.titlepicture = titlepicture;
		this.content = content;
		this.postdate = postdate;
		this.status = status;
		this.files = files;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADMINID", nullable = false)
	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIALTYPEID", nullable = false)
	public Materialtype getMaterialtype() {
		return this.materialtype;
	}

	public void setMaterialtype(Materialtype materialtype) {
		this.materialtype = materialtype;
	}

	@Column(name = "TITLE", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLEPICTURE")
	public String getTitlepicture() {
		return this.titlepicture;
	}

	public void setTitlepicture(String titlepicture) {
		this.titlepicture = titlepicture;
	}

	@Column(name = "CONTENT", length = 10000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "POSTDATE", length = 0)
	public Date getPostdate() {
		return this.postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	@Column(name = "STATUS", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material")
	public Set<File> getFiles() {
		return this.files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

}
