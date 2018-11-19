package vn.edu.saigontech.SGTEnglishClub.Validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;


@Transactional
public class VideoTypeValidator {
	@Autowired
	private VideoTypeDAO vTypeDAO;
	
	public static String isValidForAdding(Videotype vType) {
		String name = vType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
	
	public static String isValidForUpdating(Videotype vType) {
		int id = vType.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = vType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
}