package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "video_types")
public class video_types {
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String titleName;
	
	private boolean status;
	
	@OneToMany(mappedBy="video_type",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<video> video =new ArrayList<>(0);
	
	public video_types() {
	}

	public video_types(String titleName, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.video> video) {
		super();
		this.titleName = titleName;
		this.status = status;
		this.video = video;
	}

	public video_types(int id, String titleName, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.video> video) {
		super();
		this.id = id;
		this.titleName = titleName;
		this.status = status;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<video> getVideo() {
		return video;
	}

	public void setVideo(List<video> video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "video_types [id=" + id + ", titleName=" + titleName + ", status=" + status + ", video=" + video + "]";
	}
	
	
	

	
}
