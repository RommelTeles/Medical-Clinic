package com.rommel.clinical.core.appointment.repository;

import org.springframework.stereotype.Repository;

import com.rommel.clinical.core.appointment.entity.Appointment;
import com.rommel.clinical.infra.AbstractRepository;

@Repository
public interface AppointmentRepository extends AbstractRepository<Appointment> {

}
