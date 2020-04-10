package com.practice.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//create a query
		String s="from Customer";
		Query<Customer> theQuery=currentSession.createQuery(s, Customer.class);
		
		//execute 
		List<Customer> customers=theQuery.getResultList();
		
		//return result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currSession=sessionFactory.getCurrentSession();
		
		//save or update customer
		currSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomers(int theId) {
		
		//get current hibernate session
		Session cSession=sessionFactory.getCurrentSession();
		
		//now retrieve/read from db using primary key
		Customer theCustomer=cSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get current hibernate session
		Session cuSession=sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery=cuSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

}
