package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.Newstype;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Validators.NewsTypeValidator;



@Transactional
public class NewsTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CustomResponseEntity getAllNewsType() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Newstype");

			response.setErrorCode(0);
			response.setMessage("This is all News Types");
			response.setData((List<Newstype>)qry.list());
			
			

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity getNewsTypeByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Newstype> targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Newstype n where n.id = :id").setParameter("id", id);
			targetSpec = (List<Newstype>) qry.list();
			if (targetSpec.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is News Type with id equal " + id);
				response.setData(targetSpec.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This News Type is not exist");
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
	
	public CustomResponseEntity getNewsTypeByTitleName(String name) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<Newstype> targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Newstype n where n.name = :name").setParameter("name", name);
			targetSpec = (List<Newstype>) qry.list();
			if (targetSpec.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is News Type with Title: " + name);
				response.setData(targetSpec.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This News Type Title is not exist");
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

	public CustomResponseEntity addNewsType(Newstype newNType) {
		CustomResponseEntity response = new CustomResponseEntity();
		String validString = NewsTypeValidator.isValidForAdding(newNType);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newNType);
			response.setErrorCode(0);
			response.setMessage("Add this News Type successfully");
			response.setData(newNType);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity deleteNewsType(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		Newstype targetSpec = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			targetSpec = (Newstype) getNewsTypeByID(id).getData();

			if (targetSpec != null) {
				session.delete(targetSpec);
				response.setErrorCode(0);
				response.setMessage("Delete this News Type successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This News Type is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity updateNewsType(Newstype updateNType) {
		CustomResponseEntity response = new CustomResponseEntity();
		
		String validString = NewsTypeValidator.isValidForUpdating(updateNType);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateNType);
			response.setErrorCode(0);
			response.setMessage("Update this News Type successfully");
			response.setData(updateNType);
		}
		 catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
//	public CustomResponseEntity getNewstypetatistic(String someString) {
//		CustomResponseEntity response = new CustomResponseEntity();
//		List<NewsTypeReport> target = null;
//		try {
//			Session session = sessionFactory.getCurrentSession();
//			String qry = "SELECT nt.titleName, count(n.id) as numOfNews " + 
//													"FROM Newstype nt, News n " + 
//													"WHERE nt.titleName LIKE '%'||:someString||'%' AND nt.titleName = n.news_types.titleName " +
//													"GROUP BY nt.titleName ";
//			Query countNews = session.createQuery(qry).setParameter("someString", someString);
//			target = (List<NewsTypeReport>) countNews.list();
//			if (target.size() > 0) {
//				response.setErrorCode(0);
//				response.setMessage("Error 1");
//				response.setData(target);
//			} else {
//				response.setErrorCode(2);
//				response.setMessage("Error 2");
//				response.setData(null);
//			}
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

}
