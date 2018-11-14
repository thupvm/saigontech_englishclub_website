package vn.edu.saigontech.SGTEnglishClub.DAOs;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SGTEnglishClub.Models.admin;

@Transactional
public class adminDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public admin getAdminbyUsername(String username, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query<?> qry = session.createQuery("from admin a where a.userName = :uname and a.password = :pword")
					.setParameter("uname", username).setParameter("pword", password);
			return (admin) qry.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
