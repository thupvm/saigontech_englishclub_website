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
import vn.edu.saigontech.SGTEnglishClub.DAOs.MaterialDAO;
import vn.edu.saigontech.SGTEnglishClub.Models.Material;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestEMaterialDao {
	@Autowired
	private MaterialDAO materialDAO;

	@Test
	public void testAddMaterial() {
		// get number of Material before testing
		List<Material> array = (List<Material>) materialDAO.getAllMaterialAdmin().getData();
		int beforeArrNum = array.size();

		// create a new tip
		Material material = null;
		int i = 1;
		while (material == null) {
			material = (Material) materialDAO.getMaterialByID(i).getData();
			i++;
		}

		// add a Material to database and get their ID
		material.setId(null);
		int addedID = ((Material) materialDAO.addMaterial(material).getData()).getId();

		array = (List<Material>) materialDAO.getAllMaterialAdmin().getData();

		int newArrNum = array.size();

		materialDAO.deleteMaterial(addedID);

		assertTrue(newArrNum - 1 == beforeArrNum);
	}

	@Test
	public void testDeleteMaterial() {
		// get number of Material before testing
		List<Material> array = (List<Material>) materialDAO.getAllMaterialAdmin().getData();
		int beforeArrNum = array.size();

		// find a current Material then add a new One
		Material material = null;
		int i = 1;
		while (material == null) {
			material = (Material) materialDAO.getMaterialByID(i).getData();
			i++;
		}
		material.setId(null);

		// and get ID of Material
		int addedID = ((Material) materialDAO.addMaterial(material).getData()).getId();

		// delete that material
		materialDAO.deleteMaterial(addedID);

		// get number of Material after deleting
		array = (List<Material>) materialDAO.getAllMaterialAdmin().getData();
		int currentNumTip = array.size();

		// if the current Num of Material equal number of Material before, so this daos is OK
		assertTrue(currentNumTip == beforeArrNum);
	}

	@Test
	public void testUpdateTip() {
		// create a tip
		Material test = null;
		int i = 1;
		while (test == null) {
			test = (Material) materialDAO.getMaterialByID(i).getData();
			i++;
		}
		test.setId(null);

		// and get ID of new Tip
		int addedID = ((Material) materialDAO.addMaterial(test).getData()).getId();

		// edit information of this tip in class and update this tip in database
		String tempBefore = test.getTitle();

		test.setTitle("test Tip");
		test.setId(addedID);
		String tempAfter = ((Material) materialDAO.updateMaterial(test).getData()).getTitle();

		materialDAO.deleteMaterial(addedID);

		assertTrue(tempBefore != tempAfter);
	}

	@Test
	public void testGetAllMaterial() {
		boolean res = false;
		// get number of Material before testing
		List<Material> array = (List<Material>) materialDAO.getAllMaterialAdmin().getData();
		int beforeArrNum = array.size();

		// add a Material to the database using Hibernate
		Material material = null;
		int i = 1;
		while (material == null) {
			material = (Material) materialDAO.getMaterialByID(i).getData();
			i++;
		}
		material.setId(null);

		// and get ID of Material
		int addedID = ((Material) materialDAO.addMaterial(material).getData()).getId();

		// get number of Material after adding
		array = (List<Material>) materialDAO.getAllMaterialAdmin().getData();
		int currentNum = array.size();

		// if number of tip before equal after minus 1, so this daos is OK.
		if (beforeArrNum == (currentNum - 1))
			res = true;

		materialDAO.deleteMaterial(addedID);

		assertTrue(res);
	}

	@Test
	public void testGetMaterialByID() {

		// add a Material to the database
		Material beforeTest = null;
		int i = 1;
		while (beforeTest == null) {
			beforeTest = (Material) materialDAO.getMaterialByID(i).getData();
			i++;
		}
		beforeTest.setId(null);

		// and get ID of new Material
		beforeTest = ((Material) materialDAO.addMaterial(beforeTest).getData());
		
		int addedID = beforeTest.getId();

		// get this Material again by it ID
		Material afterTest = (Material) materialDAO.getMaterialByID(addedID).getData();
		
		boolean res = true;
		
		if (!beforeTest.getTitle().equals(afterTest.getTitle())) {
			res = false;
		}
		
		if (!beforeTest.getContent().equals(afterTest.getContent())) {
			res = false;
		}
		
		if (!beforeTest.getTitle().equals(afterTest.getTitle())) {
			res = false;
		}
		
		if (!beforeTest.getTitlepicture().equals(afterTest.getTitlepicture())) {
			res = false;
		}
		
		materialDAO.deleteMaterial(addedID);
		assertTrue(res);

	}

}
