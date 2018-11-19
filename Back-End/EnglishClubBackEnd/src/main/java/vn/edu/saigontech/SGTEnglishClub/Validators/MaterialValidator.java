package vn.edu.saigontech.SGTEnglishClub.Validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Material;

@Transactional
public class MaterialValidator {
	@Autowired
	private MaterialDAO eMaterialDAO;
	
	public static String isValidForAdding(Material eMa) {
		String name = eMa.getTitle();
		if (name == "" || name == null)
			return "Please fill Title ";
		String name2 = eMa.getContent();
		if(name2 =="" || name2 == null)
			return "Please fill Content";
		
		return "";
	}
	
	public static String isValidForUpdating(Material eMa) {
		int id = eMa.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = eMa.getTitle();
		if (name == "" || name == null)
			return "Please fill Title";
		String name2 = eMa.getContent();
		if(name2 =="" || name2 == null)
			return "Please fill Content";

		return "";
	}
}
