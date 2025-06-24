package com.appointment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appointment.dto.DoctorDTO;
import com.appointment.entity.Doctor;
import com.appointment.exception.NotFoundException;
import com.appointment.repo.DoctorRepository;
import com.appointment.service.DoctorService;

import jakarta.transaction.Transactional;

@Service
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Transactional
	@Override
	public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = convertToEntity(doctorDTO);
		doctor = doctorRepository.save(doctor);
		return convertToDTO(doctor);
	}

	@Transactional
	@Override
	public DoctorDTO updateDoctor(Long doctorId, DoctorDTO doctorDTO) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
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
	public DoctorDTO getDoctorById(Long doctorId) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		if (optionalDoctor.isPresent()) {
			return convertToDTO(optionalDoctor.get());
		} else {
			throw new NotFoundException("Doctor not found");
		}
	}

	@Transactional
	@Override
	public boolean deleteDoctor(Long doctorId) throws NotFoundException {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		if (optionalDoctor.isPresent()) {
			doctorRepository.deleteById(doctorId);
			return true;
		} else {
			throw new NotFoundException("Doctor not found");
		}
	}

	@Override
	public Page<DoctorDTO> getAllDoctors(Pageable pageable) {
		Page<Doctor> doctors = doctorRepository.findAllByOrderByNameAsc(pageable);
		return doctors.map(this::convertToDTO);
	}

	@Override
	public List<DoctorDTO> findDoctorsBySpecialty(String specialty) {
		List<Doctor> doctors = doctorRepository.findBySpecialtyOrderByNameAsc(specialty);
		return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

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
