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
@Table(name = "material_types", catalog = "sgt_englishclub")
public class MaterialTypes implements java.io.Serializable {

	private Integer id;
	private String name;
	private boolean status;
	private Set<Materials> materialses = new HashSet<Materials>(0);

	public MaterialTypes() {
	}

	public MaterialTypes(String name, boolean status) {
		this.name = name;
		this.status = status;
	}

	public MaterialTypes(String name, boolean status, Set<Materials> materialses) {
		this.name = name;
		this.status = status;
		this.materialses = materialses;
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

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATUS", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialTypes")
	public Set<Materials> getMaterialses() {
		return this.materialses;
	}

	public void setMaterialses(Set<Materials> materialses) {
		this.materialses = materialses;
	}

}
