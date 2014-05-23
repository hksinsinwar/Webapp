package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static volatile SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		SessionFactory inst = sessionFactory;
		if (inst == null){
		synchronized(this){	
			inst=sessionFactory;
			if(inst == null){
			inst=sessionFactory = new Configuration().configure(
					"config/hibernate.cfg.xml").buildSessionFactory();
			}
		}
		}
		return sessionFactory;
	}
}
