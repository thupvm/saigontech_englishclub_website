package vn.edu.saigontech.SGTEnglishClub.Models;



import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="admin"
    ,catalog="sgt_englishclub"
)
public class Admin  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String password;
     private String lastname;
     private String firstname;
     private String role;
     private String phone;
     private String email;
     private boolean status;
     private Set<Tip> tips = new HashSet<Tip>(0);
     private Set<Video> videos = new HashSet<Video>(0);
     private Set<Material> materials = new HashSet<Material>(0);
     private Set<News> newses = new HashSet<News>(0);

    public Admin() {
    }

	
    public Admin(String username, String password, String lastname, String firstname, String role, String phone, String email, boolean status) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }
    public Admin(String username, String password, String lastname, String firstname, String role, String phone, String email, boolean status, Set<Tip> tips, Set<Video> videos, Set<Material> materials, Set<News> newses) {
       this.username = username;
       this.password = password;
       this.lastname = lastname;
       this.firstname = firstname;
       this.role = role;
       this.phone = phone;
       this.email = email;
       this.status = status;
       this.tips = tips;
       this.videos = videos;
       this.materials = materials;
       this.newses = newses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="USERNAME", nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="PASSWORD", nullable=false, length=50)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="LASTNAME", nullable=false, length=100)
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
    @Column(name="FIRSTNAME", nullable=false, length=100)
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    
    @Column(name="ROLE", nullable=false, length=10)
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    
    @Column(name="PHONE", nullable=false, length=15)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="EMAIL", nullable=false, length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="STATUS", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="admin")
    public Set<Tip> getTips() {
        return this.tips;
    }
    
    public void setTips(Set<Tip> tips) {
        this.tips = tips;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="admin")
    public Set<Video> getVideos() {
        return this.videos;
    }
    
    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="admin")
    public Set<Material> getMaterials() {
        return this.materials;
    }
    
    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="admin")
    public Set<News> getNewses() {
        return this.newses;
    }
    
    public void setNewses(Set<News> newses) {
        this.newses = newses;
    }




}


