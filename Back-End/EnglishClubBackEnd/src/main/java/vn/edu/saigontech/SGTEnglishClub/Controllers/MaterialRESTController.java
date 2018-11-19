package vn.edu.saigontech.SGTEnglishClub.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Material;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@RestController
public class MaterialRESTController {
	@Autowired
	private MaterialDAO eMaDAO;
	
	@RequestMapping(value = "/MaterialREST", method = RequestMethod.GET)
	public CustomResponseEntity getAllMaterial() {
		return eMaDAO.getAllMaterial();
	}
	
	@RequestMapping(value = "/MaterialREST/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialByID(@PathVariable int id) {
		return eMaDAO.getMaterialByID(id);
	}
	
	@RequestMapping(value = "/MaterialRESTByTitle/{eMa}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialByTitle(@PathVariable String eMa) {
		return eMaDAO.getMaterialByTitle(eMa);
	}
	
	@RequestMapping(value = "/MaterialREST", method = RequestMethod.POST)
	public CustomResponseEntity addMaterial(@RequestBody Material newEMa){
		return eMaDAO.addMaterial(newEMa);	
	}
	
	@RequestMapping(value = "/MaterialREST/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteMaterial(@PathVariable int id){
		return eMaDAO.deleteMaterial(id);
		
	}
	
	@RequestMapping(value = "/MaterialREST", method = RequestMethod.PUT)
	public CustomResponseEntity updateMaterial(@PathVariable Material updateEMa){
		return eMaDAO.updateMaterial(updateEMa);
	}

}
