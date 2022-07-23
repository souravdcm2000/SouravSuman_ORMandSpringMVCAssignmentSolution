package com.sourav.customerRelationshipManager.dao;

import java.util.List;

import com.sourav.customerRelationshipManager.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getAllCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Customer findCustomerById(int id);
	
	public void deleteCustomerById(int id);

}
