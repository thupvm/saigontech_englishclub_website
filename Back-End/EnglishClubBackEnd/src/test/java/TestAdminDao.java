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
import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestAdminDao {
	@Autowired
	private AdminDAO adminDAO;

	@Test
	public void testAddAdmin() {
		// get number of Admin before testing
		List<Admin> array = (List<Admin>) adminDAO.getAllAdmin().getData();
		int beforeArrNum = array.size();

		// create a new tip
		Admin admin = null;
		int i = 1;
		while (admin == null) {
			admin = (Admin) adminDAO.getAdminByID(i).getData();
			i++;
		}

		// add a Admin to database and get their ID
		admin.setId(null);
		int addedID = ((Admin) adminDAO.addAdmin(admin).getData()).getId();

		array = (List<Admin>) adminDAO.getAllAdmin().getData();

		int newArrNum = array.size();

		adminDAO.deleteAdmin(addedID);

		assertTrue(newArrNum - 1 == beforeArrNum);
	}

	@Test
	public void testDeleteAdmin() {
		// get number of Admin before testing
		List<Admin> array = (List<Admin>) adminDAO.getAllAdmin().getData();
		int beforeArrNum = array.size();

		// find a current Admin then add a new One
		Admin adminTest = null;
		int i = 1;
		while (adminTest == null) {
			adminTest = (Admin) adminDAO.getAdminByID(i).getData();
			i++;
		}
		adminTest.setId(null);

		// and get ID of Admin
		int addedID = ((Admin) adminDAO.addAdmin(adminTest).getData()).getId();

		// delete that admin
		adminDAO.deleteAdmin(addedID);

		// get number of Admin after deleting
		array = (List<Admin>) adminDAO.getAllAdmin().getData();
		int currentNumTip = array.size();

		// if the current Num of Admin equal number of Admin before, so this daos is OK
		assertTrue(currentNumTip == beforeArrNum);
	}

	@Test
	public void testUpdateAdmin() {
		// create a tip
		Admin test = null;
		int i = 1;
		while (test == null) {
			test = (Admin) adminDAO.getAdminByID(i).getData();
			i++;
		}
		test.setId(null);

		// and get ID of new Tip
		int addedID = ((Admin) adminDAO.addAdmin(test).getData()).getId();

		// edit information of this tip in class and update this tip in database
		String tempBefore = test.getUsername();

		test.setUsername("test Tip");
		test.setId(addedID);
		String tempAfter = ((Admin) adminDAO.updateAdmin(test).getData()).getUsername();

		adminDAO.deleteAdmin(addedID);

		assertTrue(tempBefore != tempAfter);
	}

	@Test
	public void testGetAllAdmin() {
		boolean res = false;
		// get number of Admin before testing
		List<Admin> array = (List<Admin>) adminDAO.getAllAdmin().getData();
		int beforeArrNum = array.size();

		// add a Admin to the database using Hibernate
		Admin admin = null;
		int i = 1;
		while (admin == null) {
			admin = (Admin) adminDAO.getAdminByID(i).getData();
			i++;
		}
		admin.setId(null);

		// and get ID of Admin
		int addedID = ((Admin) adminDAO.addAdmin(admin).getData()).getId();

		// get number of Admin after adding
		array = (List<Admin>) adminDAO.getAllAdmin().getData();
		int currentNum = array.size();

		// if number of tip before equal after minus 1, so this daos is OK.
		if (beforeArrNum == (currentNum - 1))
			res = true;

		adminDAO.deleteAdmin(addedID);

		assertTrue(res);
	}

	@Test
	public void testGetAdminByID() {

		// add a Admin to the database
		Admin beforeTest = null;
		int i = 1;
		while (beforeTest == null) {
			beforeTest = (Admin) adminDAO.getAdminByID(i).getData();
			i++;
		}
		beforeTest.setId(null);

		// and get ID of new Admin
		beforeTest = ((Admin) adminDAO.addAdmin(beforeTest).getData());
		
		int addedID = beforeTest.getId();

		// get this Admin again by it ID
		Admin afterTest = (Admin) adminDAO.getAdminByID(addedID).getData();
		
		boolean res = true;
		
		if (!beforeTest.getUsername().equals(afterTest.getUsername())) {
			res = false;
		}
		
		if (!beforeTest.getPassword().equals(afterTest.getPassword())) {
			res = false;
		}
		
		if (!beforeTest.getLastname().equals(afterTest.getLastname())) {
			res = false;
		}
		
		if (!beforeTest.getFirstname().equals(afterTest.getFirstname())) {
			res = false;
		}
		
		if (!beforeTest.getRole().equals(afterTest.getRole())) {
			res = false;
		}
		
		if (!beforeTest.getPhone().equals(afterTest.getPhone())) {
			res = false;
		}
		
		adminDAO.deleteAdmin(addedID);
		assertTrue(res);

	}

}
