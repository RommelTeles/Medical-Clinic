package com.rommel.clinical.api.doctor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rommel.clinical.application.doctor.DoctorService;
import com.rommel.clinical.core.doctor.entity.Doctor;

@RestController
@RequestMapping("/clinical/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

	@Autowired
	private DoctorService service;

	@PostMapping
	public ResponseEntity<Doctor> insert(@RequestBody final Doctor doctor) {
		Doctor newDoctor = this.service.insert(doctor);
		return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Doctor> update(@RequestBody final Doctor doctor) {
		Doctor doctorUpdated = this.service.update(doctor);
		return new ResponseEntity<>(doctorUpdated, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Doctor> get(@PathVariable final Long id) {
		Doctor doctor = this.service.findById(id);

		if (doctor != null) {
			return ResponseEntity.ok(doctor);
		}

		return ResponseEntity.ok(new Doctor());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Doctor> delete(@PathVariable final Long id) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<Collection<Doctor>> get() {
		Collection<Doctor> doctors = this.service.findAll();
		return ResponseEntity.ok(doctors);
	}
}
