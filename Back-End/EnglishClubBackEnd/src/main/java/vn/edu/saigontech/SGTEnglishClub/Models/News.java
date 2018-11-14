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
@Table(name="news"
    ,catalog="sgt_englishclub"
)
public class News  implements java.io.Serializable {


     private Integer id;
     private Admin admin;
     private Newstype newstype;
     private String title;
     private String thumbnailpicturetitle;
     private String bigpicturetitle;
     private String content;
     private Date postdate;
     private boolean status;

    public News() {
    }

	
    public News(Admin admin, Newstype newstype, String title, String bigpicturetitle, String content, boolean status) {
        this.admin = admin;
        this.newstype = newstype;
        this.title = title;
        this.bigpicturetitle = bigpicturetitle;
        this.content = content;
        this.status = status;
    }
    public News(Admin admin, Newstype newstype, String title, String thumbnailpicturetitle, String bigpicturetitle, String content, Date postdate, boolean status) {
       this.admin = admin;
       this.newstype = newstype;
       this.title = title;
       this.thumbnailpicturetitle = thumbnailpicturetitle;
       this.bigpicturetitle = bigpicturetitle;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADMINID", nullable=false)
    public Admin getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NEWSTYPEID", nullable=false)
    public Newstype getNewstype() {
        return this.newstype;
    }
    
    public void setNewstype(Newstype newstype) {
        this.newstype = newstype;
    }

    
    @Column(name="TITLE", nullable=false, length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="THUMBNAILPICTURETITLE", length=100)
    public String getThumbnailpicturetitle() {
        return this.thumbnailpicturetitle;
    }
    
    public void setThumbnailpicturetitle(String thumbnailpicturetitle) {
        this.thumbnailpicturetitle = thumbnailpicturetitle;
    }

    
    @Column(name="BIGPICTURETITLE", nullable=false)
    public String getBigpicturetitle() {
        return this.bigpicturetitle;
    }
    
    public void setBigpicturetitle(String bigpicturetitle) {
        this.bigpicturetitle = bigpicturetitle;
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


