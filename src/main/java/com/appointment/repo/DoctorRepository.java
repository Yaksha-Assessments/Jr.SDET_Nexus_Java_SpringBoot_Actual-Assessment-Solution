package com.appointment.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	/**
	 * Dynamic query: Finds doctors by their specialty, ordered by name.
	 * 
	 * @param specialty the specialty to search for
	 * @return a list of doctors with the specified specialty, ordered by name
	 */
	List<Doctor> findBySpecialtyOrderByNameAsc(String specialty);

	/**
	 * Finds all doctors ordered by name.
	 * 
	 * @param pageable the pagination information
	 * @return a Page of doctors ordered by name
	 */
	Page<Doctor> findAllByOrderByNameAsc(Pageable pageable);
}
