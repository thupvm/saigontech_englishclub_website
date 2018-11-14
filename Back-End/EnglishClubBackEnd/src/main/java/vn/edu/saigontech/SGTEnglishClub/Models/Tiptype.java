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
@Table(name="tiptype"
    ,catalog="sgt_englishclub"
)
public class Tiptype  implements java.io.Serializable {


     private Integer id;
     private String name;
     private boolean status;
     private Set<Tip> tips = new HashSet<Tip>(0);

    public Tiptype() {
    }

	
    public Tiptype(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    public Tiptype(String name, boolean status, Set<Tip> tips) {
       this.name = name;
       this.status = status;
       this.tips = tips;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="tiptype")
    public Set<Tip> getTips() {
        return this.tips;
    }
    
    public void setTips(Set<Tip> tips) {
        this.tips = tips;
    }




}


