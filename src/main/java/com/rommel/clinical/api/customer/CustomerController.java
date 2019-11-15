package com.rommel.clinical.api.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rommel.clinical.application.customer.CustomerService;
import com.rommel.clinical.core.customer.entity.Customer;

@RestController
@RequestMapping("/clinical/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping
	public ResponseEntity<Customer> insert(@RequestBody final Customer customer) {
		Customer newCustomer = this.service.insert(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Customer> update(@RequestBody final Customer customer) {
		Customer customerUpdated = this.service.update(customer);
		return new ResponseEntity<>(customerUpdated, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> get(@PathVariable final Long id) {
		Customer customer = this.service.findById(id);

		if (customer != null) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.ok(new Customer());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> delete(@PathVariable final Long id) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<Customer>> get(){
		Collection<Customer> customers = this.service.findAll();
		return ResponseEntity.ok(customers);
	}
}
