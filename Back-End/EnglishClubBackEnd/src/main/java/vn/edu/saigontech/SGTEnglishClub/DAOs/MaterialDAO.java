package vn.edu.saigontech.SGTEnglishClub.DAOs;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.Material;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Validators.MaterialValidator;



@Transactional
public class MaterialDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomResponseEntity getAllMaterial() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Material");

			response.setErrorCode(0);
			response.setMessage("This is List of all E-Materials");
			response.setData(qry.list());

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity getMaterialByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Material> targetEMa = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Material e where e.id = :id").setParameter("id", id);
			targetEMa = (List<Material>) qry.list();
			if (targetEMa.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is E-Material with id equal " + id);
				response.setData(targetEMa.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This E-Material is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(null);
		}

		return response;
	}
	
	public CustomResponseEntity getMaterialByTitle(String name) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Material> targetEMa = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Material e where e.title = :name").setParameter("name", name);
			targetEMa = (List<Material>) qry.list();
			if (targetEMa.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is E-Material with Title: " + name);
				response.setData(targetEMa.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This E-Material Title is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(null);
		}

		return response;
	}
	
	public CustomResponseEntity addMaterial(Material newEMa) {
		CustomResponseEntity response = new CustomResponseEntity();
		String validString = MaterialValidator.isValidForAdding(newEMa);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newEMa);
			response.setErrorCode(0);
			response.setMessage("Add the E-Material successfully");
			response.setData(newEMa);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	
	public CustomResponseEntity deleteMaterial(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		Material targetEMa = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			targetEMa = (Material) getMaterialByID(id).getData();

			if (targetEMa != null) {
				session.delete(targetEMa);
				response.setErrorCode(0);
				response.setMessage("Delete this E-Material successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This E-Material is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	
	public CustomResponseEntity updateMaterial(Material updateEMa) {
		CustomResponseEntity response = new CustomResponseEntity();
		
		String validString = MaterialValidator.isValidForUpdating(updateEMa);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateEMa);
			response.setErrorCode(0);
			response.setMessage("Update this E-Material successfully");
			response.setData(updateEMa);
		}
		 catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	

	

}
