package com.rommel.clinical.core.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.rommel.clinical.core.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
