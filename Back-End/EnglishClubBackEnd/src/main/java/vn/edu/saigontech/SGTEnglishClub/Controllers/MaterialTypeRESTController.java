package vn.edu.saigontech.SGTEnglishClub.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@RestController
public class MaterialTypeRESTController {
	@Autowired
	private MaterialTypeDAO eMTypeDAO;
	
	@RequestMapping(value = "/MaterialTypeREST", method = RequestMethod.GET)
	public CustomResponseEntity getAllMaterialTypes() {
		return eMTypeDAO.getAllMaterialtype();
	}
	
	@RequestMapping(value = "/MaterialTypeREST/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialTypeByID(@PathVariable int id) {
		return eMTypeDAO.getMaterialtypeByID(id);
	}
	
	@RequestMapping(value = "/MaterialTypeREST", method = RequestMethod.POST)
	public CustomResponseEntity addEMaterialType(@RequestBody Materialtype newEMType) {
		return eMTypeDAO.addMaterialType(newEMType);
	}
	
	@RequestMapping(value = "/MaterialTypeREST/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteEMaterialType(@PathVariable int id) {
		return eMTypeDAO.deleteMaterialType(id);
	}
	
	@RequestMapping(value = "/MaterialTypeREST", method = RequestMethod.PUT)
	public CustomResponseEntity updateEMaterialType(@RequestBody Materialtype updateEMType) {
		return eMTypeDAO.updateMaterialType(updateEMType);
	}
	

}
