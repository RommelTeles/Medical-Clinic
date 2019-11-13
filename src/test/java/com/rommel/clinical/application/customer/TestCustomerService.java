package com.rommel.clinical.application.customer;


import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.utils.CustomerCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCustomerService {

	@Autowired
	private CustomerService service;

	@Test
	public void test001InsertError() {
		Customer customer = new Customer();

		try {
			service.insert(customer);
			Assert.fail("The test should fail");
		} catch (Exception e) {
			Assert.assertThat(e.getLocalizedMessage(), CoreMatchers.notNullValue());
		}
	}
	
	@Test
	public void test002InsertOK() {
		Customer customer = CustomerCreator.create("Name", "Address");
		this.service.insert(customer);
		Customer customerDB = this.service.findById(customer.getId());
		Assert.assertThat(customerDB, CoreMatchers.notNullValue());
	}
	
	@Test
	public void test003Get() {
		Customer customer = CustomerCreator.create("Name2", "Address2");
		this.service.insert(customer);
		Customer customerDB = this.service.findById(customer.getId());
		Assert.assertThat(customer.getId(), CoreMatchers.equalTo(customerDB.getId()));
		Assert.assertThat(customer.getAddress(), CoreMatchers.equalTo(customerDB.getAddress()));
		Assert.assertThat(customer.getName(), CoreMatchers.equalTo(customerDB.getName()));
	}
	
	@Test
	public void test004Update() {
		Customer customer = CustomerCreator.create("Name3", "Address3");
		this.service.insert(customer);
		String newAddress = "Address updated";
		customer.setAddress(newAddress);
		String newName = "Name updated";
		customer.setName(newName);
		this.service.update(customer); 
		
		Customer customerDB = this.service.findById(customer.getId());
		Assert.assertThat(customer.getId(), CoreMatchers.equalTo(customerDB.getId()));
		Assert.assertThat(newAddress, CoreMatchers.equalTo(customerDB.getAddress()));
		Assert.assertThat(newName, CoreMatchers.equalTo(customerDB.getName()));
		
	}
	
	@Test
	public void test005List() {
		Customer customer = CustomerCreator.create("Name4", "Address4");
		Customer customer2 = CustomerCreator.create("Name5", "Address5");
		Customer customer3 = CustomerCreator.create("Name6", "Address6");
		
		this.service.insert(customer);
		this.service.insert(customer2);
		this.service.insert(customer3);
		
		List<Customer> customers = this.service.findAll();
        Assert.assertThat(customers.size(), Matchers.greaterThan(2));
        Assert.assertTrue(customers.contains(customer));
        Assert.assertTrue(customers.contains(customer2));
        Assert.assertTrue(customers.contains(customer3));
	}
	
	@Test
	public void test006Delete() {
		Customer customer = CustomerCreator.create("Name7", "Address7");
		this.service.insert(customer);
		
		this.service.delete(customer.getId());
		
		Customer customerDB = this.service.findById(customer.getId());
        Assert.assertThat(customerDB, Matchers.nullValue());

	}

}
