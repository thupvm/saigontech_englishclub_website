package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.saigontech.SGTEnglishClub.DAOs.videoDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.adminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.videoTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.VideoNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.test;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@CrossOrigin
@RestController
@RequestMapping("/manage")
public class videoController {
	@Autowired
	private videoDAO videoDAO;

	@Autowired
	private adminDAO adminDAO;

	@Autowired
	private videoTypeDAO videoTypeDAO;

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public CustomResponseEntity getAllVideo() {
		return videoDAO.getAllVideo();
	}

	@RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getVideoByID(@PathVariable("id") int id) {
		return videoDAO.getVideoByID(id);
	}

	@RequestMapping(value = "/video", method = RequestMethod.POST, consumes = "application/json")
	public CustomResponseEntity addVideo(@RequestBody VideoNonMapping newVideo) {
		try {
			Video newVideoHibernate = new Video();
			newVideoHibernate.setAdmin((Admin) adminDAO.getAdminByID(newVideo.getAdminID()).getData());
			newVideoHibernate.setDescription(newVideo.getDescription());
			newVideoHibernate.setLink(newVideo.getLink());
			newVideoHibernate.setPostdate(new SimpleDateFormat("dd/MM/yyyy").parse(newVideo.getPostdate()));
			newVideoHibernate.setStatus(newVideo.isStatus());
			newVideoHibernate.setTitle(newVideo.getTitle());
			newVideoHibernate.setVideotype((Videotype) videoTypeDAO.getVideoTypeByID(newVideo.getVideoTypeID()).getData());
		
			return videoDAO.addVideo(newVideoHibernate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
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
