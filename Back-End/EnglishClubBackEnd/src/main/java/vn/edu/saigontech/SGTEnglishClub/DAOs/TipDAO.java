package vn.edu.saigontech.SGTEnglishClub.DAOs;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.Tip;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Validators.TipValidator;



@Transactional
public class TipDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public CustomResponseEntity getAllTip() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Tip");

			response.setErrorCode(0);
			response.setMessage("This is List of all Tip");
			response.setData(qry.list());

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity getTipByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Tip> target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Tip t where t.id = :id").setParameter("id", id);
			target = (List<Tip>) qry.list();
			if (target.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is Tip with id equal " + id);
				response.setData(target.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This Tip is not exist");
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
	
	public CustomResponseEntity getTipByTitle(String name) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Tip> target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Tip t where t.title = :name").setParameter("name", name);
			target = (List<Tip>) qry.list();
			if (target.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is Tip with Title: " + name);
				response.setData(target.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This Tip Title is not exist");
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
	
	
	public CustomResponseEntity addTip(Tip newTip) {
		CustomResponseEntity response = new CustomResponseEntity();
		String validString = TipValidator.isValidForAdding(newTip);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newTip);
			response.setErrorCode(0);
			response.setMessage("Add the Tip successfully");
			response.setData(newTip);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity deleteTip(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		Tip target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			target = (Tip) getTipByID(id).getData();

			if (target != null) {
				session.delete(target);
				response.setErrorCode(0);
				response.setMessage("Delete this Tip successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This Tip is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity updateTip(Tip updateTip) {
		CustomResponseEntity response = new CustomResponseEntity();
		
		String validString = TipValidator.isValidForUpdating(updateTip);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateTip);
			response.setErrorCode(0);
			response.setMessage("Update the Tip successfully");
			response.setData(updateTip);
		}
		 catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());

		}

		return response;
	}

}
