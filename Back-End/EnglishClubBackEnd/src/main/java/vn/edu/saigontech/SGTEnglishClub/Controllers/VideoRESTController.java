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

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;                                                   
import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.VideoNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.test;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@CrossOrigin
@RestController
@RequestMapping                                                                                                                                                                                                                                                        
public class VideoRESTController {
	@Autowired
	private VideoDAO videoDAO;

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private VideoTypeDAO videoTypeDAO;

	@RequestMapping(value = "/Video", method = RequestMethod.GET)
	public CustomResponseEntity getAllVideo() {
		return videoDAO.getAllVideo();
	}

	@RequestMapping(value = "/Video/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getVideoByID(@PathVariable("id") int id) {
		return videoDAO.getVideoByID(id);
	}

	@RequestMapping(value = "/Video", method = RequestMethod.POST, consumes = "application/json")
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

	@RequestMapping(value = "/Video/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteVideo(@PathVariable("id") int id) {
		return videoDAO.deleteVideo(id);
	}

	@RequestMapping(value = "/Video", method = RequestMethod.PUT, consumes = "application/json")
	public CustomResponseEntity updateVideo(@RequestBody VideoNonMapping updateVideo) {
		try {
			Video newVideoHibernate = new Video();
			newVideoHibernate.setId(updateVideo.getId());
			newVideoHibernate.setAdmin((Admin) adminDAO.getAdminByID(updateVideo.getAdminID()).getData());
			newVideoHibernate.setDescription(updateVideo.getDescription());
			newVideoHibernate.setLink(updateVideo.getLink());
			newVideoHibernate.setPostdate(new SimpleDateFormat("dd/MM/yyyy").parse(updateVideo.getPostdate()));
			newVideoHibernate.setStatus(updateVideo.isStatus());
			newVideoHibernate.setTitle(updateVideo.getTitle());
			newVideoHibernate.setVideotype((Videotype) videoTypeDAO.getVideoTypeByID(updateVideo.getVideoTypeID()).getData());
		
			return videoDAO.updateVideo(newVideoHibernate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
		
		
	}
}
