package vn.edu.saigontech.SGTEnglishClub.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Tip;


@Transactional
public class TipValidator {
	@Autowired
	private TipDAO tipDAO;
	
	public static String isValidForAdding(Tip tipsName) {
		String name = tipsName.getTitle();
		if (name == "" || name == null)
			return "Please fill Title ";
		String name2 = tipsName.getContent();
		if(name2 =="" || name2 == null)
			return "Please fill Content";
		
		return "";
	}
	
	public static String isValidForUpdating(Tip tipsName) {
		int id = tipsName.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = tipsName.getTitle();
		if (name == "" || name == null)
			return "Please fill Title";
		String name2 = tipsName.getContent();
		if(name2 =="" || name2 == null)
			return "Please fill Content";

		return "";
	}
}
