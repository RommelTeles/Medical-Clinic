package com.rommel.clinical.application.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rommel.clinical.core.doctor.entity.Doctor;
import com.rommel.clinical.core.doctor.repository.DoctorRepository;
import com.rommel.clinical.infra.AbstractRepository;
import com.rommel.clinical.infra.AbstractService;

@Service
public class DoctorService extends AbstractService<Doctor>{
	
	@Autowired
	private DoctorRepository repository;

	@Override
	protected AbstractRepository<Doctor> getRepository() {
		return this.repository;
	}

}
