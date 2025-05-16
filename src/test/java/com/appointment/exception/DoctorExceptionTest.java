package com.appointment.exception;

import static com.appointment.utils.MasterData.getDoctorDTO;
import static com.appointment.utils.TestUtils.currentTest;
import static com.appointment.utils.TestUtils.exceptionTestFile;
import static com.appointment.utils.TestUtils.testReport;
import static com.appointment.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.appointment.controller.DoctorController;
import com.appointment.dto.DoctorDTO;
import com.appointment.service.DoctorService;
import com.appointment.utils.MasterData;

@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc
public class DoctorExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService doctorService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDoctorInvalidDataException() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();
		doctorDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/doctors")
				.content(MasterData.asJsonString(doctorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateDoctorInvalidDataException() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();
		doctorDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/doctors/" + doctorDTO.getId())
				.content(MasterData.asJsonString(doctorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}
}
