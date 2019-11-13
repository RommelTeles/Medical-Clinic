package com.rommel.clinical.core.doctor;

import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rommel.clinical.core.doctor.entity.Doctor;
import com.rommel.clinical.core.doctor.repository.DoctorRepository;
import com.rommel.clinical.utils.DoctorCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestDoctorRepository {
	
	@Autowired
	private DoctorRepository repository;
	
    @Test
    public void test001InsertError() {
        Doctor doctor = new Doctor();
        try {
            repository.save(doctor);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e.getLocalizedMessage(), CoreMatchers.notNullValue());
        }
    }
    
    @Test
    public void test002InsertOK() {
    	Doctor doctor = DoctorCreator.create("Carol Teles", "Salgueiro", "12345/PE");
    	this.repository.save(doctor);
    	Assert.assertThat(doctor.getId(), CoreMatchers.notNullValue());
    }
    
    @Test
    public void test003Get() {
    	Doctor doctor = DoctorCreator.create("Heladio Teles", "Crato", "23456/PE");
    	this.repository.save(doctor);
    	Optional<Doctor> doctorFromDB = this.repository.findById(doctor.getId());
    	Assert.assertThat(doctor.getId(),
                CoreMatchers.equalTo(doctorFromDB.get().getId()));
        Assert.assertThat(doctor.getName(),
                CoreMatchers.equalTo(doctorFromDB.get().getName()));
        Assert.assertThat(doctor.getAddress(),
                CoreMatchers.equalTo(doctorFromDB.get().getAddress()));
    }

}
