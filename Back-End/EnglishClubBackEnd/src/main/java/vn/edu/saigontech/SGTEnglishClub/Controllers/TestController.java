package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;

@RestController
public class TestController {
	
	@Autowired
	private AdminDAO adminDAO;
	
	@RequestMapping("/manage/secret/test")
	public String getType() {
		return adminDAO.getIDbyUsername("thupvm")+"";
	}
	
	@RequestMapping("/manage/content/test")
	public String getContent() {
		return "get content ok";
	}
	
	@RequestMapping("/test")
	public String getAny() {
		return "get any ok";
	}

}
