package vn.edu.saigontech.SGTEnglishClub.Models.nonMapping;

public class NewsNonMapping {
	 private Integer id;                   
     private Integer adminId;
     private Integer newstypeId;
     private String title;
     private String thumbnailpicturetitle;
     private String bigpicturetitle;
     private String content;
     private String postdate;
     private boolean status;
     
     public NewsNonMapping(Integer adminId, Integer newstypeId, String title, String thumbnailpicturetitle,
 			String bigpicturetitle, String content, String postdate, boolean status) {
 		super();
 		this.adminId = adminId;
 		this.newstypeId = newstypeId;
 		this.title = title;
 		this.thumbnailpicturetitle = thumbnailpicturetitle;
 		this.bigpicturetitle = bigpicturetitle;
 		this.content = content;
 		this.postdate = postdate;
 		this.status = status;
 	 }
     
     public NewsNonMapping(Integer id, Integer adminId, Integer newstypeId, String title, String thumbnailpicturetitle,
			String bigpicturetitle, String content, String postdate, boolean status) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.newstypeId = newstypeId;
		this.title = title;
		this.thumbnailpicturetitle = thumbnailpicturetitle;
		this.bigpicturetitle = bigpicturetitle;
		this.content = content;
		this.postdate = postdate;
		this.status = status;
     }
     
     

	public NewsNonMapping() {
		super();
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

	public Integer getNewstypeId() {
		return newstypeId;
	}

	public void setNewstypeId(Integer newstypeId) {
		this.newstypeId = newstypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnailpicturetitle() {
		return thumbnailpicturetitle;
	}

	public void setThumbnailpicturetitle(String thumbnailpicturetitle) {
		this.thumbnailpicturetitle = thumbnailpicturetitle;
	}

	public String getBigpicturetitle() {
		return bigpicturetitle;
	}

	public void setBigpicturetitle(String bigpicturetitle) {
		this.bigpicturetitle = bigpicturetitle;
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
		return "NewsNonMapping [id=" + id + ", adminId=" + adminId + ", newstypeId=" + newstypeId + ", title=" + title
				+ ", thumbnailpicturetitle=" + thumbnailpicturetitle + ", bigpicturetitle=" + bigpicturetitle
				+ ", content=" + content + ", postdate=" + postdate + ", status=" + status + "]";
	}
     
	
     
     
     


}
