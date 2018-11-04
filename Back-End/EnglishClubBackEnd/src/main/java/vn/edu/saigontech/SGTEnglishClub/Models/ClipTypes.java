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
@Table(name="clip_types"
    ,catalog="sgt_englishclub"
)
public class ClipTypes  implements java.io.Serializable {


     private Integer id;
     private String name;
     private boolean status;
     private Set<Clips> clipses = new HashSet<Clips>(0);

    public ClipTypes() {
    }

	
    public ClipTypes(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    public ClipTypes(String name, boolean status, Set<Clips> clipses) {
       this.name = name;
       this.status = status;
       this.clipses = clipses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="NAME", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="STATUS", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="clipTypes")
    public Set<Clips> getClipses() {
        return this.clipses;
    }
    
    public void setClipses(Set<Clips> clipses) {
        this.clipses = clipses;
    }




}


