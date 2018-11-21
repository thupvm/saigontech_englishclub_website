package vn.edu.saigontech.SGTEnglishClub.Controllers;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.News;
import vn.edu.saigontech.SGTEnglishClub.Models.Newstype;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.NewsNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

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
	
	@RequestMapping(value = "/manage/news", method = RequestMethod.POST, consumes = "application/json")
	public CustomResponseEntity addNews(@RequestBody NewsNonMapping newsAdd){
		
		try {
			News newsHibernate = new News();
			newsHibernate.setAdmin((Admin) adminDAO.getAdminByID(newsAdd.getAdminId()).getData());
			
			newsHibernate.setNewstype((Newstype) newsTypeDAO.getNewsTypeByID(newsAdd.getNewstypeId()).getData());
			newsHibernate.setTitle(newsAdd.getTitle());
			
			newsHibernate.setThumbnailpicturetitle(newsAdd.getThumbnailpicturetitle());
			newsHibernate.setBigpicturetitle(newsAdd.getBigpicturetitle());
			newsHibernate.setContent(newsAdd.getContent());
			newsHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(newsAdd.getPostdate()));
			newsHibernate.setStatus(newsAdd.isStatus());
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
	
	@RequestMapping(value = "/manage/news", method = RequestMethod.PUT, consumes = "application/json")
	public CustomResponseEntity updateNews(@RequestBody NewsNonMapping updateNews){
		try {
			News newsHibernate = new News();
			newsHibernate.setId(updateNews.getId());
			newsHibernate.setAdmin((Admin) adminDAO.getAdminByID(updateNews.getAdminId()).getData());
			
			newsHibernate.setNewstype((Newstype) newsTypeDAO.getNewsTypeByID(updateNews.getNewstypeId()).getData());
			newsHibernate.setTitle(updateNews.getTitle());
			
			newsHibernate.setThumbnailpicturetitle(updateNews.getThumbnailpicturetitle());
			newsHibernate.setBigpicturetitle(updateNews.getBigpicturetitle());
			newsHibernate.setContent(updateNews.getContent());
			newsHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(updateNews.getPostdate()));
			newsHibernate.setStatus(updateNews.isStatus());
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
