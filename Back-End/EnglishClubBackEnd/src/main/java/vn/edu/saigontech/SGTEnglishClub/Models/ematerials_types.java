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
@Table(name= "ematerials_types")
public class ematerials_types {
	
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String titleName;
	
	private boolean status;
	
	@OneToMany(mappedBy="ematerials_types",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<ematerials> ematerials=new ArrayList<>(0);
	
	public ematerials_types() {
		super();
		
	}
	public ematerials_types(String titleName, boolean status) {
		super();
		this.titleName = titleName;
		this.status = status;
	}
	public ematerials_types(int id, String titleName, boolean status) {
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
	@Override
	public String toString() {
		return "ematerials_types [id=" + id + ", titleName=" + titleName + ", status=" + status + ", ematerials="
				+ ematerials + "]";
	}
	
	

}
