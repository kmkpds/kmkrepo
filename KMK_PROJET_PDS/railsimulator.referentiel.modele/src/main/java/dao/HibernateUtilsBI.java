package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilsBI {

	private static SessionFactory sessionFactoryBI;
	private static ServiceRegistry serviceRegistry;
 
	static {
		try {
			Configuration configuration = new Configuration();
            configuration.configure("/hibernateBI.cfg.xml");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
            sessionFactoryBI = configuration.buildSessionFactory(serviceRegistry);
		
	
		} catch (HibernateException ex) {
			throw new RuntimeException("Exception building SessionFactory: " + ex.getMessage(), ex);
		}
	}

	public static Session getSessionBI() throws HibernateException {
		 return sessionFactoryBI.openSession();
	
	}
}
