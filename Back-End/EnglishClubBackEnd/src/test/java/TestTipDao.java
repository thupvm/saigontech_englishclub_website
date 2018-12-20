import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import vn.edu.saigontech.SGTEnglishClub.Configurations.ApplicationContextConfig;
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.Tip;
import vn.edu.saigontech.SGTEnglishClub.Models.TipType;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestTipDao {
	@Autowired
	private TipDAO tipDAO;

	@Test
	public void testAddTip() {
		// get number of Tip before testing
		List<Tip> array = (List<Tip>) tipDAO.getAllTipForAdmin().getData();
		int beforeArrNum = array.size();

		// create a new tip
		Tip newTip = null;
		int i = 1;
		while (newTip == null) {
			newTip = (Tip) tipDAO.getTipByID(i).getData();
			i++;
		}

		// add a new Tip to database and get their ID
		newTip.setId(null);
		int addedID = ((Tip) tipDAO.addTip(newTip).getData()).getId();

		array = (List<Tip>) tipDAO.getAllTipForAdmin().getData();

		int newArrNum = array.size();

		tipDAO.deleteTip(addedID);

		assertTrue(newArrNum - 1 == beforeArrNum);
	}

	@Test
	public void testDeleteTip() {
		// get number of Tip before testing
		List<Tip> array = (List<Tip>) tipDAO.getAllTipForAdmin().getData();
		int beforeArrNum = array.size();

		// find a current tip then add a new tip
		Tip newTip = null;
		int i = 1;
		while (newTip == null) {
			newTip = (Tip) tipDAO.getTipByID(i).getData();
			i++;
		}
		newTip.setId(null);

		// and get ID of new Tip
		int addedID = ((Tip) tipDAO.addTip(newTip).getData()).getId();

		// delete that tip
		tipDAO.deleteTip(addedID);

		// get number of tip after deleting
		array = (List<Tip>) tipDAO.getAllTipForAdmin().getData();
		int currentNumTip = array.size();

		// if the current Num of Tip equal number of Tip before, so this daos is OK
		assertTrue(currentNumTip == beforeArrNum);
	}

	@Test
	public void testUpdateTip() {
		// create a tip
		Tip testTip = null;
		int i = 1;
		while (testTip == null) {
			testTip = (Tip) tipDAO.getTipByID(i).getData();
			i++;
		}
		testTip.setId(null);

		// and get ID of new Tip
		int addedID = ((Tip) tipDAO.addTip(testTip).getData()).getId();

		// edit information of this tip in class and update this tip in database
		String tempBefore = testTip.getTitle();

		testTip.setTitle("test Tip");
		testTip.setId(addedID);
		String tempAfter = ((Tip) tipDAO.updateTip(testTip).getData()).getTitle();

		tipDAO.deleteTip(addedID);

		assertTrue(tempBefore != tempAfter);
	}

	@Test
	public void testGetAllTips() {
		boolean res = false;
		// get number of tip before testing
		List<Tip> array = (List<Tip>) tipDAO.getAllTipForAdmin().getData();
		int beforeArrNum = array.size();

		// add a new tip to the database using Hibernate
		Tip newTip = null;
		int i = 1;
		while (newTip == null) {
			newTip = (Tip) tipDAO.getTipByID(i).getData();
			i++;
		}
		newTip.setId(null);

		// and get ID of new Tip
		int addedID = ((Tip) tipDAO.addTip(newTip).getData()).getId();

		// get number of tip after adding
		array = (List<Tip>) tipDAO.getAllTipForAdmin().getData();
		int currentNumTip = array.size();

		// if number of tip before equal after minus 1, so this daos is OK.
		if (beforeArrNum == (currentNumTip - 1))
			res = true;

		tipDAO.deleteTip(addedID);

		assertTrue(res);
	}

	@Test
	public void testGetTipByID() {

		// add a tip to the database
		Tip testTip = null;
		int i = 1;
		while (testTip == null) {
			testTip = (Tip) tipDAO.getTipByID(i).getData();
			i++;
		}
		testTip.setId(null);

		// and get ID of new Tip
		testTip = ((Tip) tipDAO.addTip(testTip).getData());
		
		System.err.println(testTip.toString());
		
		int addedID = testTip.getId();

		// get this tip again by it ID
		Tip testTip1 = (Tip) tipDAO.getTipByID(addedID).getData();
		System.err.println(testTip1.toString());
		
		boolean res = true;
		
		if (!testTip.getTitle().equals(testTip1.getTitle())) {
			res = false;
		}
		
		if (!testTip.getContent().equals(testTip1.getContent())) {
			res = false;
		}
		
		if (!testTip.getTitlepicture().equals(testTip1.getTitlepicture())) {
			res = false;
		}
		
		tipDAO.deleteTip(addedID);
		assertTrue(res);

	}

}
