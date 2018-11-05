package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admins;

@RestController
@RequestMapping("/manage")
public class AdminController {

	@Autowired
	private AdminDAO adminDAO;
	
	@RequestMapping(value="/Admin/{username}", method = RequestMethod.GET)
	public Admins getAdminByUsername(@PathVariable("username") String username) {
		return adminDAO.getAdminbyUsername(username);
	}
}
