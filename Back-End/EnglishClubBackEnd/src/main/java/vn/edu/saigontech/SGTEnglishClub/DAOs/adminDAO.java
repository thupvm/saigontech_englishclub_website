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
public class adminDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CustomResponseEntity login(String username, String password) {
		CustomResponseEntity res = new CustomResponseEntity();
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

}
