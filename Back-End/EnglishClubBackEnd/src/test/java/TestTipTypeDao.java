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
import vn.edu.saigontech.SGTEnglishClub.DAOs.TipTypeDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.TipType;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestTipTypeDao {
	@Autowired
	private TipTypeDAO tipTypeDAO;

	@Test
	public void testAddTipType() {
		// get number of News before testing
		List<TipType> array = (List<TipType>) tipTypeDAO.getAllTipsTypes().getData();
		int beforeArrNum = array.size();

		// create a new tip
		TipType news = null;
		int i = 1;
		while (news == null) {
			news = (TipType) tipTypeDAO.getTipTypeByID(i).getData();
			i++;
		}

		// add a News to database and get their ID
		news.setId(null);
		int addedID = ((TipType) tipTypeDAO.addTipType(news).getData()).getId();

		array = (List<TipType>) tipTypeDAO.getAllTipsTypes().getData();

		int newArrNum = array.size();

		tipTypeDAO.deleteTipType(addedID);

		assertTrue(newArrNum - 1 == beforeArrNum);
	}

	@Test
	public void testDeleteTipType() {
		// get number of News before testing
		List<TipType> array = (List<TipType>) tipTypeDAO.getAllTipsTypes().getData();
		int beforeArrNum = array.size();

		// find a current News then add a new One
		TipType news = null;
		int i = 1;
		while (news == null) {
			news = (TipType) tipTypeDAO.getTipTypeByID(i).getData();
			i++;
		}
		news.setId(null);

		// and get ID of News
		int addedID = ((TipType) tipTypeDAO.addTipType(news).getData()).getId();

		// delete that news
		tipTypeDAO.deleteTipType(addedID);

		// get number of News after deleting
		array = (List<TipType>) tipTypeDAO.getAllTipsTypes().getData();
		int currentNumTip = array.size();

		// if the current Num of News equal number of News before, so this daos is OK
		assertTrue(currentNumTip == beforeArrNum);
	}

	@Test
	public void testUpdateTipType() {
		// create a tip
		TipType test = null;
		int i = 1;
		while (test == null) {
			test = (TipType) tipTypeDAO.getTipTypeByID(i).getData();
			i++;
		}
		test.setId(null);

		// and get ID of new Tip
		int addedID = ((TipType) tipTypeDAO.addTipType(test).getData()).getId();

		// edit information of this tip in class and update this tip in database
		String tempBefore = test.getName();

		test.setName("test Tip");
		test.setId(addedID);
		String tempAfter = ((TipType) tipTypeDAO.updateTipType(test).getData()).getName();

		tipTypeDAO.deleteTipType(addedID);

		assertTrue(tempBefore != tempAfter);
	}

	@Test
	public void testGetAllNews() {
		boolean res = false;
		// get number of News before testing
		List<TipType> array = (List<TipType>) tipTypeDAO.getAllTipsTypes().getData();
		int beforeArrNum = array.size();

		// add a News to the database using Hibernate
		TipType news = null;
		int i = 1;
		while (news == null) {
			news = (TipType) tipTypeDAO.getTipTypeByID(i).getData();
			i++;
		}
		news.setId(null);

		// and get ID of News
		int addedID = ((TipType) tipTypeDAO.addTipType(news).getData()).getId();

		// get number of News after adding
		array = (List<TipType>) tipTypeDAO.getAllTipsTypes().getData();
		int currentNum = array.size();

		// if number of tip before equal after minus 1, so this daos is OK.
		if (beforeArrNum == (currentNum - 1))
			res = true;

		tipTypeDAO.deleteTipType(addedID);

		assertTrue(res);
	}

}
