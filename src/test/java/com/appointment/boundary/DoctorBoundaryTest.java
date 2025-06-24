package com.appointment.boundary;

import static com.appointment.utils.TestUtils.boundaryTestFile;
import static com.appointment.utils.TestUtils.currentTest;
import static com.appointment.utils.TestUtils.testReport;
import static com.appointment.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.appointment.dto.DoctorDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class DoctorBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void doctorNameNotNull() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setName(null);
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameMinThree() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setName("ab"); // Less than 3 characters
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameMax255() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setName("a".repeat(256)); // More than 255 characters
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testHospitalNameNotNull() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setHospitalName(null);
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSpecialtyNotNull() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setSpecialty(null);
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDailyTimeNotNull() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setDailyTime(null);
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
