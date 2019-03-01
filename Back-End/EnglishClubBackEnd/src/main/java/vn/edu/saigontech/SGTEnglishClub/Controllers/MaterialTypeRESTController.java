package vn.edu.saigontech.SGTEnglishClub.Controllers;

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
	
	@RequestMapping(value = "/materialType", method = RequestMethod.GET)
	public CustomResponseEntity getAllMaterialTypes() {
		return eMTypeDAO.getAllMaterialtypeAdmin();
	}
	
	@RequestMapping(value = "active/materialType", method = RequestMethod.GET)
	public CustomResponseEntity getAllActiveMaterialTypes() {
		return eMTypeDAO.getAllMaterialtype();
	}
	
	@RequestMapping(value = "/materialType/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialTypeByID(@PathVariable int id) {
		return eMTypeDAO.getMaterialtypeByID(id);
	}
	
	@RequestMapping(value = "/manage/materialType", method = RequestMethod.POST)
	public CustomResponseEntity addEMaterialType(@RequestBody Materialtype newEMType) {
		return eMTypeDAO.addMaterialType(newEMType);
	}
	
	@RequestMapping(value = "/manage/materialType/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteEMaterialType(@PathVariable int id) {
		return eMTypeDAO.deleteMaterialType(id);
	}
	
	@RequestMapping(value = "/manage/materialType", method = RequestMethod.PUT)
	public CustomResponseEntity updateEMaterialType(@RequestBody Materialtype updateEMType) {
		return eMTypeDAO.updateMaterialType(updateEMType);
	}
	

}
