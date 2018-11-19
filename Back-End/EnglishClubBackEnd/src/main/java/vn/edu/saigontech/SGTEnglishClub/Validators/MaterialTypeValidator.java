package vn.edu.saigontech.SGTEnglishClub.Validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;



@Transactional
public class MaterialTypeValidator {
	@Autowired
	private MaterialTypeDAO eMTypeDAO;
	
	public static String isValidForAdding(Materialtype eMType) {
		String name = eMType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
	
	public static String isValidForUpdating(Materialtype eMType) {
		int id = eMType.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = eMType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
}