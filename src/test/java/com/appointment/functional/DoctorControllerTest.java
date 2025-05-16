package com.appointment.functional;

import static com.appointment.utils.MasterData.getDoctorDTOPage;
import static com.appointment.utils.MasterData.getDoctorDTO;
import static com.appointment.utils.TestUtils.businessTestFile;
import static com.appointment.utils.TestUtils.currentTest;
import static com.appointment.utils.TestUtils.testReport;
import static com.appointment.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService doctorService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testListAllCourses() throws Exception {
		Page<DoctorDTO> courseDTOS = getDoctorDTOPage(0, 10);
		when(doctorService.getAllDoctors(any(Pageable.class))).thenReturn(courseDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctors")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetDoctorById() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();
		when(this.doctorService.getDoctorById(doctorDTO.getId())).thenReturn(doctorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctors/" + doctorDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(doctorDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreateDoctor() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();

		when(this.doctorService.createDoctor(any())).thenReturn(doctorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/doctors")
				.content(MasterData.asJsonString(doctorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(doctorDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateDoctor() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();

		when(this.doctorService.updateDoctor(eq(doctorDTO.getId()), any())).thenReturn(doctorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/doctors/" + doctorDTO.getId())
				.content(MasterData.asJsonString(doctorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(doctorDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeleteDoctor() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();
		when(this.doctorService.deleteDoctor(doctorDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/doctors/" + doctorDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}
}
