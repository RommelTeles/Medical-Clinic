package com.rommel.clinical.application.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.core.customer.repository.CustomerRepository;
import com.rommel.clinical.infra.AbstractRepository;
import com.rommel.clinical.infra.AbstractService;

@Service
public class CustomerService extends AbstractService<Customer> {
	
	@Autowired
	private CustomerRepository repository;

	@Override
	protected AbstractRepository<Customer> getRepository() {
		return this.repository;
	}

}
