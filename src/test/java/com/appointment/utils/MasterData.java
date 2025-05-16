package com.appointment.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.appointment.dto.DoctorDTO;
import com.appointment.dto.ScheduleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	public static DoctorDTO getDoctorDTO() {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setId(1L);
		doctorDTO.setName("Dr. Smith");
		doctorDTO.setHospitalName("City Hospital");
		doctorDTO.setSpecialty("Pediatrics");
		doctorDTO.setDailyTime("08:00-17:00"); // Example format for daily time (working hours)
		return doctorDTO;
	}

	public static List<DoctorDTO> getDoctorDTOList() {
		List<DoctorDTO> doctorDTOList = new ArrayList<>();

		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setId(1L);
		doctorDTO.setName("Dr. Smith");
		doctorDTO.setHospitalName("City Hospital");
		doctorDTO.setSpecialty("Pediatrics");
		doctorDTO.setDailyTime("08:00-17:00"); // Example format for daily time (working hours)

		doctorDTOList.add(doctorDTO);

		return doctorDTOList;
	}

	public static Page<DoctorDTO> getDoctorDTOPage(int page, int size) {
		List<DoctorDTO> courseDTOList = getDoctorDTOList();
		PageRequest pageRequest = PageRequest.of(page, size);
		return new PageImpl<>(courseDTOList, pageRequest, courseDTOList.size());
	}

	public static ScheduleDTO getScheduleDTO() {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setId(1L);
		scheduleDTO.setNameOfPatient("John Doe");
		scheduleDTO.setDay("Monday");
		scheduleDTO.setTime(LocalTime.parse("09:00")); // Example format for time (LocalTime)

		// Assuming DoctorDTO is used in ScheduleDTO
		DoctorDTO doctorDTO = getDoctorDTO();
		scheduleDTO.setDoctor(doctorDTO);

		return scheduleDTO;
	}

	public static List<ScheduleDTO> getScheduleDTOList() {
		List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setId(1L);
		scheduleDTO.setNameOfPatient("John Doe");
		scheduleDTO.setDay("Monday");
		scheduleDTO.setTime(LocalTime.parse("09:00")); // Example format for time (LocalTime)

		// Assuming DoctorDTO is used in ScheduleDTO
		DoctorDTO doctorDTO = getDoctorDTO();
		scheduleDTO.setDoctor(doctorDTO);

		scheduleDTOList.add(scheduleDTO);

		return scheduleDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
