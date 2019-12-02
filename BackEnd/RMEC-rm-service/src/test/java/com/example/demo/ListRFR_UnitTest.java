package com.example.demo;

import com.example.demo.model.Rfr_New;
import com.example.demo.repository.Rfr_New_Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest() // for restTemplate
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ListRFR_UnitTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	Rfr_New_Repository mockRfr_New_Repository;

	@Test
	public void listRFR_Ok_1() throws Exception {
		List<Rfr_New> listRfr = Arrays.asList(
				new Rfr_New(1l, 1l, "roleName", "rankName", "skillName", "locationName", null, null, false, null));
		when(mockRfr_New_Repository.findAllRfr_NewByProjectId(1l)).thenReturn(listRfr);
		mockMvc.perform(get("/listRFR/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.rfr", hasSize(1)))
				.andExpect(jsonPath("$.data.rfr[0].roleName", is("roleName")))
				.andExpect(jsonPath("$.data.rfr[0].rankName", is("rankName")))
				.andExpect(jsonPath("$.data.rfr[0].skillName", is("skillName")))
				.andExpect(jsonPath("$.data.rfr[0].locationName", is("locationName")));
		verify(mockRfr_New_Repository, times(1)).findAllRfr_NewByProjectId(1L);
	}

	@Test
	public void listRFR_Ok_n() throws Exception {
		List<Rfr_New> listRfr = Arrays.asList(
				new Rfr_New(1l, 1l, "roleName", "rankName", "skillName", "locationName", null, null, false, null),
				new Rfr_New(1l, 1l, "roleName2", "rankName2", "skillName2", "locationName2", null, null, false, null));
		when(mockRfr_New_Repository.findAllRfr_NewByProjectId(1l)).thenReturn(listRfr);
		mockMvc.perform(get("/listRFR/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.rfr", hasSize(2)))
				.andExpect(jsonPath("$.data.rfr[0].roleName", is("roleName")))
				.andExpect(jsonPath("$.data.rfr[0].rankName", is("rankName")))
				.andExpect(jsonPath("$.data.rfr[0].skillName", is("skillName")))
				.andExpect(jsonPath("$.data.rfr[0].locationName", is("locationName")))
				.andExpect(jsonPath("$.data.rfr[1].roleName", is("roleName2")))
				.andExpect(jsonPath("$.data.rfr[1].rankName", is("rankName2")))
				.andExpect(jsonPath("$.data.rfr[1].skillName", is("skillName2")))
				.andExpect(jsonPath("$.data.rfr[1].locationName", is("locationName2")));
		verify(mockRfr_New_Repository, times(1)).findAllRfr_NewByProjectId(1L);
	}

	@Test
	public void listRFR_EntryData() throws Exception {
		List<Rfr_New> listRfr = Arrays.asList();
		when(mockRfr_New_Repository.findAllRfr_NewByProjectId(1l)).thenReturn(listRfr);
		mockMvc.perform(get("/listRFR/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status", is(404)))
				.andExpect(jsonPath("$.error", is("Not Found")));
		verify(mockRfr_New_Repository, times(1)).findAllRfr_NewByProjectId(1L);
	}

	@Test
	public void listRFR_IdNotFormat() throws Exception {
		List<Rfr_New> listRfr = Arrays.asList();
		when(mockRfr_New_Repository.findAllRfr_NewByProjectId(1l)).thenReturn(listRfr);
		mockMvc.perform(get("/listRFR/")).andExpect(status().is(400));
	}
}
