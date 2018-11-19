package vn.edu.saigontech.SGTEnglishClub.Models.nonMapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.File;
import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;

public class MaterialsNonMapping {
	private Integer id;
	private Integer adminId;
	private Integer materialtypeId;
	private String title;
	private String titlepicture;
	private String content;
	private String postdate;
	private boolean status;
	
	public MaterialsNonMapping() {
			
	}
	
	public MaterialsNonMapping(Integer adminId, Integer materialtypeId, String title, String titlepicture,
			String content, String postdate, boolean status) {
		super();
		this.adminId = adminId;
		this.materialtypeId = materialtypeId;
		this.title = title;
		this.titlepicture = titlepicture;
		this.content = content;
		this.postdate = postdate;
		this.status = status;
	}
	
	public MaterialsNonMapping(Integer id, Integer adminId, Integer materialtypeId, String title, String titlepicture,
			String content, String postdate, boolean status) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.materialtypeId = materialtypeId;
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

	public Integer getMaterialtypeId() {
		return materialtypeId;
	}

	public void setMaterialtypeId(Integer materialtypeId) {
		this.materialtypeId = materialtypeId;
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
		return "MaterialsNonMapping [id=" + id + ", adminId=" + adminId + ", materialtypeId=" + materialtypeId
				+ ", title=" + title + ", titlepicture=" + titlepicture + ", content=" + content + ", postdate="
				+ postdate + ", status=" + status + "]";
	}
	
	
	
	

}
