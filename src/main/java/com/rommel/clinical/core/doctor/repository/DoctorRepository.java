package com.rommel.clinical.core.doctor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rommel.clinical.core.doctor.entity.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long>{

}
