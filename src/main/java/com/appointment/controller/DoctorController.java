package com.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.dto.DoctorDTO;
import com.appointment.exception.NotFoundException;
import com.appointment.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private Environment env;
	
	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping
	public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
		List<DoctorDTO> doctors = doctorService.getAllDoctors();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
		DoctorDTO doctor;
		try {
			doctor = doctorService.getDoctorById(id);
			return new ResponseEntity<>(doctor, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping
	public ResponseEntity<DoctorDTO> createDoctor(@Validated @RequestBody DoctorDTO doctorDTO) {
		DoctorDTO createdDoctor = doctorService.createDoctor(doctorDTO);
		return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @Validated @RequestBody DoctorDTO doctorDTO) {
		DoctorDTO updatedDoctor;
		try {
			updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
			return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) throws NotFoundException {
		doctorService.deleteDoctor(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/specialty/{specialty}")
	public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialty(@PathVariable String specialty) {
		List<DoctorDTO> doctors = doctorService.findDoctorsBySpecialty(specialty);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<String> getProfile() {
		String profileData = env.getProperty("profile.validate.data", "This is default profile");
		return new ResponseEntity<>(profileData, HttpStatus.OK);
	}
}
