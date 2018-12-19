import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import vn.edu.saigontech.SGTEnglishClub.Configurations.ApplicationContextConfig;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Tip;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestTipDAO {
	@Autowired
	private TipDAO tipDao;

	@Test
	public void testAddTip() {
//		boolean res = false;
//		List<Tip> arr = tipDao.getAllTipForAdmin();
//		
//		int addedTipId = tipDao
//				.addTip(newTip);
//		
//
//		Tip addedTip = tipDao.getTipByID(addedTipId);
//		
//		//if the student above not null, so the daos is OK
//		if (addedTip != null)
//			res = true;
//
//		tipDao.deleteTip(addedTipId));
//		assertTrue(res);
	}

	@Test
	public void testDeleteTip() {
//		int beforeNumTip = tipDao.getAllTipForAdmin().size();
//
//		int addedTipId = tipDao
//				.addTip(newTip).getId();
//		
//	
//		tipDao.deleteTip(addedTipId);
//		
//		
//		int currentNumTip = tipDao.getAllTipForAdmin().size();
//		
//		assertTrue(currentNumTip== beforeNumTip);

	}

	@Test
	public void testUpdateTip() {
		

	}

	@Test
	public void testGetAllTip() {
		

	}

	@Test
	public void testGetTipByID() {
		
	}

}