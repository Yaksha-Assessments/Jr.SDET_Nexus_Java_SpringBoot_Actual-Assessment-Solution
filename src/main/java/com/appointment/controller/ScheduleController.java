package com.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.dto.ScheduleDTO;
import com.appointment.exception.NotFoundException;
import com.appointment.service.ScheduleService;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

	private final ScheduleService scheduleService;

	@Autowired
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@PostMapping("/appointment")
	public ResponseEntity<ScheduleDTO> scheduleAppointment(@Validated @RequestBody ScheduleDTO scheduleDTO) {
		ScheduleDTO scheduledAppointment = scheduleService.createSchedule(scheduleDTO);
		return new ResponseEntity<>(scheduledAppointment, HttpStatus.CREATED);
	}

	@PutMapping("/appointment/{id}")
	public ResponseEntity<ScheduleDTO> updateAppointment(@PathVariable Long id,
			@Validated @RequestBody ScheduleDTO scheduleDTO) {
		ScheduleDTO updatedAppointment;
		try {
			updatedAppointment = scheduleService.updateSchedule(id, scheduleDTO);
			return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/appointment/{id}")
	public ResponseEntity<ScheduleDTO> getAppointmentById(@PathVariable Long id) {
		ScheduleDTO appointment;
		try {
			appointment = scheduleService.getScheduleById(id);
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/doctor/{id}/{day}")
	public ResponseEntity<List<ScheduleDTO>> getDoctorScheduleByDay(@PathVariable Long id, @PathVariable String day) {
		List<ScheduleDTO> doctorSchedule = scheduleService.getDoctorScheduleByDay(id, day);
		return new ResponseEntity<>(doctorSchedule, HttpStatus.OK);
	}
}
