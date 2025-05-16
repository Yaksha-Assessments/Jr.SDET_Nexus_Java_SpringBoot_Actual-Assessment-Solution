package com.appointment.service;

import java.util.List;

import com.appointment.dto.ScheduleDTO;
import com.appointment.exception.NotFoundException;

public interface ScheduleService {

	ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

	ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO) throws NotFoundException;

	List<ScheduleDTO> getAllSchedules();

	ScheduleDTO getScheduleById(Long id) throws NotFoundException;

	boolean deleteSchedule(Long id) throws NotFoundException;

	List<ScheduleDTO> getDoctorScheduleByDay(Long doctorId, String day);

	List<ScheduleDTO> getDoctorAppointments(Long doctorId, String day);
}
