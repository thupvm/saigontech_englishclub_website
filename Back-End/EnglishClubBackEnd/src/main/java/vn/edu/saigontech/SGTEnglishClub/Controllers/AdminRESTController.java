package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@CrossOrigin
@RestController
@RequestMapping("/manage")
public class AdminRESTController {
	@Autowired
	private AdminDAO adminDAO;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public CustomResponseEntity getAllAdmin() {
		return adminDAO.getAllAdmin();
	}
	
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getAdminByID(@PathVariable("id") int id) {
		return adminDAO.getAdminByID(id);
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public CustomResponseEntity addAdmin(@RequestBody Admin newAdmin) {
		return adminDAO.addAdmin(newAdmin);
	}
	
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteAdmin(@PathVariable("id") int id) {
		return adminDAO.deleteAdmin(id);
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.PUT)
	public CustomResponseEntity updateAdmin(@RequestBody Admin newAdmin) {
		return adminDAO.updateAdmin(newAdmin);
	}

}
