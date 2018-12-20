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
import vn.edu.saigontech.SGTEnglishClub.DAOs.VideoDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestVideoDao {
	@Autowired
	private VideoDAO videoDAO;

	@Test
	public void testAddVideo() {
		// get number of Video before testing
		List<Video> array = (List<Video>) videoDAO.getAllVideoForAdmin().getData();
		int beforeArrNum = array.size();

		// create a new tip
		Video video = null;
		int i = 1;
		while (video == null) {
			video = (Video) videoDAO.getVideoByID(i).getData();
			i++;
		}

		// add a Video to database and get their ID
		video.setId(null);
		int addedID = ((Video) videoDAO.addVideo(video).getData()).getId();

		array = (List<Video>) videoDAO.getAllVideoForAdmin().getData();

		int newArrNum = array.size();

		videoDAO.deleteVideo(addedID);

		assertTrue(newArrNum - 1 == beforeArrNum);
	}

	@Test
	public void testDeleteVideo() {
		// get number of Video before testing
		List<Video> array = (List<Video>) videoDAO.getAllVideoForAdmin().getData();
		int beforeArrNum = array.size();

		// find a current Video then add a new One
		Video video = null;
		int i = 1;
		while (video == null) {
			video = (Video) videoDAO.getVideoByID(i).getData();
			i++;
		}
		video.setId(null);

		// and get ID of Video
		int addedID = ((Video) videoDAO.addVideo(video).getData()).getId();

		// delete that video
		videoDAO.deleteVideo(addedID);

		// get number of Video after deleting
		array = (List<Video>) videoDAO.getAllVideoForAdmin().getData();
		int currentNumTip = array.size();

		// if the current Num of Video equal number of Video before, so this daos is OK
		assertTrue(currentNumTip == beforeArrNum);
	}

	@Test
	public void testUpdateTip() {
		// create a tip
		Video test = null;
		int i = 1;
		while (test == null) {
			test = (Video) videoDAO.getVideoByID(i).getData();
			i++;
		}
		test.setId(null);

		// and get ID of new Tip
		int addedID = ((Video) videoDAO.addVideo(test).getData()).getId();

		// edit information of this tip in class and update this tip in database
		String tempBefore = test.getTitle();

		test.setTitle("test Tip");
		test.setId(addedID);
		String tempAfter = ((Video) videoDAO.updateVideo(test).getData()).getTitle();

		videoDAO.deleteVideo(addedID);

		assertTrue(tempBefore != tempAfter);
	}

	@Test
	public void testGetAllVideo() {
		boolean res = false;
		// get number of Video before testing
		List<Video> array = (List<Video>) videoDAO.getAllVideoForAdmin().getData();
		int beforeArrNum = array.size();

		// add a Video to the database using Hibernate
		Video video = null;
		int i = 1;
		while (video == null) {
			video = (Video) videoDAO.getVideoByID(i).getData();
			i++;
		}
		video.setId(null);

		// and get ID of Video
		int addedID = ((Video) videoDAO.addVideo(video).getData()).getId();

		// get number of Video after adding
		array = (List<Video>) videoDAO.getAllVideoForAdmin().getData();
		int currentNum = array.size();

		// if number of tip before equal after minus 1, so this daos is OK.
		if (beforeArrNum == (currentNum - 1))
			res = true;

		videoDAO.deleteVideo(addedID);

		assertTrue(res);
	}

	@Test
	public void testGetVideoByID() {

		// add a Video to the database
		Video beforeTest = null;
		int i = 1;
		while (beforeTest == null) {
			beforeTest = (Video) videoDAO.getVideoByID(i).getData();
			i++;
		}
		beforeTest.setId(null);

		// and get ID of new Video
		beforeTest = ((Video) videoDAO.addVideo(beforeTest).getData());
		
		int addedID = beforeTest.getId();

		// get this Video again by it ID
		Video afterTest = (Video) videoDAO.getVideoByID(addedID).getData();
		
		boolean res = true;
		
		if (!beforeTest.getTitle().equals(afterTest.getTitle())) {
			res = false;
		}
		
		if (!beforeTest.getDescription().equals(afterTest.getDescription())) {
			res = false;
		}
		
		if (!beforeTest.getLink().equals(afterTest.getLink())) {
			res = false;
		}
		
		videoDAO.deleteVideo(addedID);
		assertTrue(res);

	}

}
