package com.rommel.clinical.application.appointment;

import java.time.LocalDate;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rommel.clinical.application.customer.CustomerService;
import com.rommel.clinical.application.doctor.DoctorService;
import com.rommel.clinical.core.appointment.entity.Appointment;
import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.core.doctor.entity.Doctor;
import com.rommel.clinical.utils.AppointmentCreator;
import com.rommel.clinical.utils.CustomerCreator;
import com.rommel.clinical.utils.DoctorCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestAppointmentService {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService service;

	@Test
	public void test001InsertError() {
		Appointment appointment = new Appointment();
		try {
			service.insert(appointment);
			Assert.fail("The test should fail");
		} catch (Exception e) {
			Assert.assertThat(e.getLocalizedMessage(), CoreMatchers.notNullValue());

		}

	}

	@Test
	public void test002InsertOK() {
		Appointment appointment = createAppointment("Siclano", "Sem Rua", "Mario", "N/S", "0001/PE");

		Appointment appointmentDB = this.service.findById(appointment.getId());
		Assert.assertThat(appointmentDB, CoreMatchers.notNullValue());
	}

	@Test
	public void test003Get() {

		Appointment appointment = createAppointment("Fulano", "Sem Rua", "Heladio", "N/S", "0010/PE");

		Appointment appointmentDB = this.service.findById(appointment.getId());

		Assert.assertThat(appointment.getId(), CoreMatchers.equalTo(appointmentDB.getId()));
		Assert.assertThat(appointment.getCustomer().getId(), CoreMatchers.equalTo(appointmentDB.getCustomer().getId()));
		Assert.assertThat(appointment.getDoctor().getId(), CoreMatchers.equalTo(appointmentDB.getDoctor().getId()));
		Assert.assertThat(appointment.getDate(), CoreMatchers.equalTo(appointmentDB.getDate()));

	}

	@Test
	public void test004Update() {

		Appointment appointment = createAppointment("Chico da Silva", "Sem Rua", "Monica", "N/S", "0012/PE");

		Appointment appointmentDB = this.service.findById(appointment.getId());
		
		Doctor newDoctor = DoctorCreator.create("Bueno", "Brejo Santo", "1111/CE");
		
		this.doctorService.insert(newDoctor);
		
		appointmentDB.setDoctor(newDoctor);

		this.service.update(appointmentDB);
		
		Appointment appointmentUpdated = this.service.findById(appointment.getId());

		
		Assert.assertThat(appointment.getId(), CoreMatchers.equalTo(appointmentUpdated.getId()));
		Assert.assertThat(newDoctor.getId(), CoreMatchers.equalTo(appointmentUpdated.getDoctor().getId()));

	}
	
	@Test
	public void test005List() {
		
		Appointment appointment = createAppointment("Chico da Silva", "Sem Rua", "Monica", "N/S", "0013/PE");
		Appointment appointment2 = createAppointment("Bezerra da Silva", "Sem Rua", "Ricardo", "N/S", "0014/PE");
		Appointment appointment3 = createAppointment("Rafael da Silva", "Sem Rua", "Paulo", "N/S", "0015/PE");
		
		List<Appointment> appointments = this.service.findAll();
		Assert.assertThat(appointments.size(), Matchers.greaterThan(2));
		Assert.assertTrue(appointments.contains(appointment));
		Assert.assertTrue(appointments.contains(appointment2));
		Assert.assertTrue(appointments.contains(appointment3));
		
	}
	
	@Test
	public void test006Delete() {
		Appointment appointment = createAppointment("Maria da Silva", "Sem Rua", "Paulo Ricardo", "N/S", "0016/PE");

		this.service.delete(appointment.getId());
		
		Appointment appointmentDB = this.service.findById(appointment.getId());
		Assert.assertThat(appointmentDB, Matchers.nullValue());

	}

	private Appointment createAppointment(String customerName, String customerAddress, String doctorName,
			String doctorAddress, String crm) {
		Customer customer = CustomerCreator.create(customerName, customerAddress);
		this.customerService.insert(customer);

		Doctor doctor = DoctorCreator.create(doctorName, doctorAddress, crm);
		this.doctorService.insert(doctor);

		Appointment appointment = AppointmentCreator.create(customer, doctor, LocalDate.now());
		this.service.insert(appointment);
		return appointment;
	}

}
