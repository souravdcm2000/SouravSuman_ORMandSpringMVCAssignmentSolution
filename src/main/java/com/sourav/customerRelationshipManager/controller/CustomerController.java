package com.sourav.customerRelationshipManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sourav.customerRelationshipManager.entity.Customer;
import com.sourav.customerRelationshipManager.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	// Mapping for customer list
	@RequestMapping("/list")
	public String customerList(Model uiModel) {
		
		// List of Customers from service
		List<Customer> customers = customerService.findAll();
		
		// Add to the UI Model
		uiModel.addAttribute("Customers", customers);
		
		return "customer-list";
	}
	
	// Mapping for add customer
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model uiModel) {
		
		// Entity Model to bind with uiModel
		Customer customer = new Customer();
		
		uiModel.addAttribute("Customer", customer);
		
		return "customer-details-form";
	}
	
	// Mapping for Update
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,
			Model uiModel) {
		
		// Get the customer with given ID
		Customer customer = customerService.findById(id);
		
		// Set as ui Model to pre-populate with customer data
		uiModel.addAttribute("Customer", customer);
		
		return "customer-details-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		Customer customer;
		if(id != 0)
		{
			customer = customerService.findById(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
		}
		else
			customer = new Customer(firstName, lastName, email);
		// save the Book
		customerService.save(customer);


		// use a redirect to prevent duplicate submissions
		return "redirect:/customers/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {

		// delete the Customer
		customerService.deleteById(id);

		// redirect to /customers/list
		return "redirect:/customers/list";
	}

}
