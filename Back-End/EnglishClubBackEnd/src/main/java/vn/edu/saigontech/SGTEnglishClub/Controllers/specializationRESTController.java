package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.specializationDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Specialization;

import org.springframework.web.bind.annotation.RequestMethod;

@Transactional
@RestController
@RequestMapping("/rest")
public class specializationRESTController {
	
	@Autowired
	private specializationDAO specializationDAO;
	
	@RequestMapping(value = "SpecializationREST", method = RequestMethod.GET)
	public ResponseEntity<List<Specialization>> getAllSpec(){
		ResponseEntity<List<Specialization>> res = new ResponseEntity<>(specializationDAO.getAllSpecialization(), HttpStatus.OK);
		return res;
	}
	
	@RequestMapping(value = "/SpecializationREST/{id}", method = RequestMethod.GET)
	public Specialization getSpecByID(@PathVariable int id){
		
		
		return specializationDAO.getSpecializationByID(id);
	}
	
	@RequestMapping(value = "/SpecializationREST", method = RequestMethod.POST)
	public Specialization addSpec(@RequestBody Specialization newSpec ){
		
		
		return specializationDAO.addSpecialization(newSpec);
	}
	
	@RequestMapping(value = "/SpecializationREST/{id}", method = RequestMethod.DELETE)
	public String deleteSpec(@PathVariable int id){
		
		
		return specializationDAO.deleteSpecialization(id);
	}
	
	@RequestMapping(value = "/SpecializationREST", method = RequestMethod.PUT)
	public Specialization updateSpec(@RequestBody Specialization targetSpec ){
		
		
		return specializationDAO.updateSpecialization(targetSpec);
	}
	
}
