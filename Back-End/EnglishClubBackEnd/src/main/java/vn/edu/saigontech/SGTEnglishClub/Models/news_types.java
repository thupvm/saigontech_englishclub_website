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
@Table(name= "news_types")
public class news_types {
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String titleName;
	
	private boolean status;
	
	@OneToMany(mappedBy="news_types",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<news> news=new ArrayList<news>(0);

	public news_types() {
		super();
	}

	public news_types(String titleName, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.news> news) {
		super();
		this.titleName = titleName;
		this.status = status;
		this.news = news;
	}

	public news_types(int id, String titleName, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.news> news) {
		super();
		this.id = id;
		this.titleName = titleName;
		this.status = status;
		this.news = news;
	}
	
	

	public news_types(int id, String titleName, boolean status) {
		super();
		this.id = id;
		this.titleName = titleName;
		this.status = status;
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

	public List<news> getNews() {
		return news;
	}

	public void setNews(List<news> news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "news_types [id=" + id + ", titleName=" + titleName + ", status=" + status + ", news=" + news + "]";
	}
	
	

}
