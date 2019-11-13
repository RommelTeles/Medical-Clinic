package com.rommel.clinical.utils;

import com.rommel.clinical.core.customer.entity.Customer;

public class CustomerCreator {

	public static Customer create(String name, String address) {
		return new Customer(null,name, address);
	}
}
