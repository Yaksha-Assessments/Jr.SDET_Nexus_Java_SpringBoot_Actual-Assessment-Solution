package com.appointment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	/**
	 * Dynamic query: Finds schedules for a specific doctor on a specific day.
	 * 
	 * @param doctorId the ID of the doctor
	 * @param day      the day to search for
	 * @return a list of schedules for the specified doctor on the specified day
	 */
	List<Schedule> findByDoctorIdAndDay(Long doctorId, String day);
}
