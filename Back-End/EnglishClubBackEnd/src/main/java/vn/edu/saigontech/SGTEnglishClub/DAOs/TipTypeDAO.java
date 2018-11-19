package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.TipType;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Validators.TipTypeValidator;



@Transactional
public class TipTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomResponseEntity getAllTipsTypes() {
		CustomResponseEntity respond = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from TipType");
			
			respond.setErrorCode(0);
			respond.setMessage("This is all Tip Types");
			respond.setData(qry.list());
			
		}
		catch(Exception e) {
			e.printStackTrace();
			respond.setErrorCode(1);
			respond.setMessage("Error of database");
			respond.setData(e.getMessage());
		}
		return respond;
		
	}
	
	public CustomResponseEntity getTipTypeByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<TipType> targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from TipType t where t.id = :id").setParameter("id", id);
			targetSpec = (List<TipType>) qry.list();
			if (targetSpec.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is Tip Type with id equal " + id);
				response.setData(targetSpec.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This Tip Type is not exist");
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
	
//	public customResponseEntity getTipTypeByStatus(String status) {
//		customResponseEntity response = new customResponseEntity();
//		List<TipType> targetSpec = null;
//		try {
//			Session session = sessionFactory.getCurrentSession();
//			Query<?> qry = session.createQuery("from TipType t where t.status = :status").setParameter("status", status);
//			targetSpec = (List<TipType>) qry.list();
//			
//			if (targetSpec.size() > 0) {
//				response.setErrorCode(0);
//				response.setMessage("This is all Tip Type with " + status + " status");
//				response.setData(targetSpec.get(0));
//			} else {
//				
//			}
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setErrorCode(1);
//			response.setMessage("Error of database");
//			response.setData(null);
//		}
//
//		return response;
//	}

	public CustomResponseEntity addTipType(TipType newTType) {
		CustomResponseEntity response = new CustomResponseEntity();
		String validString = TipTypeValidator.isValidForAdding(newTType);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newTType);
			response.setErrorCode(0);
			response.setMessage("Add this Tip Type successfully");
			response.setData(newTType);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity deleteTipType(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		TipType targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			targetSpec = (TipType) getTipTypeByID(id).getData();

			if (targetSpec != null) {
				session.delete(targetSpec);
				response.setErrorCode(0);
				response.setMessage("Delete this Tip Types successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This Tip Types is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity updateTipType(TipType updateTType) {
		CustomResponseEntity response = new CustomResponseEntity();
		
		String validString = TipTypeValidator.isValidForUpdating(updateTType);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateTType);
			response.setErrorCode(0);
			response.setMessage("Update this Tip Types successfully");
			response.setData(updateTType);
		}
		 catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	

}
