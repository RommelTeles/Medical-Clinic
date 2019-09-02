package com.rommel.clinical.application.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rommel.clinical.core.appointment.entity.Appointment;
import com.rommel.clinical.core.appointment.repository.AppointmentRepository;
import com.rommel.clinical.infra.AbstractRepository;
import com.rommel.clinical.infra.AbstractService;

@Service
public class AppointmentService extends AbstractService<Appointment> {
	
	@Autowired
	private AppointmentRepository repository;

	@Override
	protected AbstractRepository<Appointment> getRepository() {
		return this.repository;
	}

}
