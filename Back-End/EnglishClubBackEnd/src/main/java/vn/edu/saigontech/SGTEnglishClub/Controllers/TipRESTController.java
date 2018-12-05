package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.Tip;
import vn.edu.saigontech.SGTEnglishClub.Models.TipType;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Utils.fileUploadUtils;

@RestController
public class TipRESTController {
	@Autowired
	private TipTypeDAO tipTypeDAO;

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private TipDAO tipDAO;

	@RequestMapping(value = "/tip", method = RequestMethod.GET)
	public CustomResponseEntity getAllTips() {
		return tipDAO.getAllTipForClient();
	}

	@RequestMapping(value = "/tip/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getTipById(@PathVariable int id) {
		return tipDAO.getTipByID(id);
	}

	@RequestMapping(value = "/tip/title/{newsStr}", method = RequestMethod.GET)
	public CustomResponseEntity getTipByTitle(@PathVariable String newsStr) {
		return tipDAO.getTipByTitle(newsStr);
	}
	
	@RequestMapping(value = "/manage/tip", method = RequestMethod.GET)
	public CustomResponseEntity getAllTips1() {
		return tipDAO.getAllTipForAdmin();
	}


	@RequestMapping(value = "/manage/tip", method = RequestMethod.POST)
	public CustomResponseEntity addTip(@RequestParam("adminID") int adminID, @RequestParam("tipTypeID") int tipTypeID,
			@RequestParam("title") String title, @RequestParam("titleImage") MultipartFile image,
			@RequestParam("content") String content, @RequestParam("postDate") String postDate,
			HttpServletRequest req) {
		try {
			Tip tipHibernate = new Tip();
			tipHibernate.setAdmin((Admin) adminDAO.getAdminByID(adminID).getData());
			tipHibernate.setTiptype((TipType) tipTypeDAO.getTipTypeByID(tipTypeID).getData());
			tipHibernate.setTitle(title);
			tipHibernate.setTitlepicture(
					fileUploadUtils.saveUploadedFile(image, req.getServletContext().getRealPath("/images/")));

			tipHibernate.setContent(content);
			tipHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(postDate));
			tipHibernate.setStatus(true);

			return tipDAO.addTip(tipHibernate);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	@RequestMapping(value = "/manage/tip/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteTip(@PathVariable int id, HttpServletRequest req) {
		Tip currentTip = (Tip) tipDAO.getTipByID(id).getData();
		
		fileUploadUtils.deleteUploadFile(currentTip.getTitlepicture(), req.getServletContext().getRealPath("/images/"));
		
		return tipDAO.deleteTip(id);

	}

	@RequestMapping(value = "/manage/tip/{id}", method = RequestMethod.POST)
	public CustomResponseEntity updateTip(@PathVariable("id") int id, 
			@RequestParam("adminID") int adminID,
			@RequestParam("tipTypeID") int tipTypeID, 
			@RequestParam("title") String title,
			@RequestParam(value ="titleImage", required = false) MultipartFile image, 
			@RequestParam("content") String content,
			@RequestParam("postDate") String postDate,
			@RequestParam("status") boolean status, 
			HttpServletRequest req) {
		try {

			Tip tipHibernate = (Tip) tipDAO.getTipByID(id).getData();

			tipHibernate.setAdmin((Admin) adminDAO.getAdminByID(adminID).getData());
			tipHibernate.setTiptype((TipType) tipTypeDAO.getTipTypeByID(tipTypeID).getData());
			tipHibernate.setTitle(title);

			if (image != null) {
				fileUploadUtils.deleteUploadFile(tipHibernate.getTitlepicture(),
						req.getServletContext().getRealPath("/images/"));

				tipHibernate.setTitlepicture(
						fileUploadUtils.saveUploadedFile(image, req.getServletContext().getRealPath("/images/")));
			}
			tipHibernate.setContent(content);
			tipHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(postDate));
			tipHibernate.setStatus(status);
			return tipDAO.updateTip(tipHibernate);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

}
