package vn.edu.saigontech.SGTEnglishClub.DAOs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AdminDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int getIDbyUsername(String username) {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query qry = session.createQuery("select a.id from Admins a where a.username like :uname")
					.setString("uname", username);
			
			int res = (int) qry.list().get(0);
			
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	

}
