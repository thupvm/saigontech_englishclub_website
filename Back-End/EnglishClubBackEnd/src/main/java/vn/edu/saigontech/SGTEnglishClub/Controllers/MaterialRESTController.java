package vn.edu.saigontech.SGTEnglishClub.Controllers;


import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.Material;
import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.MaterialsNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@RestController
public class MaterialRESTController {
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private MaterialTypeDAO eMTypeDAO;
	
	@Autowired
	private MaterialDAO eMaDAO;
	
	@RequestMapping(value = "/material", method = RequestMethod.GET)
	public CustomResponseEntity getAllMaterial() {
		return eMaDAO.getAllMaterial();
	}
	
	@RequestMapping(value = "/material/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialByID(@PathVariable int id) {
		return eMaDAO.getMaterialByID(id);
	}
	
	@RequestMapping(value = "/material/name/{eMa}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialByTitle(@PathVariable String eMa) {
		return eMaDAO.getMaterialByTitle(eMa);
	}
	
	@RequestMapping(value = "/manage/material", method = RequestMethod.POST)
	public CustomResponseEntity addMaterial(@RequestBody MaterialsNonMapping newMaterial){
		try {
			Material materialHibernate = new Material();
			materialHibernate.setAdmin((Admin) adminDAO.getAdminByID(newMaterial.getAdminId()).getData());
			
			materialHibernate.setMaterialtype((Materialtype) eMTypeDAO.getMaterialtypeByID(newMaterial.getMaterialtypeId()).getData());
			materialHibernate.setTitle(newMaterial.getTitle());
			materialHibernate.setTitlepicture(newMaterial.getTitlepicture());
			materialHibernate.setContent(newMaterial.getContent());
			materialHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(newMaterial.getPostdate()));
			materialHibernate.setStatus(newMaterial.isStatus());
			return eMaDAO.addMaterial(materialHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
		
	}
	
	@RequestMapping(value = "/manage/material/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteMaterial(@PathVariable int id){
		return eMaDAO.deleteMaterial(id);
		
	}
	

	@RequestMapping(value = "/manage/material", method = RequestMethod.PUT)
	public CustomResponseEntity updateMaterial(@RequestBody MaterialsNonMapping updateMa){
		try {
			Material materialHibernate = new Material();
			materialHibernate.setAdmin((Admin) adminDAO.getAdminByID(updateMa.getAdminId()).getData());
			materialHibernate.setId(updateMa.getId());
			materialHibernate.setMaterialtype((Materialtype) eMTypeDAO.getMaterialtypeByID(updateMa.getId()).getData());
			materialHibernate.setTitle(updateMa.getTitle());
			materialHibernate.setTitlepicture(updateMa.getTitlepicture());
			materialHibernate.setContent(updateMa.getContent());
			materialHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(updateMa.getPostdate()));
			materialHibernate.setStatus(updateMa.isStatus());
			return eMaDAO.updateMaterial(materialHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}

	}

	
	
}
