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
@Table(name="video"
    ,catalog="sgt_englishclub"
)
public class Video  implements java.io.Serializable {


     private Integer id;
     private Admin admin;
     private Videotype videotype;
     private String title;
     private String description;
     private String link;
     private Date postdate;
     private boolean status;

    public Video() {
    }

	
    public Video(Admin admin, Videotype videotype, String title, String description, String link, boolean status) {
        this.admin = admin;
        this.videotype = videotype;
        this.title = title;
        this.description = description;
        this.link = link;
        this.status = status;
    }
    public Video(Admin admin, Videotype videotype, String title, String description, String link, Date postdate, boolean status) {
       this.admin = admin;
       this.videotype = videotype;
       this.title = title;
       this.description = description;
       this.link = link;
       this.postdate = postdate;
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
    @JoinColumn(name="ADMINID", nullable=false)
    public Admin getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIPTYPEID", nullable=false)
    public Videotype getVideotype() {
        return this.videotype;
    }
    
    public void setVideotype(Videotype videotype) {
        this.videotype = videotype;
    }

    
    @Column(name="TITLE", nullable=false, length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="DESCRIPTION", nullable=false, length=1000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="LINK", nullable=false, length=500)
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="POSTDATE", length=0)
    public Date getPostdate() {
        return this.postdate;
    }
    
    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    
    @Column(name="STATUS", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }




}


