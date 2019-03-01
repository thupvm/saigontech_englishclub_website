package vn.edu.saigontech.SGTEnglishClub.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.saigontech.SGTEnglishClub.DAOs.TipTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.TipType;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@RestController
public class TipTypeRESTController {
	@Autowired
	private TipTypeDAO tipTypeDao;
	
	@RequestMapping(value="/tipType", method=RequestMethod.GET)
	public CustomResponseEntity getAllTipTypes() {
		return tipTypeDao.getAllTipsTypes();
	}
	
	@RequestMapping(value="/active/tipType", method=RequestMethod.GET)
	public CustomResponseEntity getAllActiveTipTypes() {
		return tipTypeDao.getAllTipsTypes();
	}
	
	@RequestMapping(value="/tipType/{id}", method=RequestMethod.GET)
	public CustomResponseEntity getTipTypeById(@PathVariable int id) {
		return tipTypeDao.getTipTypeByID(id);
	}          
	

	@RequestMapping(value="/manage/tipType", method=RequestMethod.POST)
	public CustomResponseEntity addTipType(@RequestBody TipType newTType) {
		return tipTypeDao.addTipType(newTType);
	}
	
	@RequestMapping(value="/manage/tipType/{id}", method=RequestMethod.DELETE)
	public CustomResponseEntity deleteTiptype(@PathVariable int id) {
		return tipTypeDao.deleteTipType(id);
	}
	
	@RequestMapping(value="/manage/tipType", method=RequestMethod.PUT)
	public CustomResponseEntity updateTipType(@RequestBody TipType updateTType) {
		return tipTypeDao.updateTipType(updateTType);
	}
}
