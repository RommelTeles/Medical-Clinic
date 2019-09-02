package com.rommel.clinical.core.doctor.repository;

import org.springframework.stereotype.Repository;

import com.rommel.clinical.core.doctor.entity.Doctor;
import com.rommel.clinical.infra.AbstractRepository;

@Repository
public interface DoctorRepository extends AbstractRepository<Doctor>{

}
