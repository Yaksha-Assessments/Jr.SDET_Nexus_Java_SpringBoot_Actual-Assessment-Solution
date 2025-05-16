package com.appointment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.dto.DoctorDTO;
import com.appointment.entity.Doctor;
import com.appointment.exception.NotFoundException;
import com.appointment.repo.DoctorRepository;
import com.appointment.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Override
	public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = convertToEntity(doctorDTO);
		doctor = doctorRepository.save(doctor);
		return convertToDTO(doctor);
	}

	@Override
	public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isPresent()) {
			Doctor doctor = optionalDoctor.get();
			// Update doctor's fields from doctorDTO
			doctor.setName(doctorDTO.getName());
			doctor.setHospitalName(doctorDTO.getHospitalName());
			doctor.setSpecialty(doctorDTO.getSpecialty());
			doctor.setDailyTime(doctorDTO.getDailyTime());

			doctor = doctorRepository.save(doctor);
			return convertToDTO(doctor);
		} else {
			throw new NotFoundException("Doctor not found");
		}
	}

	@Override
	public List<DoctorDTO> getAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public DoctorDTO getDoctorById(Long id) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isPresent()) {
			return convertToDTO(optionalDoctor.get());
		} else {
			throw new NotFoundException("Doctor not found");
		}
	}

	@Override
	public boolean deleteDoctor(Long id) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		if (optionalDoctor.isPresent()) {
			doctorRepository.deleteById(id);
			return true;
		} else {
			throw new NotFoundException("Doctor not found");
		}
	}

	@Override
	public List<DoctorDTO> findDoctorsBySpecialty(String specialty) {
		List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
		return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Helper methods to convert between DTO and Entity
	private DoctorDTO convertToDTO(Doctor doctor) {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setId(doctor.getId());
		doctorDTO.setName(doctor.getName());
		doctorDTO.setHospitalName(doctor.getHospitalName());
		doctorDTO.setSpecialty(doctor.getSpecialty());
		doctorDTO.setDailyTime(doctor.getDailyTime());
		return doctorDTO;
	}

	private Doctor convertToEntity(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor();
		doctor.setId(doctorDTO.getId());
		doctor.setName(doctorDTO.getName());
		doctor.setHospitalName(doctorDTO.getHospitalName());
		doctor.setSpecialty(doctorDTO.getSpecialty());
		doctor.setDailyTime(doctorDTO.getDailyTime());
		return doctor;
	}
}
