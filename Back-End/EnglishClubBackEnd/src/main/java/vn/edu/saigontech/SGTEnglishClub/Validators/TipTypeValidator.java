package vn.edu.saigontech.SGTEnglishClub.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.TipTypeDAO;

import vn.edu.saigontech.SGTEnglishClub.Models.TipType;






@Transactional
public class TipTypeValidator {
	@Autowired
	private TipTypeDAO tTypeDAO;
	
	public static String isValidForAdding(TipType tType) {
		String name = tType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
	
	public static String isValidForUpdating(TipType tType) {
		int id = tType.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = tType.getName();
		if (name == "" || name == null)
			return "Please fill title name";
		
		return "";
	}
}