package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@CrossOrigin
@RestController
public class LoginRESTController {
	@Autowired
	private AdminDAO adminDAO;

	@RequestMapping(value = "/manage/login", method = RequestMethod.POST)
	public CustomResponseEntity login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return adminDAO.login(username, password);
	}
}
