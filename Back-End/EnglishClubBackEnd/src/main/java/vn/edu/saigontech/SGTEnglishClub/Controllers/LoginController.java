package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.Configurations.TokenAuthenticationService;
import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admins;
import vn.edu.saigontech.SGTEnglishClub.Requests.loginInfo;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Responses.LoginResponse;
@CrossOrigin
@RestController
public class LoginController {
	@Autowired
	private AdminDAO adminDAO;
	
	@RequestMapping(value="/manage/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomResponseEntity login(@RequestBody loginInfo li) {
		
		Admins targetAdmin = adminDAO.getAdminbyUsername(li.getUsername(), li.getPassword());
		if (targetAdmin != null) {
			String accessToken = TokenAuthenticationService.getValidateToken(targetAdmin.getUsername());
			
			LoginResponse lR = new LoginResponse(targetAdmin.getId(), targetAdmin.getFullname(), accessToken);
			
			return CustomResponseEntity.getOKResponse("Log-in successfully", lR);
		} else return CustomResponseEntity.getWrongUsernamePassword();
	}

}
