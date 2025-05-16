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

import com.appointment.dto.ScheduleDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ScheduleBoundaryTest {

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
	public void testNameNotNull() throws IOException {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setName(null);
		Set<ConstraintViolation<ScheduleDTO>> violations = validator.validate(scheduleDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDoctorNotNull() throws IOException {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setDoctor(null);
		Set<ConstraintViolation<ScheduleDTO>> violations = validator.validate(scheduleDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDayNotNull() throws IOException {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setDay(null);
		Set<ConstraintViolation<ScheduleDTO>> violations = validator.validate(scheduleDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTimeNotNull() throws IOException {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setTime(null);
		Set<ConstraintViolation<ScheduleDTO>> violations = validator.validate(scheduleDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTimingsNotNull() throws IOException {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setTimings(null);
		Set<ConstraintViolation<ScheduleDTO>> violations = validator.validate(scheduleDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
