/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Appointment;
import datamodel.Users;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() 
   {
      if (sessionFactory != null) 
      {
         return sessionFactory;
      }
     
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Appointment> listAppointments() 
   {
      List<Appointment> resultList = new ArrayList<Appointment>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try
      {
         tx = session.beginTransaction();
         List<?> appointments = session.createQuery("FROM Appointment").list();
         for (Iterator<?> iterator = appointments.iterator(); iterator.hasNext();)
         {
        	 Appointment created = (Appointment) iterator.next();
            resultList.add(created);
         }
         tx.commit();
      }
      catch (HibernateException e) 
      {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      }
      finally 
      {
         session.close();
      }
      return resultList;
   }

   public static List<Appointment> listAppointments(String keyword)
   {
      List<Appointment> resultList = new ArrayList<Appointment>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      
      int holder = Integer.parseInt(keyword);
      
      //TODO: SEARCHES BY THE ID NUMBER
      
      try 
      {
         tx = session.beginTransaction();
         List<?> appointments = session.createQuery("FROM Appointment").list();
         for (Iterator<?> iterator = appointments.iterator(); iterator.hasNext();) 
         {
        	Appointment created = (Appointment) iterator.next();
            if (created.getId().equals(holder)) 
            {
               resultList.add(created);
            }
         }
         tx.commit();
      }
      catch (HibernateException e) 
      {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createAppointments(int id, String firstName, String lastName, String address,
		   String date, String time, String details) 
   {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try 
      {
         tx = session.beginTransaction();
         session.save(new Appointment(id, firstName, lastName, address, date, time, details));
         tx.commit();
      } 
      catch (HibernateException e) 
      {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      }
      finally 
      {
         session.close();
      }
   }
   
   public static void createUsers(String firstName, String lastName) 
   {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try 
      {
         tx = session.beginTransaction();
         session.save(new Users(firstName, lastName));
         tx.commit();
      } 
      catch (HibernateException e) 
      {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      }
      finally 
      {
         session.close();
      }
   }
   
   public static List<Users> listUsers() 
   {
      List<Users> resultList = new ArrayList<Users>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try
      {
         tx = session.beginTransaction();
         List<?> appointments = session.createQuery("FROM Users").list();
         for (Iterator<?> iterator = appointments.iterator(); iterator.hasNext();)
         {
        	 Users created = (Users) iterator.next();
            resultList.add(created);
         }
         tx.commit();
      }
      catch (HibernateException e) 
      {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      }
      finally 
      {
         session.close();
      }
      return resultList;
   }
   
   public static boolean isUser(String email, String password)
   {
		List<Users> resultList = listUsers();

		Users personLogginIn = resultList.stream().filter(Users -> email.contentEquals(Users.getEmail())).findFirst().orElse(null);
		
		if (personLogginIn == null || !personLogginIn.getPassword().equals(password)) {
			return false;
		} else {
			return true;
		}
   }
}
