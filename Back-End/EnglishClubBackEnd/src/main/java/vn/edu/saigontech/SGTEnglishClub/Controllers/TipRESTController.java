package vn.edu.saigontech.SGTEnglishClub.Controllers;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.Tip;
import vn.edu.saigontech.SGTEnglishClub.Models.TipType;
import vn.edu.saigontech.SGTEnglishClub.Models.nonMapping.TipNonMapping;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@RestController
public class TipRESTController {
	@Autowired
	private TipTypeDAO tipTypeDAO;

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private TipDAO tipDAO;

	@RequestMapping(value = "/TipREST", method = RequestMethod.GET)
	public CustomResponseEntity getAllTips() {
		return tipDAO.getAllTip();
	}

	@RequestMapping(value = "/TipREST/{id}", method = RequestMethod.GET)
	public CustomResponseEntity getTipById(@PathVariable int id) {
		return tipDAO.getTipByID(id);
	}

	@RequestMapping(value = "/TipRESTByTitle/{newsStr}", method = RequestMethod.GET)
	public CustomResponseEntity getTipByTitle(@PathVariable String newsStr) {
		return tipDAO.getTipByTitle(newsStr);
	}

	@RequestMapping(value = "/TipREST", method = RequestMethod.POST)
	public CustomResponseEntity addTip(@RequestBody TipNonMapping newTip) {
		try {
			Tip tipHibernate = new Tip();
			tipHibernate.setAdmin((Admin) adminDAO.getAdminByID(newTip.getAdminId()).getData());
			tipHibernate.setTiptype((TipType) tipTypeDAO.getTipTypeByID(newTip.getTiptypeId()).getData());
			tipHibernate.setTitle(newTip.getTitle());
			tipHibernate.setTitlepicture(newTip.getTitlepicture());
			tipHibernate.setContent(newTip.getContent());
			tipHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(newTip.getPostdate()));
			tipHibernate.setStatus(newTip.isStatus());
			return tipDAO.addTip(tipHibernate);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	@RequestMapping(value = "/TipREST/{id}", method = RequestMethod.DELETE)
	public CustomResponseEntity deleteTip(@PathVariable int id) {
		return tipDAO.deleteTip(id);

	}

	@RequestMapping(value = "/TipREST", method = RequestMethod.PUT)
	public CustomResponseEntity updateTip(@RequestBody TipNonMapping updateTip) {
		try {
			Tip tipHibernate = new Tip();
			tipHibernate.setId(updateTip.getId());
			tipHibernate.setAdmin((Admin) adminDAO.getAdminByID(updateTip.getAdminId()).getData());
			tipHibernate.setTiptype((TipType) tipTypeDAO.getTipTypeByID(updateTip.getTiptypeId()).getData());
			tipHibernate.setTitle(updateTip.getTitle());
			tipHibernate.setTitlepicture(updateTip.getTitlepicture());
			tipHibernate.setContent(updateTip.getContent());
			tipHibernate.setPostdate(new SimpleDateFormat("dd/mm/yyyy").parse(updateTip.getPostdate()));
			tipHibernate.setStatus(updateTip.isStatus());
			return tipDAO.updateTip(tipHibernate);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

}
