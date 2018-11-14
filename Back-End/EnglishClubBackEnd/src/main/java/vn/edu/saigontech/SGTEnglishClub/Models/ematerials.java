package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "ematerials")
public class ematerials {
	
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title;
	@Column(length = 300)
	private String summary;
	
	@Column(nullable = false, length = 2000)
	private String content;
	
	@Column(length = 200)
	private String image_title;
	
	private int admin_id;
	
	private int materials_type_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="admin")
	private admin admin;
	
	@ManyToOne
	@JoinColumn(name="ematerials_types")
	private ematerials_types ematerials_types;
	
	@OneToMany(mappedBy="ematerials")
	private List<files> files;
	
	public ematerials() {
		super();
	}
	
	public ematerials(int id, String title, String summary, String content, String image_title, int admin_id,
			int materials_type_id, Date postDate, boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.image_title = image_title;
		this.admin_id = admin_id;
		this.materials_type_id = materials_type_id;
		this.postDate = postDate;
		this.status = status;
	}
	
	public ematerials(String title, String summary, String content, String image_title, int admin_id,
			int materials_type_id, Date postDate, boolean status) {
		super();
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.image_title = image_title;
		this.admin_id = admin_id;
		this.materials_type_id = materials_type_id;
		this.postDate = postDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage_title() {
		return image_title;
	}

	public void setImage_title(String image_title) {
		this.image_title = image_title;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getMaterials_type_id() {
		return materials_type_id;
	}

	public void setMaterials_type_id(int materials_type_id) {
		this.materials_type_id = materials_type_id;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public admin getAdmin() {
		return admin;
	}

	public void setAdmin(admin admin) {
		this.admin = admin;
	}

	public ematerials_types getEmaterials_type() {
		return ematerials_types;
	}

	public void setEmaterials_type(ematerials_types ematerials_types) {
		this.ematerials_types = ematerials_types;
	}

	public List<files> getFiles() {
		return files;
	}

	public void setFiles(List<files> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "ematerials [id=" + id + ", title=" + title + ", summary=" + summary + ", content=" + content
				+ ", image_title=" + image_title + ", admin_id=" + admin_id + ", materials_type_id=" + materials_type_id
				+ ", postDate=" + postDate + ", status=" + status + ", admin=" + admin + ", ematerials_types="
				+ ematerials_types + ", files=" + files + "]";
	}
	
	

}
