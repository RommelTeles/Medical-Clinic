package com.rommel.clinical.application.doctor;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rommel.clinical.core.doctor.entity.Doctor;
import com.rommel.clinical.utils.DoctorCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestDoctorService {
	
	@Autowired
	private DoctorService service;
	
	@Test
    public void test001InsertError() {
        Doctor doctor = new Doctor();
        try {
            service.insert(doctor);
            Assert.fail("The test should fail");
        } catch (Exception e) {
            Assert.assertThat(e.getLocalizedMessage(), CoreMatchers.notNullValue());
        }
    }
	
	@Test
	public void test002InsertOK() {
		Doctor doctor = DoctorCreator.create("Carol", "Test", "0002/PE");
		this.service.insert(doctor);
		
		Doctor doctorFromDB = this.service.findById(doctor.getId());
		Assert.assertThat(doctorFromDB, CoreMatchers.notNullValue());
	}
	
	@Test
	public void test003Get() {
		Doctor doctor = DoctorCreator.create("José Ferro", "Test Recife", "0003/PE");
		this.service.insert(doctor);
		
		Doctor doctorDB = this.service.findById(doctor.getId());
		Assert.assertThat(doctor.getId(), CoreMatchers.equalTo(doctorDB.getId()));
		Assert.assertThat(doctor.getName(), CoreMatchers.equalTo(doctorDB.getName()));
		Assert.assertThat(doctor.getAddress(), CoreMatchers.equalTo(doctorDB.getAddress()));
		Assert.assertThat(doctor.getCrm(), CoreMatchers.equalTo(doctorDB.getCrm()));
	}
	
	@Test
	public void test004Update() {
		Doctor doctor = DoctorCreator.create("Mario Marra", "Test Olinda", "0004/PE");
		this.service.insert(doctor);
		
		Doctor doctorDB = this.service.findById(doctor.getId());
	
		String newName = "Mario Marra Melo";
		doctorDB.setName(newName);
		String newAddress = "Test End. Novo";
		doctorDB.setAddress(newAddress);
		String newCRM = "0001/PB"; 
		doctorDB.setCrm(newCRM);
		
		
		this.service.update(doctorDB);
		
		Assert.assertThat(doctor.getId(), CoreMatchers.equalTo(doctorDB.getId()));
		Assert.assertThat(newName, CoreMatchers.equalTo(doctorDB.getName()));
		Assert.assertThat(newAddress, CoreMatchers.equalTo(doctorDB.getAddress()));
		Assert.assertThat(newCRM, CoreMatchers.equalTo(doctorDB.getCrm()));
		
	}
	
	@Test
	public void test005List() {
		Doctor doctor = DoctorCreator.create("Mario Marra", "Test Olinda", "0005/PE");
		Doctor doctor2 = DoctorCreator.create("Mario Jose", "Test Natal", "0006/PE");
		Doctor doctor3 = DoctorCreator.create("Mario Fernando", "Test Salgueiro", "0007/PE");
		
		this.service.insert(doctor);
		this.service.insert(doctor2);
		this.service.insert(doctor3);
		
		List<Doctor> doctors = this.service.findAll();
		Assert.assertThat(doctors.size(), Matchers.greaterThan(2));
		Assert.assertTrue(doctors.contains(doctor));
		Assert.assertTrue(doctors.contains(doctor2));
		Assert.assertTrue(doctors.contains(doctor3));
	}
	
	@Test
	public void test006Delete() {
		Doctor doctor = DoctorCreator.create("Fernanda Alves", "Test Salvador", "0008/PE");
	
		this.service.insert(doctor);
		
		this.service.delete(doctor.getId());
		
		Doctor doctorDB = this.service.findById(doctor.getId());
		Assert.assertThat(doctorDB, Matchers.nullValue());
	}
	
}
