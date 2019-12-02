package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import com.example.demo.common.ApiResponse;
import com.example.demo.dto.RfrEffortMonthDto;
import com.example.demo.error.IdRequestFailsException;
import com.example.demo.error.ProjectNotFound;
import com.example.demo.error.RFRNotFoundException;
import com.example.demo.model.Projects;
import com.example.demo.model.RfrEffortMonth;
import com.example.demo.model.Rfr_New;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.RfrEffortMonthRepository;
import com.example.demo.repository.Rfr_New_Repository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.RfrService;

@RunWith(MockitoJUnitRunner.class)
public class SaveRFR_UnitTest {
	@Mock
	Rfr_New_Repository rfr_New_Repository;
	@Mock
	ProjectRepository projectRepository;
	@Mock 
	ProjectService projectService;
	@Mock
	RfrEffortMonthRepository rfrEffortMonthRepository;
	@InjectMocks
	RfrService rfrService;
	@Test(expected = RFRNotFoundException.class)
	public void testSaveRFR_notExistRfrId() throws IdRequestFailsException, RFRNotFoundException, ProjectNotFound{
		LocalDate date = LocalDate.of(2019, 01, 01);
		LocalDate date2 = LocalDate.of(2019, 02, 01);
		Projects project = new Projects(1L, "project1", date, date2, null, null, false);
		Optional<Projects> project_optional= Optional.of(project);
		when(projectService.checkProjectExist(1L)).thenReturn(true);
		when(projectRepository.findById(1L)).thenReturn(project_optional);
		Map<String,Object> requestData = new HashMap<String, Object>();
		requestData.put("project_id", "1");
		List<String> list1= new ArrayList<String>();
		list1.add("1");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("1");
		list1.add("2");
		List<List<String>> listAll= new ArrayList<List<String>>();
		listAll.add(list1);
		requestData.put("rfr", listAll);
		rfrService.saveRFRbyProjectId(requestData);
	}
	@Test(expected = ProjectNotFound.class)
	public void testSaveRFR_notExistProjectId() throws IdRequestFailsException, RFRNotFoundException, ProjectNotFound{
		when(projectService.checkProjectExist(99L)).thenReturn(false);
		Map<String,Object> requestData = new HashMap<String, Object>();
		requestData.put("project_id", "99");
		List<String> list1= new ArrayList<String>();
		list1.add("1");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("1");
		list1.add("2");
		List<List<String>> listAll= new ArrayList<List<String>>();
		listAll.add(list1);
		requestData.put("rfr", listAll);
		rfrService.saveRFRbyProjectId(requestData);
	}
	@Test
	public void testSaveRFR_CorrectResponseWith_1_RfrElement() throws IdRequestFailsException, ProjectNotFound, RFRNotFoundException {
		LocalDate date = LocalDate.of(2019, 01, 01);
		LocalDate date2 = LocalDate.of(2019, 02, 01);
		Projects project = new Projects(1L, "project1", date, date2, null, null, false);
		RfrEffortMonth rfrEffortMonth1= new RfrEffortMonth(1L, 1L, "m1", 3);
		RfrEffortMonth rfrEffortMonth2= new RfrEffortMonth(2L, 1L, "m2", 4);
		Optional<RfrEffortMonth> rfrE_OP1= Optional.of(rfrEffortMonth1);
		Optional<RfrEffortMonth> rfrE_OP2= Optional.of(rfrEffortMonth2);
		Optional<Projects> project_optional= Optional.of(project);
		Rfr_New rfr_New = new Rfr_New(1L, 1L, "roleName", "rankName", "skillName", "locationName", null, null, false, null);
		Optional<Rfr_New> rfr_New_optional= Optional.of(rfr_New);
		when(projectService.checkProjectExist(1L)).thenReturn(true);
		when(projectRepository.findById(1L)).thenReturn(project_optional);
		when(rfrService.checkRfrExist(1L)).thenReturn(true);
		when(rfr_New_Repository.findById(1L)).thenReturn(rfr_New_optional);
		when(rfrEffortMonthRepository.findByRfrIdAndEffortName(1L, "m1")).thenReturn(rfrE_OP1);
		when(rfrEffortMonthRepository.findByRfrIdAndEffortName(1L, "m2")).thenReturn(rfrE_OP2);
		Map<String,Object> requestData = new HashMap<String, Object>();
		requestData.put("project_id", "1");
		List<String> list1= new ArrayList<String>();
		list1.add("1");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("1");
		list1.add("2");
		List<List<String>> listAll= new ArrayList<List<String>>();
		listAll.add(list1);
		requestData.put("rfr", listAll);
		assertEquals(new ApiResponse<String>(HttpStatus.OK), rfrService.saveRFRbyProjectId(requestData));
	}
	@Test
	public void testSaveRFR_CorrectResponseWith_N_RfrElement() throws IdRequestFailsException, ProjectNotFound, RFRNotFoundException {
		LocalDate date = LocalDate.of(2019, 01, 01);
		LocalDate date2 = LocalDate.of(2019, 02, 01);
		Projects project = new Projects(1L, "project1", date, date2, null, null, false);
		Optional<Projects> project_optional= Optional.of(project);
		
		RfrEffortMonth rfrEffortMonth1= new RfrEffortMonth(1L, 1L, "m1", 3);
		RfrEffortMonth rfrEffortMonth2= new RfrEffortMonth(2L, 1L, "m2", 4);
		Optional<RfrEffortMonth> rfrE_OP1= Optional.of(rfrEffortMonth1);
		Optional<RfrEffortMonth> rfrE_OP2= Optional.of(rfrEffortMonth2);
		Rfr_New rfr_New = new Rfr_New(1L, 1L, "roleName", "rankName", "skillName", "locationName", null, null, false, null);
		Optional<Rfr_New> rfr_New_optional= Optional.of(rfr_New);
		
		RfrEffortMonth rfrEffortMonth1_2= new RfrEffortMonth(1L, 2L, "m1", 3);
		RfrEffortMonth rfrEffortMonth2_2= new RfrEffortMonth(2L, 2L, "m2", 4);
		Optional<RfrEffortMonth> rfrE_OP1_2= Optional.of(rfrEffortMonth1_2);
		Optional<RfrEffortMonth> rfrE_OP2_2= Optional.of(rfrEffortMonth2_2);
		Rfr_New rfr_New_2 = new Rfr_New(2L, 1L, "roleName", "rankName", "skillName", "locationName", null, null, false, null);
		Optional<Rfr_New> rfr_New_optional_2= Optional.of(rfr_New_2);
		
		when(projectService.checkProjectExist(1L)).thenReturn(true);
		when(projectRepository.findById(1L)).thenReturn(project_optional);
		when(rfrService.checkRfrExist(1L)).thenReturn(true);
		when(rfr_New_Repository.findById(1L)).thenReturn(rfr_New_optional);
		when(rfrEffortMonthRepository.findByRfrIdAndEffortName(1L, "m1")).thenReturn(rfrE_OP1);
		when(rfrEffortMonthRepository.findByRfrIdAndEffortName(1L, "m2")).thenReturn(rfrE_OP2);
		
		when(rfrService.checkRfrExist(2L)).thenReturn(true);
		when(rfr_New_Repository.findById(2L)).thenReturn(rfr_New_optional_2);
		when(rfrEffortMonthRepository.findByRfrIdAndEffortName(2L, "m1")).thenReturn(rfrE_OP1_2);
		when(rfrEffortMonthRepository.findByRfrIdAndEffortName(2L, "m2")).thenReturn(rfrE_OP2_2);
		Map<String,Object> requestData = new HashMap<String, Object>();
		requestData.put("project_id", "1");
		List<String> list1= new ArrayList<String>();
		list1.add("1");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("AAA");
		list1.add("1");
		list1.add("2");
		List<String> list2= new ArrayList<String>();
		list2.add("2");
		list2.add("AAA");
		list2.add("AAA");
		list2.add("AAA");
		list2.add("AAA");
		list2.add("1");
		list2.add("2");
		List<List<String>> listAll= new ArrayList<List<String>>();
		listAll.add(list1);
		listAll.add(list2);
		requestData.put("rfr", listAll);
		assertEquals(new ApiResponse<String>(HttpStatus.OK), rfrService.saveRFRbyProjectId(requestData));
	}
}
