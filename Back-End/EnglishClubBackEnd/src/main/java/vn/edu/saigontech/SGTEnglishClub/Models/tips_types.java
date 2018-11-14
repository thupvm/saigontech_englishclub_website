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
@Table(name= "tips_types")
public class tips_types {
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String titleName;
	
	private boolean status;
	
	@OneToMany(mappedBy="tips_types",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<tips> tips=new ArrayList<>(0);
	
	public tips_types() {
	}
	
	
	public tips_types(String titleName, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.tips> tips) {
		super();
		this.titleName = titleName;
		this.status = status;
		this.tips = tips;
	}
	

	public tips_types(int id, String titleName, boolean status,
			List<vn.edu.saigontech.SGTEnglishClub.Models.tips> tips) {
		super();
		this.id = id;
		this.titleName = titleName;
		this.status = status;
		this.tips = tips;
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


	public List<tips> getTips() {
		return tips;
	}


	public void setTips(List<tips> tips) {
		this.tips = tips;
	}


	@Override
	public String toString() {
		return "tips_types [id=" + id + ", titleName=" + titleName + ", status=" + status + ", tips=" + tips + "]";
	}
	
	
	
	


}
