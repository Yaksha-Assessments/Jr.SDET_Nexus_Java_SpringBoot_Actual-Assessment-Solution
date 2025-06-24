package com.appointment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.appointment.dto.DoctorDTO;
import com.appointment.exception.NotFoundException;

public interface DoctorService {

	/**
	 * Creates a new doctor with the given details.
	 * 
	 * @param doctorDTO the doctor details to be added
	 * @return the created DoctorDTO with assigned ID
	 */
	DoctorDTO createDoctor(DoctorDTO doctorDTO);

	/**
	 * Updates an existing doctor with new details.
	 * 
	 * @param doctorId  the ID of the doctor to update
	 * @param doctorDTO the new doctor details
	 * @return the updated DoctorDTO
	 * @throws NotFoundException if the doctor with the given ID is not found
	 */
	DoctorDTO updateDoctor(Long doctorId, DoctorDTO doctorDTO) throws NotFoundException;

	/**
	 * Gets an existing doctor with details.
	 * 
	 * @param doctorId the ID of the doctor to get
	 * @return the DoctorDTO
	 * @throws NotFoundException if the doctor with the given ID is not found
	 */
	DoctorDTO getDoctorById(Long doctorId) throws NotFoundException;

	/**
	 * Deletes a doctor by its ID.
	 * 
	 * @param doctorId the ID of the doctor to delete
	 * @return the boolean value representing the status
	 * @throws NotFoundException if the doctor with the given ID is not found
	 */
	boolean deleteDoctor(Long doctorId) throws NotFoundException;

	/**
	 * Retrieves a list of all doctors in a paginated format.
	 * 
	 * @param pageable the pagination information
	 * @return a Page of DoctorDTOs representing all doctors
	 */
	Page<DoctorDTO> getAllDoctors(Pageable pageable);

	/**
	 * Retrieves a list of all doctors by specialty.
	 * 
	 * @param specialty the specialty to search for
	 * @return a list of DoctorDTOs representing all doctors with the given
	 *         specialty
	 */
	List<DoctorDTO> findDoctorsBySpecialty(String specialty);
}
