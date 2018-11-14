package vn.edu.saigontech.SGTEnglishClub.Controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.Configurations.TokenAuthenticationService;
import vn.edu.saigontech.SGTEnglishClub.DAOs.adminDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.admin;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Responses.LoginResponse;

@CrossOrigin
@RestController
public class LoginController {
	@Autowired
	private adminDAO adminDAO;

	@Autowired
	ServletContext servletContext;

	
	
	@RequestMapping(value = "/manage/login", method = RequestMethod.POST)
	public CustomResponseEntity login(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		
		admin targetAdmin = adminDAO.getAdminbyUsername(username, password);
		if (targetAdmin != null) {
			String accessToken = TokenAuthenticationService.getValidateToken(targetAdmin.getUserName());

			LoginResponse lR = new LoginResponse(targetAdmin.getId(),
					targetAdmin.getFirstName() + " " + targetAdmin.getLastName(), accessToken);

			return CustomResponseEntity.getOKResponse("Log-in successfully", lR);
		} else
			return CustomResponseEntity.getWrongUsernamePassword();
		
	}

}
