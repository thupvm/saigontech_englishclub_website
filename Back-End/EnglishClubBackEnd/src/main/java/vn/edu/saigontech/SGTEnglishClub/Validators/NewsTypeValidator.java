package vn.edu.saigontech.SGTEnglishClub.Validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Newstype;




@Transactional
public class NewsTypeValidator {
	@Autowired
	private NewsTypeDAO newstypesDAO;
	
	public static String isValidForAdding(Newstype nType) {
		String name = nType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
	
	public static String isValidForUpdating(Newstype nType) {
		int id = nType.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = nType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
	


}