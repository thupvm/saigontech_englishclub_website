package vn.edu.saigontech.SGTEnglishClub.DAOs;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.File;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Utils.fileUploadUtils;


@Transactional
public class FileDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomResponseEntity getAllFile() {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from File");

			response.setErrorCode(0);
			response.setMessage("This is all File");
			response.setData((List<File>)qry.list());
		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity getFileByID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<File> target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from File f where f.id = :id").setParameter("id", id);
			target = (List<File>) qry.list();
			if (target.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is File with id equal " + id);
				response.setData(target.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This File is not exist");
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
	
	public CustomResponseEntity getFileByMaterialID(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<File> target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from File f where f.material.id = :id and f.status = true").setParameter("id", id);
			target = (List<File>) qry.list();
			if (target.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is File in material id equal " + id);
				response.setData(target);
			} else {
				response.setErrorCode(2);
				response.setMessage("This File is not exist");
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
	
	public CustomResponseEntity getFileByMaterialIDAdmin(int id) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<File> target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from File f where f.material.id = :id").setParameter("id", id);
			target = (List<File>) qry.list();
			if (target.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is File in material id equal " + id);
				response.setData(target);
			} else {
				response.setErrorCode(2);
				response.setMessage("This File is not exist");
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
	
	public CustomResponseEntity getFileByNameFile(String name) {
		CustomResponseEntity response = new CustomResponseEntity();
		List<File> target = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from File f where f.nameFile = :name").setParameter("name", name);
			target = (List<File>) qry.list();
			if (target.size() > 0) {
				response.setErrorCode(0);
				response.setMessage("This is File with Name " + name);
				response.setData(target.get(0));
			} else {
				response.setErrorCode(2);
				response.setMessage("This File Name is not exist");
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
	
	public CustomResponseEntity addFile(File newFile) {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newFile);
			response.setErrorCode(0);
			response.setMessage("Add this File successfully");
			response.setData(newFile);

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}
	
	public CustomResponseEntity deleteFile(int id, HttpServletRequest req) {
		CustomResponseEntity response = new CustomResponseEntity();
		File target = null;
		
		
		
		try {
			Session session = sessionFactory.getCurrentSession();
			target = (File) getFileByID(id).getData();
			
			if (target != null) {
				session.delete(target);
				response.setErrorCode(0);
				response.setMessage("Delete this File successfully");
				response.setData(null);
			} else {
				response.setErrorCode(2);
				response.setMessage("This File is not exist");
				response.setData(null);
			}
			
			boolean res = fileUploadUtils.deleteUploadFile(target.getName(), req.getServletContext().getRealPath("/images/"));
			if (res == false) {
				return CustomResponseEntity.getErrorRemovingResponse();
			}
			

			

		} catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}

	public CustomResponseEntity updateFile(File updateFile) {
		CustomResponseEntity response = new CustomResponseEntity();
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(updateFile);
			response.setErrorCode(0);
			response.setMessage("Update this File successfully");
			response.setData(updateFile);
		}
		 catch (Exception e) {
			response.setErrorCode(1);
			response.setMessage("Error of database");
			response.setData(e.getMessage());
		}

		return response;
	}


}
