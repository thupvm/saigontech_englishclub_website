package vn.edu.saigontech.SGTEnglishClub.Controllers;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.News;
import vn.edu.saigontech.SGTEnglishClub.Models.Newstype;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.NewsNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Utils.fileUploadUtils;

@RestController
public class NewsRESTController {
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private NewsTypeDAO newsTypeDAO;
	
	@Autowired
	private NewsDAO newsDAO;
	
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public CustomResponseEntity getAllNews() {
		return newsDAO.getAllNews();
	}
	
	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getNewsByID(@PathVariable int id) {
		return newsDAO.getNewsByID(id);
	}
	
	@RequestMapping(value = "/news/title/{newsStr}", method = RequestMethod.GET)
	public CustomResponseEntity getNewsByTitle(@PathVariable String newsStr) {
		return newsDAO.getNewsByTitle(newsStr);
	}
	
	@RequestMapping(value = "/manage/news", method = RequestMethod.POST)
	public CustomResponseEntity addNews(@RequestParam("adminID") int adminID, @RequestParam("newsTypeID") int newsTypeID,
			@RequestParam("title") String title, @RequestParam("thumbnailImage") MultipartFile thumbImage,
			@RequestParam("bigImage") MultipartFile bigImage,
			@RequestParam("content") String content, @RequestParam("postDate") String postDate,
			HttpServletRequest req){
		
		try {
			News newsHibernate = new News();
			newsHibernate.setAdmin((Admin) adminDAO.getAdminByID(adminID).getData());
			
			newsHibernate.setNewstype((Newstype) newsTypeDAO.getNewsTypeByID(newsTypeID).getData());
			newsHibernate.setTitle(title);
			
			newsHibernate.setThumbnailpicturetitle(fileUploadUtils.saveUploadedFile(thumbImage, req.getServletContext().getRealPath("/images/")));
			
			newsHibernate.setBigpicturetitle(fileUploadUtils.saveUploadedFile(bigImage, req.getServletContext().getRealPath("/images/")));
			newsHibernate.setContent(content);
			newsHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(postDate));
			newsHibernate.setStatus(true);
			return newsDAO.addNews(newsHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}
	
	
	@RequestMapping(value = "/manage/news/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteNews(@PathVariable int id){
		return newsDAO.deleteNews(id);
		
	}
	
	@RequestMapping(value = "/manage/news/{id}", method = RequestMethod.POST)
	public CustomResponseEntity updateNews(@PathVariable("id") int id,
			@RequestParam("adminID") int adminID, @RequestParam("newsTypeID") int newsTypeID,
			@RequestParam("title") String title, @RequestParam(value = "thumbnailImage", required = false) MultipartFile thumbImage,
			@RequestParam(value = "bigImage", required = false) MultipartFile bigImage,
			@RequestParam("content") String content, @RequestParam("postDate")String postDate,
			@RequestParam("status") boolean status,
			HttpServletRequest req){
		try {
			News newsHibernate = (News) newsDAO.getNewsByID(id).getData();
			newsHibernate.setId(id);
			newsHibernate.setAdmin((Admin) adminDAO.getAdminByID(adminID).getData());
			
			newsHibernate.setNewstype((Newstype) newsTypeDAO.getNewsTypeByID(newsTypeID).getData());
			newsHibernate.setTitle(title);
			
			
			
			if (thumbImage != null) 
				newsHibernate.setThumbnailpicturetitle(fileUploadUtils.saveUploadedFile(thumbImage, req.getServletContext().getRealPath("/images/")));
			
			
			
			if (bigImage != null)
				newsHibernate.setBigpicturetitle(fileUploadUtils.saveUploadedFile(bigImage, req.getServletContext().getRealPath("/images/")));
			
			newsHibernate.setContent(content);
			newsHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(postDate));
			newsHibernate.setStatus(status);
			return newsDAO.updateNews(newsHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}
	
	@RequestMapping(value="/manage/news/searchNews/{someString}", method = RequestMethod.GET)
	public CustomResponseEntity searchNews(@PathVariable String someString) {
		return newsDAO.searchNews(someString);
	}

}
