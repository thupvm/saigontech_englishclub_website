package vn.edu.saigontech.SGTEnglishClub.DAOs;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.News;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Validators.NewsValidator;

@Transactional
public class NewsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CustomResponseEntity getAllNews() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from News");

			response.setErrorCode(0);
			response.setMessage("This is list of all News");
			response.setData(qry.list());

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity getNewsByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<News> targetNews = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry  = session.createQuery("from News n where n.id = :id").setParameter("id", id);
			targetNews = (List<News>) qry.list();
			if (targetNews.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is News with id equal " + id);
				response.setData(targetNews.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This News is not exist");
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
	
	public CustomResponseEntity getNewsByTitle(String name) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<News> targetNews = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from News n where n.title = :name").setParameter("name", name);
			targetNews = (List<News>) qry.list();
			if (targetNews.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is News with Title: " + name);
				response.setData(targetNews.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This News Title is not exist");
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
	
	public CustomResponseEntity addNews(News NewsAdd) {
		CustomResponseEntity response = new CustomResponseEntity();
		String validString = NewsValidator.isValidForAdding(NewsAdd);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(NewsAdd);
			response.setErrorCode(0);
			response.setMessage("Add the News successfully");
			response.setData(NewsAdd);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity deleteNews(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		News targetNews = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			targetNews = (News) getNewsByID(id).getData();

			if (targetNews != null) {
				session.delete(targetNews);
				response.setErrorCode(0);
				response.setMessage("Delete this News successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This News is not exist");
				response.setData(null);
			}

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity updateNews(News updateNews) {
		CustomResponseEntity response = new CustomResponseEntity();
		
		String validString = NewsValidator.isValidForUpdating(updateNews);
		System.out.println(validString);
		if (validString != "") {
			response.setErrorCode(3);
			response.setMessage(validString);
			response.setData(null);
			return response;
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateNews);
			response.setErrorCode(0);
			response.setMessage("Update this News successfully");
			response.setData(updateNews);
		}
		 catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity searchNews(String someString) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<News> targetNews = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			String qry = "FROM News n WHERE " +
						"n.title LIKE '%'||:someString||'%' OR " +
						"n.content LIKE '%'||:someString||'%'";
			Query searchNews = session.createQuery(qry).setParameter("someString", someString);
			targetNews = (List<News>) searchNews.list();
			if (targetNews.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is News with Title/Content contains '" + someString + "'");
				response.setData(targetNews);
			} else {
				response.setErrorCode(2);
				response.setMessage("Can't find News contains '" + someString + "'");
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
	
}
