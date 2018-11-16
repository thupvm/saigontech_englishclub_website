package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.saigontech.SGTEnglishClub.DAOs.videoDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@CrossOrigin
@RestController
@RequestMapping("/manage")
public class videoController {
	@Autowired
	private videoDAO videoDAO;
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public CustomResponseEntity getAllVideo() {
		return videoDAO.getAllVideo();
	}
	
	@RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getVideoByID(@PathVariable("id") int id) {
		return videoDAO.getVideoByID(id);
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public CustomResponseEntity addVideo(@RequestBody Video newVideo) {
		return videoDAO.addVideo(newVideo);
	}
	
	@RequestMapping(value = "/video/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteVideo(@PathVariable("id") int id) {
		return videoDAO.deleteVideo(id);
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.PUT)
	public CustomResponseEntity updateVideo(@RequestBody Video newVideo) {
		return videoDAO.updateVideo(newVideo);
	}
}
