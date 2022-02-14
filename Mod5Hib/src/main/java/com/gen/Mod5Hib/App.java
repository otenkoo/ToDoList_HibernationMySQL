package com.gen.Mod5Hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		MenuItems task = new MenuItems();

		task.greeting();
		task.menuLoop();

//		Configuration con = new Configuration().configure().addAnnotatedClass(ToDoList.class);
//
//		SessionFactory sf = con.buildSessionFactory();
//		Session session = sf.openSession();
//
//		Transaction tx = session.beginTransaction();
//
//		session.save(task);
//
//		tx.commit();
	}
}
