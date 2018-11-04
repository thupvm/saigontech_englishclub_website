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
@Table(name="clips"
    ,catalog="sgt_englishclub"
)
public class Clips  implements java.io.Serializable {


     private Integer id;
     private Admins admins;
     private ClipTypes clipTypes;
     private String title;
     private Date uploadPeriod;
     private String description;
     private String link;
     private boolean status;

    public Clips() {
    }

	
    public Clips(Admins admins, ClipTypes clipTypes, Date uploadPeriod, String link, boolean status) {
        this.admins = admins;
        this.clipTypes = clipTypes;
        this.uploadPeriod = uploadPeriod;
        this.link = link;
        this.status = status;
    }
    public Clips(Admins admins, ClipTypes clipTypes, String title, Date uploadPeriod, String description, String link, boolean status) {
       this.admins = admins;
       this.clipTypes = clipTypes;
       this.title = title;
       this.uploadPeriod = uploadPeriod;
       this.description = description;
       this.link = link;
       this.status = status;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADMIN_ID", nullable=false)
    public Admins getAdmins() {
        return this.admins;
    }
    
    public void setAdmins(Admins admins) {
        this.admins = admins;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIP_TYPE_ID", nullable=false)
    public ClipTypes getClipTypes() {
        return this.clipTypes;
    }
    
    public void setClipTypes(ClipTypes clipTypes) {
        this.clipTypes = clipTypes;
    }

    
    @Column(name="TITLE", length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPLOAD_PERIOD", nullable=false, length=19)
    public Date getUploadPeriod() {
        return this.uploadPeriod;
    }
    
    public void setUploadPeriod(Date uploadPeriod) {
        this.uploadPeriod = uploadPeriod;
    }

    
    @Column(name="DESCRIPTION", length=1000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="LINK", nullable=false, length=10000)
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }

    
    @Column(name="STATUS", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }




}


