package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@RestController
public class VideoTypeRESTController {
	@Autowired
	private VideoTypeDAO videoTypeDao;
	
	@RequestMapping(value="/videoType", method=RequestMethod.GET)
	public CustomResponseEntity getAllVideoTypes() {
		return videoTypeDao.getAllVideoType();
	}
	
	@RequestMapping(value="/videoType/{id}", method=RequestMethod.GET)
	public CustomResponseEntity getVideoTypeById(@PathVariable int id) {
		return videoTypeDao.getVideoTypeByID(id);
	}
	

	@RequestMapping(value="/manage/videoType", method=RequestMethod.POST)
	public CustomResponseEntity addVideoType(@RequestBody Videotype newVType) {
		return videoTypeDao.addVideoType(newVType);
	}
	
	@RequestMapping(value="/manage/videoType/{id}", method=RequestMethod.DELETE)
	public CustomResponseEntity deleteVideoType(@PathVariable int id) {
		return videoTypeDao.deleteVideoType(id);
	}
	
	@RequestMapping(value="/manage/videoType", method=RequestMethod.PUT)
	public CustomResponseEntity updateVideoType(@RequestBody Videotype updateVideoType) {
		return videoTypeDao.updateVideoType(updateVideoType);
	}
}

