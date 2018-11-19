package vn.edu.saigontech.SGTEnglishClub.Validators;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;





@Transactional
public class VideoValidator {
	@Autowired
	private VideoDAO videoDAO;
	
	public static String isValidForAdding(Video videoName) {
		String name = videoName.getTitle();
		if (name == "" || name == null)
			return "Please fill Title ";
		
		return "";
	}
	
	public static String isValidForUpdating(Video videoName) {
		int id = videoName.getId();
		if (Double.isNaN(id) || id < 1)
			return "Please fill id";
		
		String name = videoName.getTitle();
		if (name == "" || name == null)
			return "Please fill Title";
		return "";
	}
}
