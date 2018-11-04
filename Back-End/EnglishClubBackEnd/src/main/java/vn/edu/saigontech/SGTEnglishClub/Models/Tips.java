package vn.edu.saigontech.SGTEnglishClub.Models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tips", catalog = "sgt_englishclub")
public class Tips implements java.io.Serializable {

	private Integer id;
	private Admins admins;
	private TipTypes tipTypes;
	private String title;
	private String titlePicture;
	private Date uploadPeriod;
	private String content;
	private boolean status;

	public Tips() {
	}

	public Tips(Admins admins, TipTypes tipTypes, String title, String titlePicture, Date uploadPeriod, String content,
			boolean status) {
		this.admins = admins;
		this.tipTypes = tipTypes;
		this.title = title;
		this.titlePicture = titlePicture;
		this.uploadPeriod = uploadPeriod;
		this.content = content;
		this.status = status;
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
	@JoinColumn(name = "TIP_TYPE_ID", nullable = false)
	public TipTypes getTipTypes() {
		return this.tipTypes;
	}

	public void setTipTypes(TipTypes tipTypes) {
		this.tipTypes = tipTypes;
	}

	@Column(name = "TITLE", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE_PICTURE", nullable = false)
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

	@Column(name = "CONTENT", nullable = false, length = 16777215)
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

}
