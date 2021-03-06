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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="materialtype"
    ,catalog="sgt_englishclub"
)
public class Materialtype  implements java.io.Serializable {


     private Integer id;
     private String name;
     private boolean status;
     private Set<Material> materials = new HashSet<Material>(0);

    public Materialtype() {
    }

	
    public Materialtype(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    public Materialtype(String name, boolean status, Set<Material> materials) {
       this.name = name;
       this.status = status;
       this.materials = materials;
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
    @JsonIgnore
@OneToMany(fetch=FetchType.LAZY, mappedBy="materialtype")
    public Set<Material> getMaterials() {
        return this.materials;
    }
    
    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }




}


