package com.appointment.service;

import java.util.List;

import com.appointment.dto.DoctorDTO;
import com.appointment.exception.NotFoundException;

public interface DoctorService {

	DoctorDTO createDoctor(DoctorDTO doctorDTO);

	DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) throws NotFoundException;

	List<DoctorDTO> getAllDoctors();

	DoctorDTO getDoctorById(Long id) throws NotFoundException;

	boolean deleteDoctor(Long id) throws NotFoundException;

	List<DoctorDTO> findDoctorsBySpecialty(String specialty);
}
