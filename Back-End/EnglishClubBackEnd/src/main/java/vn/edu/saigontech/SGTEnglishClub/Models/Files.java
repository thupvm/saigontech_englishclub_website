package vn.edu.saigontech.SGTEnglishClub.Models;

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

@Entity
@Table(name= "files")
public class files {
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String nameFile;
	
	@Column(length = 50)
	private String image_Name;
	
	private int ematerial_id;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="ematerials")
	private ematerials ematerials;
	
	public files(int id, String nameFile, String image_Name, int ematerial_id, String status) {
		super();
		this.id = id;
		this.nameFile = nameFile;
		this.image_Name = image_Name;
		this.ematerial_id = ematerial_id;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	public String getImage_Name() {
		return image_Name;
	}
	public void setImage_Name(String image_Name) {
		this.image_Name = image_Name;
	}
	public int getEmaterial_id() {
		return ematerial_id;
	}
	public void setEmaterial_id(int ematerial_id) {
		this.ematerial_id = ematerial_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
