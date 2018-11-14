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
@Table(name= "tips")
public class tips {
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column(length = 50)
	private String thumbnail_picture_title;
	
	@Column(length = 50)
	private String big_picture_title;
	
	@Column(length = 2000)
	private String content;
	
	private int admin_id;
	
	private int tips_type_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="admin")
	private admin admin;
	
	@ManyToOne
	@JoinColumn(name="tips_types")
	private tips_types tips_types;
	
	public tips() {
		super();
	}
	
	public tips(String title, String thumbnail_picture_title, String big_picture_title, String content,
			int admin_id, int tips_type_id, Date postDate, boolean status,
			vn.edu.saigontech.SGTEnglishClub.Models.admin admin, tips_types tips_types) {
		super();
		this.title = title;
		this.thumbnail_picture_title = thumbnail_picture_title;
		this.big_picture_title = big_picture_title;
		this.content = content;
		this.admin_id = admin_id;
		this.tips_type_id = tips_type_id;
		this.postDate = postDate;
		this.status = status;
		this.admin = admin;
		this.tips_types = tips_types;
	}


	public tips(int id, String title, String thumbnail_picture_title, String big_picture_title, String content,
			int admin_id, int tips_type_id, Date postDate, boolean status,
			vn.edu.saigontech.SGTEnglishClub.Models.admin admin, tips_types tips_type) {
		super();
		this.id = id;
		this.title = title;
		this.thumbnail_picture_title = thumbnail_picture_title;
		this.big_picture_title = big_picture_title;
		this.content = content;
		this.admin_id = admin_id;
		this.tips_type_id = tips_type_id;
		this.postDate = postDate;
		this.status = status;
		this.admin = admin;
		this.tips_types = tips_types;
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

	public String getThumbnail_picture_title() {
		return thumbnail_picture_title;
	}

	public void setThumbnail_picture_title(String thumbnail_picture_title) {
		this.thumbnail_picture_title = thumbnail_picture_title;
	}

	public String getBig_picture_title() {
		return big_picture_title;
	}

	public void setBig_picture_title(String big_picture_title) {
		this.big_picture_title = big_picture_title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getTips_type_id() {
		return tips_type_id;
	}

	public void setTips_type_id(int tips_type_id) {
		this.tips_type_id = tips_type_id;
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

	public tips_types getTips_type() {
		return tips_types;
	}

	public void setTips_type(tips_types tips_types) {
		this.tips_types = tips_types;
	}

	@Override
	public String toString() {
		return "tips [id=" + id + ", title=" + title + ", thumbnail_picture_title=" + thumbnail_picture_title
				+ ", big_picture_title=" + big_picture_title + ", content=" + content + ", admin_id=" + admin_id
				+ ", tips_type_id=" + tips_type_id + ", postDate=" + postDate + ", status=" + status + ", admin="
				+ admin + ", tips_types=" + tips_types + "]";
	}
	
	
	
	
	
}
