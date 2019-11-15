package com.rommel.clinical.api.appointment;

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

import com.rommel.clinical.application.appointment.AppointmentService;
import com.rommel.clinical.core.appointment.entity.Appointment;

@RestController
@RequestMapping("/clinical/appointment")
@CrossOrigin(origins = "*")
public class AppointmentController {

	@Autowired
	private AppointmentService service;
	
	@PostMapping
	public ResponseEntity<Appointment> insert(@RequestBody final Appointment appointment){
		Appointment newAppointment = this.service.insert(appointment);
		return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Appointment> update(@RequestBody final Appointment appointment){
		Appointment appointmentUpdated = this.service.update(appointment);
		return new ResponseEntity<>(appointmentUpdated, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Appointment> get(@PathVariable final Long id){
		Appointment appointment = this.service.findById(id);
		
		if(appointment != null) {
			return ResponseEntity.ok(appointment);
		}
		return ResponseEntity.ok(new Appointment());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Appointment> delete(@PathVariable final Long id){
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<Appointment>> get(){
		Collection<Appointment> appointments = this.service.findAll();
		return ResponseEntity.ok(appointments);
	}
}
