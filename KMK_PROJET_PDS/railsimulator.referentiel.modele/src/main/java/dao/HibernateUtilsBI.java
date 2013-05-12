package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;



public class HibernateUtilsBI {

	private static final SessionFactory sessionFactoryBI;
 
	static {
		try {
			
			sessionFactoryBI = new AnnotationConfiguration().configure("/hibernateBI.cfg.xml").buildSessionFactory();
	
		} catch (HibernateException ex) {
			throw new RuntimeException("Exception building SessionFactory: " + ex.getMessage(), ex);
		}
	}

	public static Session getSessionBI() throws HibernateException {
		 return sessionFactoryBI.openSession();
	
	}
}
