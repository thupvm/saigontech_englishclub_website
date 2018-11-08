package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.Admins;

@Transactional
public class AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Admins getAdminbyUsername(String username, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<?> qry = session.createQuery("from Admins a where a.username = :uname and a.password = :pword")
					.setParameter("uname", username).setParameter("pword", password);

			return (Admins) qry.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

}
