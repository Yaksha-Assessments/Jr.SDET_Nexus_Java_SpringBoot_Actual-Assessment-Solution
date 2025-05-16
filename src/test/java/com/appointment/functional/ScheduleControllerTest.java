package com.appointment.functional;

import static com.appointment.utils.MasterData.getScheduleDTO;
import static com.appointment.utils.MasterData.getScheduleDTOList;
import static com.appointment.utils.TestUtils.businessTestFile;
import static com.appointment.utils.TestUtils.currentTest;
import static com.appointment.utils.TestUtils.testReport;
import static com.appointment.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class ScheduleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ScheduleService scheduleService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAppointmentById() throws Exception {
		ScheduleDTO scheduleDTO = getScheduleDTO();
		when(this.scheduleService.getScheduleById(scheduleDTO.getId())).thenReturn(scheduleDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/schedules/appointment/" + scheduleDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(scheduleDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetDoctorScheduleByDay() throws Exception {
		ScheduleDTO scheduleDTO = getScheduleDTO();
		List<ScheduleDTO> scheduleDTOs = getScheduleDTOList();

		when(this.scheduleService.getDoctorScheduleByDay(eq(scheduleDTO.getDoctor().getId()), any()))
				.thenReturn(scheduleDTOs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/schedules/doctor/" + scheduleDTO.getDoctor().getId() + "/" + scheduleDTO.getDay())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(scheduleDTOs)) ? "true"
						: "false"),
				businessTestFile);
	}
}
