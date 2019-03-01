package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Validators.MaterialTypeValidator;

@Transactional
public class MaterialTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CustomResponseEntity getAllMaterialtypeAdmin() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Materialtype");

			response.setErrorCode(0);
			response.setMessage("This is all E-Material Types");
			response.setData(qry.list());

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity getAllMaterialtype() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Materialtype m where m.status = true");

			response.setErrorCode(0);
			response.setMessage("This is all E-Material Types");
			response.setData(qry.list());

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity getMaterialtypeByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Materialtype> targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Materialtype s where s.id = :id").setParameter("id", id);
			targetSpec = (List<Materialtype>) qry.list();
			if (targetSpec.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is E-Material Type with id equal " + id);
				response.setData(targetSpec.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This E-Material Type is not exist");
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

	public CustomResponseEntity getMaterialTypeByTitleName(String name) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Materialtype> targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Materialtype s where s.name = :name").setParameter("name", name);
			targetSpec = (List<Materialtype>) qry.list();
			if (targetSpec.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is E-Material Type with Title: " + name);
				response.setData(targetSpec.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This E-Materials Type Title is not exist");
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

	public CustomResponseEntity addMaterialType(Materialtype newEMType) {
		CustomResponseEntity response = new CustomResponseEntity();
		String validString = MaterialTypeValidator.isValidForAdding(newEMType);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}

		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newEMType);
			response.setErrorCode(0);
			response.setMessage("Add this E-MaterialType successfully");
			response.setData(newEMType);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity deleteMaterialType(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		Materialtype targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			targetSpec = (Materialtype) getMaterialtypeByID(id).getData();

			if (targetSpec != null) {
				session.delete(targetSpec);
				response.setErrorCode(0);
				response.setMessage("Delete this E-Material Type successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This E-Material Type is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity updateMaterialType(Materialtype updateEMType) {
		CustomResponseEntity response = new CustomResponseEntity();

		String validString = MaterialTypeValidator.isValidForUpdating(updateEMType);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}

		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateEMType);
			response.setErrorCode(0);
			response.setMessage("Update this E-Material Type successfully");
			response.setData(updateEMType);
		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

}
