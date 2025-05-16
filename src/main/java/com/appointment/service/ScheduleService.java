package com.appointment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.appointment.dto.ScheduleDTO;
import com.appointment.exception.NotFoundException;

public interface ScheduleService {

	/**
	 * Creates a new schedule with the given details.
	 * 
	 * @param scheduleDTO the schedule details to be added
	 * @return the created ScheduleDTO with assigned ID
	 */
	ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

	/**
	 * Updates an existing schedule with new details.
	 * 
	 * @param scheduleId  the ID of the schedule to update
	 * @param scheduleDTO the new schedule details
	 * @return the updated ScheduleDTO
	 * @throws NotFoundException if the schedule with the given ID is not found
	 */
	ScheduleDTO updateSchedule(Long scheduleId, ScheduleDTO scheduleDTO) throws NotFoundException;

	/**
	 * Gets an existing schedule with details.
	 * 
	 * @param scheduleId the ID of the schedule to get
	 * @return the ScheduleDTO
	 * @throws NotFoundException if the schedule with the given ID is not found
	 */
	ScheduleDTO getScheduleById(Long scheduleId) throws NotFoundException;

	/**
	 * Deletes a schedule by its ID.
	 * 
	 * @param scheduleId the ID of the schedule to delete
	 * @return the boolean value representing the status
	 * @throws NotFoundException if the schedule with the given ID is not found
	 */
	boolean deleteSchedule(Long scheduleId) throws NotFoundException;

	/**
	 * Retrieves a list of all schedules in a paginated format.
	 * 
	 * @param pageable the pagination information
	 * @return a Page of ScheduleDTOs representing all schedules
	 */
	Page<ScheduleDTO> getAllSchedules(Pageable pageable);

	/**
	 * Retrieves a list of schedules for a specific doctor on a specific day.
	 * 
	 * @param doctorId the ID of the doctor
	 * @param day      the day to search for
	 * @return a list of ScheduleDTOs representing the doctor's schedules on the
	 *         given day
	 */
	List<ScheduleDTO> getDoctorScheduleByDay(Long doctorId, String day);

	/**
	 * Retrieves a list of appointments for a specific doctor on a specific day.
	 * 
	 * @param doctorId the ID of the doctor
	 * @param day      the day to search for
	 * @return a list of ScheduleDTOs representing the doctor's appointments on the
	 *         given day
	 */
	List<ScheduleDTO> getDoctorAppointments(Long doctorId, String day);
}
