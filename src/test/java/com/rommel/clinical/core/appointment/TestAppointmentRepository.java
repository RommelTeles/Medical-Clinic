package com.rommel.clinical.core.appointment;

import java.time.LocalDate;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rommel.clinical.core.appointment.entity.Appointment;
import com.rommel.clinical.core.appointment.repository.AppointmentRepository;
import com.rommel.clinical.core.customer.TestCustomerRepository;
import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.core.customer.repository.CustomerRepository;
import com.rommel.clinical.core.doctor.TestDoctorRepository;
import com.rommel.clinical.core.doctor.entity.Doctor;
import com.rommel.clinical.core.doctor.repository.DoctorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestAppointmentRepository {

	@Autowired
	private AppointmentRepository repository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Test
	public void testInsertError() {
		Appointment appointment = new Appointment();
		try {
			this.repository.save(appointment);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertThat(e.getLocalizedMessage(), CoreMatchers.notNullValue());
		}
	}

	@Test
	public void testInsertOK() {
		Appointment appointment = createAppointment();
		this.repository.save(appointment);
		Assert.assertThat(appointment.getId(), CoreMatchers.notNullValue());

	}
	
	@Test
	public void testGet() {
		Appointment appointment = createAppointment();
		this.repository.save(appointment);
		Optional<Appointment> appointmentFromDB = this.repository.findById(appointment.getId());
		
		
		Assert.assertThat(appointment.getId(),
	                CoreMatchers.equalTo(appointmentFromDB.get().getId()));
		Assert.assertThat(appointment.getCustomer(),
                CoreMatchers.equalTo(appointmentFromDB.get().getCustomer()));
		Assert.assertThat(appointment.getDoctor(),
                CoreMatchers.equalTo(appointmentFromDB.get().getDoctor()));
		Assert.assertThat(appointment.getDate(),
                CoreMatchers.equalTo(appointmentFromDB.get().getDate()));
		
	}

	private Appointment createAppointment() {
		
		Customer customer = TestCustomerRepository.createCustomer();
		this.customerRepository.save(customer);
		Doctor doctor = TestDoctorRepository.createDoctor();
		this.doctorRepository.save(doctor);
		
		return new Appointment(null, customer, doctor,
				LocalDate.now());
	}
}
