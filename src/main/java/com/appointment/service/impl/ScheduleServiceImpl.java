package com.appointment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.dto.DoctorDTO;
import com.appointment.dto.ScheduleDTO;
import com.appointment.entity.Doctor;
import com.appointment.entity.Schedule;
import com.appointment.exception.NotFoundException;
import com.appointment.repo.ScheduleRepository;
import com.appointment.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleRepository scheduleRepository;

	@Autowired
	public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	@Override
	public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = convertToEntity(scheduleDTO);
		schedule = scheduleRepository.save(schedule);
		return convertToDTO(schedule);
	}

	@Override
	public ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO) throws NotFoundException {
		Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
		if (optionalSchedule.isPresent()) {
			Schedule schedule = optionalSchedule.get();
			// Update schedule's fields from scheduleDTO
			schedule.setName(scheduleDTO.getName());

			Doctor doctor = new Doctor();
			doctor.setId(scheduleDTO.getDoctor().getId());
			doctor.setName(scheduleDTO.getDoctor().getName());
			doctor.setHospitalName(scheduleDTO.getDoctor().getHospitalName());
			doctor.setSpecialty(scheduleDTO.getDoctor().getSpecialty());
			doctor.setDailyTime(scheduleDTO.getDoctor().getDailyTime());

			schedule.setDoctor(doctor);
			schedule.setDay(scheduleDTO.getDay());
			schedule.setTime(scheduleDTO.getTime());
			schedule.setTimings(scheduleDTO.getTimings());
			schedule.setNameOfPatient(scheduleDTO.getNameOfPatient());

			schedule = scheduleRepository.save(schedule);
			return convertToDTO(schedule);
		} else {
			throw new NotFoundException("Schedule not found");
		}
	}

	@Override
	public List<ScheduleDTO> getAllSchedules() {
		List<Schedule> schedules = scheduleRepository.findAll();
		return schedules.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ScheduleDTO getScheduleById(Long id) throws NotFoundException {
		Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
		if (optionalSchedule.isPresent()) {
			return convertToDTO(optionalSchedule.get());
		} else {
			throw new NotFoundException("Schedule not found");
		}
	}

	@Override
	public boolean deleteSchedule(Long id) throws NotFoundException {
		Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
		if (optionalSchedule.isPresent()) {
			scheduleRepository.deleteById(id);
			return true;
		} else {
			throw new NotFoundException("Schedule not found");
		}
	}

	@Override
	public List<ScheduleDTO> getDoctorAppointments(Long doctorId, String day) {
		List<Schedule> appointments = scheduleRepository.findByDoctorIdAndDay(doctorId, day);
		return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private ScheduleDTO convertToDTO(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setId(schedule.getId());
		scheduleDTO.setName(schedule.getName());

		DoctorDTO doctor = new DoctorDTO();
		doctor.setId(schedule.getDoctor().getId());
		doctor.setName(schedule.getDoctor().getName());
		doctor.setHospitalName(schedule.getDoctor().getHospitalName());
		doctor.setSpecialty(schedule.getDoctor().getSpecialty());
		doctor.setDailyTime(schedule.getDoctor().getDailyTime());

		scheduleDTO.setDoctor(doctor);
		scheduleDTO.setDay(schedule.getDay());
		scheduleDTO.setTime(schedule.getTime());
		scheduleDTO.setTimings(schedule.getTimings());
		scheduleDTO.setNameOfPatient(schedule.getNameOfPatient());
		return scheduleDTO;
	}

	private Schedule convertToEntity(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		schedule.setId(scheduleDTO.getId());
		schedule.setName(scheduleDTO.getName());

		Doctor doctor = new Doctor();
		doctor.setId(scheduleDTO.getDoctor().getId());
		doctor.setName(scheduleDTO.getDoctor().getName());
		doctor.setHospitalName(scheduleDTO.getDoctor().getHospitalName());
		doctor.setSpecialty(scheduleDTO.getDoctor().getSpecialty());
		doctor.setDailyTime(scheduleDTO.getDoctor().getDailyTime());

		schedule.setDoctor(doctor);
		schedule.setDay(scheduleDTO.getDay());
		schedule.setTime(scheduleDTO.getTime());
		schedule.setTimings(scheduleDTO.getTimings());
		schedule.setNameOfPatient(scheduleDTO.getNameOfPatient());
		return schedule;
	}

	@Override
	public List<ScheduleDTO> getDoctorScheduleByDay(Long doctorId, String day) {
		List<Schedule> doctorSchedules = scheduleRepository.findByDoctorIdAndDay(doctorId, day);
		return doctorSchedules.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
