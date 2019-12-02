package com.example.demo;

import com.example.demo.model.Projects;
import com.example.demo.repository.ProjectRepository;
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

import java.time.LocalDate;
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
public class ListProject_UnitTest {
	
	private static final ObjectMapper om = new ObjectMapper();
	@Autowired
    private MockMvc mockMvc;
	@MockBean
	ProjectRepository mockProjectRepository;

	@Test
	public void listProject_OK_1() throws Exception{
		List<Projects> listProject  = Arrays.asList( new Projects(1l, "RMEC",LocalDate.of(2019, 9, 1), LocalDate.of(2020, 2, 1), null, null, false));
		  when(mockProjectRepository.findAll()).thenReturn(listProject);
		  mockMvc.perform(get("/listProject")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))	 
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.data.projects",hasSize(1)))
		  .andExpect(jsonPath("$.data.projects[0].projectName", is("RMEC")))
		  .andExpect(jsonPath("$.data.projects[0].projectFrom", is("2019-09-01")))
		  .andExpect(jsonPath("$.data.projects[0].projectTo", is("2020-02-01")));
		  verify(mockProjectRepository, times(1)).findAll();
	}
	@Test
	public void listProject_OK_n() throws Exception{
		List<Projects> listProject  = Arrays.asList( new Projects(1l, "RMEC",LocalDate.of(2019, 9, 1), LocalDate.of(2020, 2, 1), null, null, false),
				new Projects(2l, "ASS",LocalDate.of(2019, 10, 1), LocalDate.of(2020, 3, 1), null, null, false));
		  when(mockProjectRepository.findAll()).thenReturn(listProject);
		  mockMvc.perform(get("/listProject")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))	 
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.data.projects",hasSize(2)))
		  .andExpect(jsonPath("$.data.projects[0].projectName", is("RMEC")))
		  .andExpect(jsonPath("$.data.projects[0].projectFrom", is("2019-09-01")))
		  .andExpect(jsonPath("$.data.projects[0].projectTo", is("2020-02-01")))
		  .andExpect(jsonPath("$.data.projects[1].projectName", is("ASS")))
		  .andExpect(jsonPath("$.data.projects[1].projectFrom", is("2019-10-01")))
		  .andExpect(jsonPath("$.data.projects[1].projectTo", is("2020-03-01")));
		  verify(mockProjectRepository, times(1)).findAll();
	  }
	@Test
	  public void listPropject_EntryData() throws Exception {
		  List<Projects> listProject  = Arrays.asList();
		  when(mockProjectRepository.findAll()).thenReturn(listProject);
		  mockMvc.perform(get("/listProject")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))	 
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.status", is(404)))
		  .andExpect(jsonPath("$.error", is("Not Found")));
		  verify(mockProjectRepository, times(1)).findAll();
	  }
	@Test
	  public void listPropject_Format_Error() throws Exception {
		  List<Projects> listProject  = Arrays.asList();
		  when(mockProjectRepository.findAll()).thenReturn(listProject);
		  mockMvc.perform(get("/@@@"))
		  .andExpect(status().is(400));
	  }
}
