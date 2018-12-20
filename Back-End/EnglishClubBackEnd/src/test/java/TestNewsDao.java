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
import vn.edu.saigontech.SGTEnglishClub.DAOs.NewsDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.News;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestNewsDao {
	@Autowired
	private NewsDAO newsDAO;

	@Test
	public void testAddNews() {
		// get number of News before testing
		List<News> array = (List<News>) newsDAO.getAllNews().getData();
		int beforeArrNum = array.size();

		// create a new tip
		News news = null;
		int i = 1;
		while (news == null) {
			news = (News) newsDAO.getNewsByID(i).getData();
			i++;
		}

		// add a News to database and get their ID
		news.setId(null);
		int addedID = ((News) newsDAO.addNews(news).getData()).getId();

		array = (List<News>) newsDAO.getAllNews().getData();

		int newArrNum = array.size();

		newsDAO.deleteNews(addedID);

		assertTrue(newArrNum - 1 == beforeArrNum);
	}

	@Test
	public void testDeleteNews() {
		// get number of News before testing
		List<News> array = (List<News>) newsDAO.getAllNews().getData();
		int beforeArrNum = array.size();

		// find a current News then add a new One
		News news = null;
		int i = 1;
		while (news == null) {
			news = (News) newsDAO.getNewsByID(i).getData();
			i++;
		}
		news.setId(null);

		// and get ID of News
		int addedID = ((News) newsDAO.addNews(news).getData()).getId();

		// delete that news
		newsDAO.deleteNews(addedID);

		// get number of News after deleting
		array = (List<News>) newsDAO.getAllNews().getData();
		int currentNumTip = array.size();

		// if the current Num of News equal number of News before, so this daos is OK
		assertTrue(currentNumTip == beforeArrNum);
	}

	@Test
	public void testUpdateTip() {
		// create a tip
		News test = null;
		int i = 1;
		while (test == null) {
			test = (News) newsDAO.getNewsByID(i).getData();
			i++;
		}
		test.setId(null);

		// and get ID of new Tip
		int addedID = ((News) newsDAO.addNews(test).getData()).getId();

		// edit information of this tip in class and update this tip in database
		String tempBefore = test.getTitle();

		test.setTitle("test Tip");
		test.setId(addedID);
		String tempAfter = ((News) newsDAO.updateNews(test).getData()).getTitle();

		newsDAO.deleteNews(addedID);

		assertTrue(tempBefore != tempAfter);
	}

	@Test
	public void testGetAllNews() {
		boolean res = false;
		// get number of News before testing
		List<News> array = (List<News>) newsDAO.getAllNews().getData();
		int beforeArrNum = array.size();

		// add a News to the database using Hibernate
		News news = null;
		int i = 1;
		while (news == null) {
			news = (News) newsDAO.getNewsByID(i).getData();
			i++;
		}
		news.setId(null);

		// and get ID of News
		int addedID = ((News) newsDAO.addNews(news).getData()).getId();

		// get number of News after adding
		array = (List<News>) newsDAO.getAllNews().getData();
		int currentNum = array.size();

		// if number of tip before equal after minus 1, so this daos is OK.
		if (beforeArrNum == (currentNum - 1))
			res = true;

		newsDAO.deleteNews(addedID);

		assertTrue(res);
	}

	@Test
	public void testGetNewsByID() {

		// add a News to the database
		News beforeTest = null;
		int i = 1;
		while (beforeTest == null) {
			beforeTest = (News) newsDAO.getNewsByID(i).getData();
			i++;
		}
		beforeTest.setId(null);

		// and get ID of new News
		beforeTest = ((News) newsDAO.addNews(beforeTest).getData());
		
		int addedID = beforeTest.getId();

		// get this News again by it ID
		News afterTest = (News) newsDAO.getNewsByID(addedID).getData();
		
		boolean res = true;
		
		if (!beforeTest.getTitle().equals(afterTest.getTitle())) {
			res = false;
		}
		
		if (!beforeTest.getContent().equals(afterTest.getContent())) {
			res = false;
		}
		
		if (!beforeTest.getThumbnailpicturetitle().equals(afterTest.getThumbnailpicturetitle())) {
			res = false;
		}
		
		if (!beforeTest.getBigpicturetitle().equals(afterTest.getBigpicturetitle())) {
			res = false;
		}
		
		newsDAO.deleteNews(addedID);
		assertTrue(res);

	}

}
