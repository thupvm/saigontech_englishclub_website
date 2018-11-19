package vn.edu.saigontech.SGTEnglishClub.Models.nonMapping;

import java.util.Date;

import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.TipType;

public class TipNonMapping {
	 private Integer id;
     private Integer adminId;
     private Integer tiptypeId;
     private String title;
     private String titlepicture;
     private String content;
     private String postdate;
     private boolean status;
     
     
	public TipNonMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipNonMapping(Integer adminId, Integer tiptypeId, String title, String titlepicture,
			String content, String postdate, boolean status) {
		super();
		this.adminId = adminId;
		this.tiptypeId = tiptypeId;
		this.title = title;
		this.titlepicture = titlepicture;
		this.content = content;
		this.postdate = postdate;
		this.status = status;
	}

	public TipNonMapping(Integer id, Integer adminId, Integer tiptypeId, String title, String titlepicture,
			String content, String postdate, boolean status) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.tiptypeId = tiptypeId;
		this.title = title;
		this.titlepicture = titlepicture;
		this.content = content;
		this.postdate = postdate;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getTiptypeId() {
		return tiptypeId;
	}
	public void setTiptypeId(Integer tiptypeId) {
		this.tiptypeId = tiptypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitlepicture() {
		return titlepicture;
	}
	public void setTitlepicture(String titlepicture) {
		this.titlepicture = titlepicture;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TipNonMapping [id=" + id + ", adminId=" + adminId + ", tiptypeId=" + tiptypeId + ", title=" + title
				+ ", titlepicture=" + titlepicture + ", content=" + content + ", postdate=" + postdate + ", status="
				+ status + "]";
	}
	
	
	
     
}
