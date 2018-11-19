package vn.edu.saigontech.SGTEnglishClub.Validators;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.News;







@Transactional
public class NewsValidator {
	@Autowired
	private NewsDAO newsDAO;
	
	public static String isValidForAdding(News newsName) {
		String name = newsName.getTitle();
		if (name == "" || name == null)
			return "Please fill Title ";
		String name2 = newsName.getContent();
		if(name2 =="" || name2 == null)
			return "Please fill Content";
		
		return "";
	}
	
	public static String isValidForUpdating(News newsName) {
		int id = newsName.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = newsName.getTitle();
		if (name == "" || name == null)
			return "Please fill Title";
		String name2 = newsName.getContent();
		if(name2 =="" || name2 == null)
			return "Please fill Content";

		return "";
	}
}

