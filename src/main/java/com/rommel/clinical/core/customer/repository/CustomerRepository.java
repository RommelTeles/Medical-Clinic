package com.rommel.clinical.core.customer.repository;


import org.springframework.stereotype.Repository;

import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.infra.AbstractRepository;

@Repository
public interface CustomerRepository extends AbstractRepository<Customer> {

}
