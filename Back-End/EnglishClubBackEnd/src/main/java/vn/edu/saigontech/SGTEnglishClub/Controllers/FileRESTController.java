package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.saigontech.SGTEnglishClub.DAOs.FileDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.File;
import vn.edu.saigontech.SGTEnglishClub.Models.Material;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Utils.fileUploadUtils;

@RestController
public class FileRESTController {

	@Autowired
	private FileDAO fileDao;
	@Autowired
	private MaterialDAO eMaDAO;

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public CustomResponseEntity getAllFile() {
		return fileDao.getAllFile();
	}

	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getFileByID(@PathVariable int id) {
		return fileDao.getFileByID(id);
	}

	@RequestMapping(value = "/file/material/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getFileByMaterialID(@PathVariable int id) {
		return fileDao.getFileByMaterialID(id);
	}

	@RequestMapping(value = "/file/{fileStr}", method = RequestMethod.GET)
	public CustomResponseEntity getFileByFileName(@PathVariable String fileStr) {
		return fileDao.getFileByNameFile(fileStr);
	}

//	@RequestMapping(value = "/manage/file", method = RequestMethod.POST)
//	public CustomResponseEntity addFile(@RequestBody File newFile) {
//		return fileDao.addFile(newFile);
//	}
	
	@RequestMapping(value = "/manage/file", method = RequestMethod.POST)
	public CustomResponseEntity addFile(@RequestParam("ebookFile") MultipartFile newFile,
			@RequestParam("materialID") int materialID,
			@RequestParam("status") boolean status,
			HttpServletRequest req) {
		Material targetMaterial = (Material) eMaDAO.getMaterialByID(materialID).getData();
		String fileName;
		try {
			fileName = fileUploadUtils.saveUploadedFile(newFile, req.getServletContext().getRealPath("/images/"));
			File targetFile = new File(targetMaterial, fileName, status);
			
			return fileDao.addFile(targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
		
	}

	@RequestMapping(value = "/manage/file/material/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getFileByMaterialIDAdmin(@PathVariable int id) {
		return fileDao.getFileByMaterialIDAdmin(id);
	}

	@RequestMapping(value = "/manage/file/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteFile(@PathVariable int id, HttpServletRequest req) {
		return fileDao.deleteFile(id, req);
	}

	@RequestMapping(value = "/manage/file", method = RequestMethod.PUT)
	public CustomResponseEntity updateFile(@RequestBody File updateFile) {
		return fileDao.updateFile(updateFile);
	}
}
