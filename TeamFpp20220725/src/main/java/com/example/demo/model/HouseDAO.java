package com.example.demo.model;


import java.io.Serializable;
import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

public class HouseDAO {
	public HouseListVO  getList() {
		List<House> hous= getAll();
		return new HouseListVO(hous);
	}
    public List<House>  getAll(){
    	Configuration configObj = new Configuration();	
    	configObj.addAnnotatedClass(House.class);
		configObj.configure("hibernate.cfg.xml");
		SessionFactory factory= configObj.buildSessionFactory();		
		Session session = null;
		session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<House> data = session.createQuery("FROM House").list();
			System.out.println(data);
			tx.commit();
			return data;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
    }
  //新增資料，連線到資料庫
    public void add(House  ep) {
    	Configuration configObj = new Configuration();	
    	configObj.addAnnotatedClass(House.class);
		configObj.configure("hibernate.cfg.xml");
		SessionFactory  factory= configObj.buildSessionFactory();		
		Session session = null;
		session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Serializable sz=session.save(ep);
			System.out.println(sz);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
    }
    
    //刪除資料，連線到資料庫
    public void remove(House  ep) {
    	Configuration configObj = new Configuration();	
    	configObj.addAnnotatedClass(House.class);
		configObj.configure("hibernate.cfg.xml");
		SessionFactory  factory= configObj.buildSessionFactory();		
		Session session = null;
		session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(ep);			
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
    }
    public void update(House  ep) {
    	Configuration configObj = new Configuration();	
    	configObj.addAnnotatedClass(House.class);
		configObj.configure("hibernate.cfg.xml");
		SessionFactory  factory= configObj.buildSessionFactory();		
		Session session = null;
		session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(ep);			
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
    }
	
}
