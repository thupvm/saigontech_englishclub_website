package vn.edu.saigontech.SGTEnglishClub.Models.nonMapping;

public class VideoNonMapping {
	private Integer id;
	private Integer adminID;
	private Integer videoTypeID;
	private String title;
	private String description;
	private String link;
	private String postdate;
	private boolean status;
	public VideoNonMapping(Integer id, Integer adminID, Integer videoTypeID, String title, String description,
			String link, String postdate, boolean status) {
		super();
		this.id = id;
		this.adminID = adminID;
		this.videoTypeID = videoTypeID;
		this.title = title;
		this.description = description;
		this.link = link;
		this.postdate = postdate;
		this.status = status;
	}
	public VideoNonMapping(Integer adminID, Integer videoTypeID, String title, String description, String link,
			String postdate, boolean status) {
		super();
		this.adminID = adminID;
		this.videoTypeID = videoTypeID;
		this.title = title;
		this.description = description;
		this.link = link;
		this.postdate = postdate;
		this.status = status;
	}
	public VideoNonMapping() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public Integer getVideoTypeID() {
		return videoTypeID;
	}
	public void setVideoTypeID(Integer videoTypeID) {
		this.videoTypeID = videoTypeID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return "VideoNonMapping [id=" + id + ", adminID=" + adminID + ", videoTypeID=" + videoTypeID + ", title="
				+ title + ", description=" + description + ", link=" + link + ", postdate=" + postdate + ", status="
				+ status + "]";
	}
	
	
	
	
	
	

}
