package vn.edu.saigontech.SGTEnglishClub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.FileDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.File;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;





@RestController
public class FileRESTController {

	@Autowired
	private FileDAO fileDao;
	
	@RequestMapping(value = "/FileREST", method = RequestMethod.GET)
	public CustomResponseEntity getAllAdmin() {
		return fileDao.getAllFile();
	}
	
	@RequestMapping(value = "/FileREST/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getAdminByID(@PathVariable int id) {
		return fileDao.getFileByID(id);
	}
	
	@RequestMapping(value = "/FileRESTByFileName/{fileStr}", method = RequestMethod.GET)
	public CustomResponseEntity getFileByFileName(@PathVariable String fileStr) {
		return fileDao.getFileByNameFile(fileStr);
	}
	
	@RequestMapping(value="/FileREST", method = RequestMethod.POST)
	public CustomResponseEntity addFile(@RequestBody File newFile) {
		return fileDao.addFile(newFile);
	}
	
	@RequestMapping(value="/FileREST/{id}", method=RequestMethod.DELETE)
	public CustomResponseEntity deleteFile(@PathVariable int id) {
		return fileDao.deleteFile(id);
	}
	
	@RequestMapping(value="FileREST", method=RequestMethod.PUT)
	public CustomResponseEntity updateFile(@RequestBody File updateFile) {
		return fileDao.updateFile(updateFile);
	}
}
