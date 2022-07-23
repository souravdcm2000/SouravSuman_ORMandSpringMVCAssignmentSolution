package com.sourav.customerRelationshipManager.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.sourav.customerRelationshipManager.entity.Customer;

@Repository
@Transactional
@EnableTransactionManagement
public class CustomerDAOImpl implements CustomerDAO {

	// Inject Session Factory
	@Autowired
	private SessionFactory sessionFactory;

	// Create Session
	private Session session;

	@Autowired
	CustomerDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		Transaction tx = session.beginTransaction();

		// All customers into a list
		List<Customer> customers = session.createQuery("from Customer").list();

		tx.commit();

		return customers;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(customer);

		tx.commit();
	}

	@Override
	@Transactional
	public Customer findCustomerById(int id) {
		Customer customer = new Customer();
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		customer = session.get(Customer.class, id);

		tx.commit();

		return customer;
	}

	@Override
	@Transactional
	public void deleteCustomerById(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();
	}

}
