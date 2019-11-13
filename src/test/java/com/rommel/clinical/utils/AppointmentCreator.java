package com.rommel.clinical.utils;

import java.time.LocalDate;

import com.rommel.clinical.core.appointment.entity.Appointment;
import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.core.doctor.entity.Doctor;

public class AppointmentCreator {
	
	public static Appointment create(Customer customer, Doctor doctor, LocalDate date) {
		return new Appointment(null, customer, doctor, date);
	}

}
