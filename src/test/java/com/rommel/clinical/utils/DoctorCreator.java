package com.rommel.clinical.utils;

import com.rommel.clinical.core.doctor.entity.Doctor;

public class DoctorCreator {

	public static Doctor create(String name, String address, String crm) {
		return new Doctor(null,name,address,crm);
	}

}
