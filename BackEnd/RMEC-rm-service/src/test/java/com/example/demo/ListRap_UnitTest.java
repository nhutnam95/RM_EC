package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
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
import com.example.demo.model.Rap;
import com.example.demo.repository.RapRepository;

@RunWith(SpringRunner.class)
@SpringBootTest() // for restTemplate
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ListRap_UnitTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	RapRepository mockRapRepository;
	
	@Test
	public void listRAP_Ok_1() throws Exception {
		List<Rap> listRap = Arrays.asList(
				new Rap(1l, 1l, 1l, "NamDN3", "M1", "M5", 0.5f, "Planning", null, null, false));
		
		when(mockRapRepository.findAllRapByProjectId(1l)).thenReturn(listRap);
		mockMvc.perform(get("/listRAP/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.rap", hasSize(1)))
				.andExpect(jsonPath("$.data.rap[0].accountName", is("NamDN3")))
				.andExpect(jsonPath("$.data.rap[0].from", is("M1")))
				.andExpect(jsonPath("$.data.rap[0].to", is("M5")))
				.andExpect(jsonPath("$.data.rap[0].fte", is(0.5)))
				.andExpect(jsonPath("$.data.rap[0].status", is("Planning")));
		verify(mockRapRepository, times(1)).findAllRapByProjectId(1L);
	}
	
	@Test
	public void listRAP_Ok_n() throws Exception {
		List<Rap> listRap = Arrays.asList(
				new Rap(1l, 1l, 1l, "NamDN3", "M1", "M5", 0.5f, "Planning", null, null, false),
				new Rap(2l, 1l, 2l, "TrieuTV2", "M1", "M3", 0.2f, "Planning", null, null, false));
		
		when(mockRapRepository.findAllRapByProjectId(1l)).thenReturn(listRap);
		mockMvc.perform(get("/listRAP/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.rap", hasSize(2)))
				.andExpect(jsonPath("$.data.rap[0].accountName", is("NamDN3")))
				.andExpect(jsonPath("$.data.rap[0].from", is("M1")))
				.andExpect(jsonPath("$.data.rap[0].to", is("M5")))
				.andExpect(jsonPath("$.data.rap[0].fte", is(0.5)))
				.andExpect(jsonPath("$.data.rap[0].status", is("Planning")))
				
				.andExpect(jsonPath("$.data.rap[1].accountName", is("TrieuTV2")))
				.andExpect(jsonPath("$.data.rap[1].from", is("M1")))
				.andExpect(jsonPath("$.data.rap[1].to", is("M3")))
				.andExpect(jsonPath("$.data.rap[1].fte", is(0.2)))
				.andExpect(jsonPath("$.data.rap[1].status", is("Planning")));
		verify(mockRapRepository, times(1)).findAllRapByProjectId(1L);
	}
	
	@Test
	public void listRAP_EntryData() throws Exception {
		List<Rap> listRap = Arrays.asList();
		when(mockRapRepository.findAllRapByProjectId(1l)).thenReturn(listRap);
		mockMvc.perform(get("/listRAP/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status", is(404)))
				.andExpect(jsonPath("$.error", is("Not Found")));
		verify(mockRapRepository, times(1)).findAllRapByProjectId(1L);
	}
	
	@Test
	public void listRFR_IsNotFormat() throws Exception {
		List<Rap> listRap = Arrays.asList();
		when(mockRapRepository.findAllRapByProjectId(1l)).thenReturn(listRap);
		mockMvc.perform(get("/listRAP/@")).andExpect(status().is(400));
	}
}
