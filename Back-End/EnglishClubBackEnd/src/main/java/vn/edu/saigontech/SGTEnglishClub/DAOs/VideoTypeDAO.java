package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.saigontech.SGTEnglishClub.Models.Videotype;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@Transactional
public class VideoTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomResponseEntity getAllActiveVideoType() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Videotype v where v.status = true");
			List<Videotype> videoTypeArr = (List<Videotype>) qry.list();
			System.out.println(videoTypeArr.size());
			return CustomResponseEntity.getOKResponse("This is the list of video type", videoTypeArr);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}
	
	public CustomResponseEntity getAllVideoType() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Videotype");
			List<Videotype> videoTypeArr = (List<Videotype>) qry.list();
			System.out.println(videoTypeArr.size());
			return CustomResponseEntity.getOKResponse("This is the list of video type", videoTypeArr);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity getVideoTypeByID(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Videotype v where v.id = :id").setParameter("id", id);
			List<Videotype> videoTypeArr = (List<Videotype>) qry.list();
			return CustomResponseEntity.getOKResponse("This is the video with id equal " + id, videoTypeArr.get(0));
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity addVideoType(Videotype newVideoType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newVideoType);
			return CustomResponseEntity.getOKResponse("Adding this video successfully", newVideoType);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity deleteVideoType(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Videotype targetVideoType = (Videotype) getVideoTypeByID(id).getData();
			if (targetVideoType != null) {
				session.delete(targetVideoType);
				return CustomResponseEntity.getOKResponse("Delete this video type successfully", targetVideoType);
			} else
				return CustomResponseEntity.getDatabaseErrorResponse();
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}
	
	public CustomResponseEntity updateVideoType(Videotype newVideoType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(newVideoType);
			return CustomResponseEntity.getOKResponse("Update this video type successfully", newVideoType);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

}
