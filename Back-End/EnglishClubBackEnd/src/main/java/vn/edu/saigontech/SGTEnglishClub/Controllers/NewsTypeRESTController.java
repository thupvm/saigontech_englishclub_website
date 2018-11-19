package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Newstype;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;


@RestController
public class NewsTypeRESTController {
	@Autowired
	private NewsTypeDAO newsTypeDAO;
	
	@RequestMapping(value = "/NewsTypeREST", method = RequestMethod.GET)
	public CustomResponseEntity getAllNewsTypes() {
		return newsTypeDAO.getAllNewsType();
	}
	
	@RequestMapping(value = "/NewsTypeREST/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getNewsTypeByID(@PathVariable int id) {
		return newsTypeDAO.getNewsTypeByID(id);
	}
	
	@RequestMapping(value = "/NewsTypeREST", method = RequestMethod.POST)
	public CustomResponseEntity addNewsType(@RequestBody Newstype newNType) {
		return newsTypeDAO.addNewsType(newNType);
	}
	
	@RequestMapping(value = "/NewsTypeREST/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteNewsType(@PathVariable int id) {
		return newsTypeDAO.deleteNewsType(id);
	}
	
	@RequestMapping(value = "/NewsTypeREST", method = RequestMethod.PUT)
	public CustomResponseEntity updateNewsType(@RequestBody Newstype updateNType) {
		return newsTypeDAO.updateNewsType(updateNType);
	}
	
	//@RequestMapping(value = "/newsTypeStatistic/{newsStr}", method = RequestMethod.GET)
	//public CustomResponseEntity NewsTypeStatistic(@PathVariable String newsStr) {
		//return newsTypeDAO.getNewsTypeStatistic(newsStr);
	//}
	

}
