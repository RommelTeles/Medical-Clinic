package com.rommel.clinical.core.customer.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rommel.clinical.core.customer.appointment.entity.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
