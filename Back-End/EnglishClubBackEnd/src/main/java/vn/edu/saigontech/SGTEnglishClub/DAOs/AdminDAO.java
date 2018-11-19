package vn.edu.saigontech.SGTEnglishClub.DAOs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Configurations.TokenAuthenticationService;
import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Responses.LoginResponse;

import java.util.List;

@Transactional
public class AdminDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CustomResponseEntity login(String username, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Admin a where a.username = :uname and a.password = :pword")
					.setParameter("uname", username).setParameter("pword", password);
			List<Admin> adminArr = (List<Admin>) qry.list();
			if (adminArr.size() > 0) {
				Admin targetAdmin = adminArr.get(0);
				LoginResponse lR = new LoginResponse(targetAdmin.getId(),
						targetAdmin.getFirstname() + " " + targetAdmin.getLastname(),
						TokenAuthenticationService.getValidateToken(targetAdmin.getUsername()));
				return CustomResponseEntity.getOKResponse("Login-successfully", lR);
			} else {
				return CustomResponseEntity.getWrongUsernamePassword();
			}
		} catch (Exception e) {
			return CustomResponseEntity.getWrongUsernamePassword();
		}

	}

	public CustomResponseEntity getAllAdmin() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Admin");
			List<Admin> adminArr = (List<Admin>) qry.list();
			return CustomResponseEntity.getOKResponse("This is the list of admin", adminArr);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity getAdminByID(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> qry = session.createQuery("from Admin a where a.id = :id").setParameter("id", id);
			List<Admin> adminArr = (List<Admin>) qry.list();
			return CustomResponseEntity.getOKResponse("This is the admin with id equal " + id, adminArr.get(0));
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity addAdmin(Admin newAdmin) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(newAdmin);
			return CustomResponseEntity.getOKResponse("Adding this admin successfully", newAdmin);
		} catch (Exception e) {
			e.printStackTrace();
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

	public CustomResponseEntity deleteAdmin(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Admin targetAdmin = (Admin) getAdminByID(id).getData();
			if (targetAdmin != null) {
				session.delete(targetAdmin);
				return CustomResponseEntity.getOKResponse("Delete this admin successfully", targetAdmin);
			} else
				return CustomResponseEntity.getDatabaseErrorResponse();
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}
	
	public CustomResponseEntity updateAdmin(Admin newAdmin) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(newAdmin);
			return CustomResponseEntity.getOKResponse("Update this admin successfully", newAdmin);
		} catch (Exception e) {
			return CustomResponseEntity.getDatabaseErrorResponse();
		}
	}

}
