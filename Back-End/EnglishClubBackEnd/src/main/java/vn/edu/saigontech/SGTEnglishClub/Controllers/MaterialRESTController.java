package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.FileDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.File;
import vn.edu.saigontech.SGTEnglishClub.Models.Material;
import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.MaterialsNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Utils.fileUploadUtils;

@RestController
public class MaterialRESTController {
	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private MaterialTypeDAO eMTypeDAO;

	@Autowired
	private MaterialDAO eMaDAO;

	@Autowired
	private FileDAO fileDAO;

	@RequestMapping(value = "/material", method = RequestMethod.GET)
	public CustomResponseEntity getAllMaterial() {
		return eMaDAO.getAllMaterial();
	}

	@RequestMapping(value = "/manage/material", method = RequestMethod.GET)
	public CustomResponseEntity getAllMaterialAdmin() {
		return eMaDAO.getAllMaterialAdmin();
	}

	@RequestMapping(value = "/material/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialByID(@PathVariable int id) {
		return eMaDAO.getMaterialByID(id);
	}

	@RequestMapping(value = "/material/name/{eMa}", method = RequestMethod.GET)
	public CustomResponseEntity getMaterialByTitle(@PathVariable String eMa) {
		return eMaDAO.getMaterialByTitle(eMa);
	}

	@RequestMapping(value = "/manage/material", method = RequestMethod.POST)
	public CustomResponseEntity addMaterial(@RequestBody MaterialsNonMapping newMaterial) {
		try {
			Material materialHibernate = new Material();
			materialHibernate.setAdmin((Admin) adminDAO.getAdminByID(newMaterial.getAdminId()).getData());

			materialHibernate.setMaterialtype(
					(Materialtype) eMTypeDAO.getMaterialtypeByID(newMaterial.getMaterialtypeId()).getData());
			materialHibernate.setTitle(newMaterial.getTitle());
			materialHibernate.setTitlepicture(newMaterial.getTitlepicture());
			materialHibernate.setContent(newMaterial.getContent());
			materialHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(newMaterial.getPostdate()));
			materialHibernate.setStatus(newMaterial.isStatus());
			return eMaDAO.addMaterial(materialHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}

	}

	@RequestMapping(value = "/manage/materialandfile", method = RequestMethod.POST)
	public CustomResponseEntity addMaterial(@RequestParam("adminID") int adminID,
			@RequestParam("eMaterialTypeID") int eMaterialTypeID, @RequestParam("title") String title,
			@RequestParam("titleImage") MultipartFile image, @RequestParam("content") String content,
			@RequestParam("postdate") String postDate, @RequestParam("ebooks") MultipartFile[] ebooks,
			HttpServletRequest req) {
		try {
			Material materialHibernate = new Material();
			materialHibernate.setAdmin((Admin) adminDAO.getAdminByID(adminID).getData());

			materialHibernate.setMaterialtype((Materialtype) eMTypeDAO.getMaterialtypeByID(eMaterialTypeID).getData());
			materialHibernate.setTitle(title);
			materialHibernate.setTitlepicture(
					fileUploadUtils.saveUploadedFile(image, req.getServletContext().getRealPath("/images/")));
			materialHibernate.setContent(content);
			materialHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(postDate));
			materialHibernate.setStatus(true);
			Material resEMaterial = (Material) eMaDAO.addMaterial(materialHibernate).getData();

			for (MultipartFile ebook : ebooks) {
				File currentEBook = new File(resEMaterial,
						fileUploadUtils.saveUploadedFile(ebook, req.getServletContext().getRealPath("/images/")), true);
				fileDAO.addFile(currentEBook);
			}

			return CustomResponseEntity.getOKResponse("Adding material and their ebook successfully!", resEMaterial);

		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}

	}

	@RequestMapping(value = "/manage/material/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteMaterial(@PathVariable int id) {
		return eMaDAO.deleteMaterial(id);

	}

	@RequestMapping(value = "/manage/material/{id}", method = RequestMethod.POST)
	public CustomResponseEntity updateMaterial(@PathVariable("id") int id, @RequestParam("adminID") int adminID,
			@RequestParam("eMaterialTypeID") int eMaterialTypeID, @RequestParam("title") String title,
			@RequestParam(value ="titleImage", required = false) MultipartFile image, @RequestParam("content") String content,
			@RequestParam("postdate") String postDate, @RequestParam("status") boolean status, HttpServletRequest req) {
		try {
			Material materialHibernate = (Material) eMaDAO.getMaterialByID(id).getData();
			materialHibernate.setAdmin((Admin) adminDAO.getAdminByID(adminID).getData());
			materialHibernate.setId(id);
			materialHibernate.setMaterialtype((Materialtype) eMTypeDAO.getMaterialtypeByID(eMaterialTypeID).getData());
			materialHibernate.setTitle(title);
			if (image != null) {
				fileUploadUtils.deleteUploadFile(materialHibernate.getTitlepicture(),
						req.getServletContext().getRealPath("/images/"));

				materialHibernate.setTitlepicture(
						fileUploadUtils.saveUploadedFile(image, req.getServletContext().getRealPath("/images/")));
			}
			materialHibernate.setContent(content);
			materialHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(postDate));
			materialHibernate.setStatus(status);
			return eMaDAO.updateMaterial(materialHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}

	}

}
