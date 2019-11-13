package com.rommel.clinical.core.customer;

import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.core.customer.repository.CustomerRepository;
import com.rommel.clinical.utils.CustomerCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestCustomerRepository {
	
    @Autowired
    private CustomerRepository repository;

 
    @Test
    public void testInsertError() {
        Customer customer = new Customer();
        try {
            repository.save(customer);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e.getLocalizedMessage(), CoreMatchers.notNullValue());
        }
    }
    
    @Test
    public void testInsertOK() {
    	
    	Customer customer = CustomerCreator.create("Carine", "Sem");
    	this.repository.save(customer);
        Assert.assertThat(customer.getId(), CoreMatchers.notNullValue());
    }
    
    @Test
    public void testGet() {
    	
    	Customer customer = CustomerCreator.create("Rommel", "Sem");
    	this.repository.save(customer);
    	
    	Optional<Customer> customerFromDB = this.repository.findById(customer.getId());
    	
    	Assert.assertThat(customer.getId(),
                CoreMatchers.equalTo(customerFromDB.get().getId()));
        Assert.assertThat(customer.getName(),
                CoreMatchers.equalTo(customerFromDB.get().getName()));
        Assert.assertThat(customer.getAddress(),
                CoreMatchers.equalTo(customerFromDB.get().getAddress()));
    	
    	
    }

}
