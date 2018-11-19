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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import vn.edu.saigontech.SGTEnglishClub.Utils.CustomDateSerializer;

@Entity
@Table(name="tip"
    ,catalog="sgt_englishclub"
)
public class Tip  implements java.io.Serializable {
     private Integer id;
     private Admin admin;
     private TipType tiptype;
     private String title;
     private String titlepicture;
     private String content;
     private Date postdate;
     private boolean status;

    public Tip() {
    }

	
    public Tip(Admin admin, TipType tiptype, String title, String titlepicture, String content, boolean status) {
        this.admin = admin;
        this.tiptype = tiptype;
        this.title = title;
        this.titlepicture = titlepicture;
        this.content = content;
        this.status = status;
    }
    public Tip(Admin admin, TipType tiptype, String title, String titlepicture, String content, Date postdate, boolean status) {
       this.admin = admin;
       this.tiptype = tiptype;
       this.title = title;
       this.titlepicture = titlepicture;
       this.content = content;
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

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ADMINID", nullable=false)
    public Admin getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="TIPTYPEID", nullable=false)
    public TipType getTiptype() {
        return this.tiptype;
    }
    
    public void setTiptype(TipType tiptype) {
        this.tiptype = tiptype;
    }

    
    @Column(name="TITLE", nullable=false, length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="TITLEPICTURE", nullable=false)
    public String getTitlepicture() {
        return this.titlepicture;
    }
    
    public void setTitlepicture(String titlepicture) {
        this.titlepicture = titlepicture;
    }

    
    @Column(name="CONTENT", nullable=false, length=16777215)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="POSTDATE", length=0)
    @JsonSerialize(using = CustomDateSerializer.class)
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


