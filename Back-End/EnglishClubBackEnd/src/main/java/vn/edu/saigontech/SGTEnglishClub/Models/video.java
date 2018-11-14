package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name= "video")
public class video {
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column(length = 50)
	private String picture_title;
	
	@Column(length = 2000)
	private String description;
	
	@Column(length = 2000)
	private String link;
	
	private int admin_id;
	
	private int video_type_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="admin")
	private admin admin;
	
	@ManyToOne
	@JoinColumn(name="video_type")
	private video_types video_type;

	public video() {
		
	}
	
	public video(String title, String picture_title, String description, String link, int admin_id,
			int video_type_id, Date postDate, boolean status,
			vn.edu.saigontech.SGTEnglishClub.Models.admin admin, video_types video_type) {
		super();
		this.title = title;
		this.picture_title = picture_title;
		this.description = description;
		this.link = link;
		this.admin_id = admin_id;
		this.video_type_id = video_type_id;
		this.postDate = postDate;
		this.status = status;
		this.admin = admin;
		this.video_type = video_type;
	}
	
	public video(int id, String title, String picture_title, String description, String link, int admin_id,
			int video_type_id, Date postDate, boolean status,
			vn.edu.saigontech.SGTEnglishClub.Models.admin admin, video_types video_type) {
		super();
		this.id = id;
		this.title = title;
		this.picture_title = picture_title;
		this.description = description;
		this.link = link;
		this.admin_id = admin_id;
		this.video_type_id = video_type_id;
		this.postDate = postDate;
		this.status = status;
		this.admin = admin;
		this.video_type = video_type;
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

	public String getPicture_title() {
		return picture_title;
	}

	public void setPicture_title(String picture_title) {
		this.picture_title = picture_title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getVideo_type_id() {
		return video_type_id;
	}

	public void setVideo_type_id(int video_type_id) {
		this.video_type_id = video_type_id;
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

	public video_types getVideo_type() {
		return video_type;
	}

	public void setVideo_type(video_types video_type) {
		this.video_type = video_type;
	}

	@Override
	public String toString() {
		return "video [id=" + id + ", title=" + title + ", picture_title=" + picture_title + ", description="
				+ description + ", link=" + link + ", admin_id=" + admin_id + ", video_type_id=" + video_type_id
				+ ", postDate=" + postDate + ", status=" + status + ", admin=" + admin + ", video_type=" + video_type
				+ "]";
	}
	
	

}
