package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.saigontech.SGTEnglishClub.Models.Video;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

@Transactional
public class VideoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CustomResponseEntity getAllVideoForAdmin() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Video v order by v.id desc");
			List<Video> videoArr = (List<Video>) qry.list();
			System.out.println(videoArr.size());
			return CustomResponseEntity.getOKResponse("This is the list of video", videoArr);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity getAllVideoForClient() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Video v where v.status = true order by v.id desc");
			List<Video> videoArr = (List<Video>) qry.list();
			System.out.println(videoArr.size());
			return CustomResponseEntity.getOKResponse("This is the list of video", videoArr);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity getVideoByID(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Video v where v.id = :id").setParameter("id", id);
			List<Video> videoArr = (List<Video>) qry.list();
			return CustomResponseEntity.getOKResponse("This is the video with id equal " + id, videoArr.get(0));
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity addVideo(Video newVideo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newVideo);
			return CustomResponseEntity.getOKResponse("Adding this video successfully", newVideo);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity deleteVideo(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Video targetVideo = (Video) getVideoByID(id).getData();
			if (targetVideo != null) {
				session.delete(targetVideo);
				return CustomResponseEntity.getOKResponse("Delete this video successfully", targetVideo);
			} else
				return CustomResponseEntity.getDatabaseErrorResponse();
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity updateVideo(Video newVideo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(newVideo);
			return CustomResponseEntity.getOKResponse("Update this video successfully", newVideo);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity searchVideos(String someString) {

		List<Video> targetVideos = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			String qry = "FROM Video v WHERE " + "v.title LIKE '%'||:someString||'%' OR "
					+ "v.description LIKE '%'||:someString||'%'";
			Query searchNews = session.createQuery(qry).setParameter("someString", someString);
			targetVideos = (List<Video>) searchNews.list();
			if (targetVideos.size() > 0) {

				return CustomResponseEntity.getOKResponse(
						"This is Videos with title/desciption contains '" + someString + "'", targetVideos);
			} else {
				return CustomResponseEntity.getNotFoundResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}

	}

	public CustomResponseEntity searchVideosByType(int videoTypeID) {

		List<Video> targetVideos = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			String qry = "FROM Video v WHERE " + "v.videotype.id = :vidTypeID";
			Query searchNews = session.createQuery(qry).setParameter("vidTypeID", videoTypeID);
			targetVideos = (List<Video>) searchNews.list();
			if (targetVideos.size() > 0) {

				return CustomResponseEntity.getOKResponse(
						"This is you target videos with type " + targetVideos.get(0).getVideotype().getName(),
						targetVideos);
			} else {
				return CustomResponseEntity.getNotFoundResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}

	}

}
