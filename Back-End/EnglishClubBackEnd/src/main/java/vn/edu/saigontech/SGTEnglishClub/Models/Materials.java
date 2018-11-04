package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "materials", catalog = "sgt_englishclub")
public class Materials implements java.io.Serializable {

	private Integer id;
	private Admins admins;
	private MaterialTypes materialTypes;
	private String title;
	private String titlePicture;
	private Date uploadPeriod;
	private String content;
	private boolean status;
	private Set<Files> fileses = new HashSet<Files>(0);

	public Materials() {
	}

	public Materials(Admins admins, MaterialTypes materialTypes, String title, Date uploadPeriod, boolean status) {
		this.admins = admins;
		this.materialTypes = materialTypes;
		this.title = title;
		this.uploadPeriod = uploadPeriod;
		this.status = status;
	}

	public Materials(Admins admins, MaterialTypes materialTypes, String title, String titlePicture, Date uploadPeriod,
			String content, boolean status, Set<Files> fileses) {
		this.admins = admins;
		this.materialTypes = materialTypes;
		this.title = title;
		this.titlePicture = titlePicture;
		this.uploadPeriod = uploadPeriod;
		this.content = content;
		this.status = status;
		this.fileses = fileses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADMIN_ID", nullable = false)
	public Admins getAdmins() {
		return this.admins;
	}

	public void setAdmins(Admins admins) {
		this.admins = admins;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE_ID", nullable = false)
	public MaterialTypes getMaterialTypes() {
		return this.materialTypes;
	}

	public void setMaterialTypes(MaterialTypes materialTypes) {
		this.materialTypes = materialTypes;
	}

	@Column(name = "TITLE", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE_PICTURE")
	public String getTitlePicture() {
		return this.titlePicture;
	}

	public void setTitlePicture(String titlePicture) {
		this.titlePicture = titlePicture;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPLOAD_PERIOD", nullable = false, length = 19)
	public Date getUploadPeriod() {
		return this.uploadPeriod;
	}

	public void setUploadPeriod(Date uploadPeriod) {
		this.uploadPeriod = uploadPeriod;
	}

	@Column(name = "CONTENT", length = 10000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "STATUS", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materials")
	public Set<Files> getFileses() {
		return this.fileses;
	}

	public void setFileses(Set<Files> fileses) {
		this.fileses = fileses;
	}

}
