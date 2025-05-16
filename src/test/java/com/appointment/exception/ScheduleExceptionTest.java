package com.appointment.exception;

import static com.appointment.utils.MasterData.getScheduleDTO;
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

import com.appointment.controller.ScheduleController;
import com.appointment.dto.ScheduleDTO;
import com.appointment.service.ScheduleService;
import com.appointment.utils.MasterData;

@WebMvcTest(ScheduleController.class)
@AutoConfigureMockMvc
public class ScheduleExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ScheduleService scheduleService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateScheduleInvalidDataException() throws Exception {
		ScheduleDTO scheduleDTO = getScheduleDTO();
		scheduleDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/schedules/appointment")
				.content(MasterData.asJsonString(scheduleDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateScheduleInvalidDataException() throws Exception {
		ScheduleDTO scheduleDTO = getScheduleDTO();
		scheduleDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/schedules/appointment/" + scheduleDTO.getId())
				.content(MasterData.asJsonString(scheduleDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}
}
