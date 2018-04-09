/**
 * 
 */
package com.yorma.general.dao.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static Session getOpenSession() {
		return sessionFactory.openSession();
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
