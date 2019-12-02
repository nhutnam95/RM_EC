package com.example.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.common.ApiResponse;
import com.example.demo.error.IdProjectWrongFormatException;
import com.example.demo.error.IdRequestFailsException;
import com.example.demo.error.ProjectNotFound;
import com.example.demo.error.RFRNotFoundException;
import com.example.demo.error.RequestWrongFormat;
import com.example.demo.dto.Rfr_New_FE_Dto;
import com.example.demo.mapper.RfrMapper;
import com.example.demo.model.Projects;
import com.example.demo.model.RfrEffortMonth;
import com.example.demo.model.Rfr_New;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.RfrEffortMonthRepository;
import com.example.demo.repository.Rfr_New_Repository;
import com.example.demo.repository.SaveDAO;

@Service
public class RfrService{
	@Autowired
	private Rfr_New_Repository rfr_New_Repository;
	@Autowired
	private RfrEffortMonthRepository rfrEffortMonthRepository;
//	@Autowired
//	private SaveRFRDAO saveRFRDAO;
	@Autowired
	private RfrEffortMonthService rfrEffortMonthService;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired 
	private SaveDAO saveRFRDAO;
	@Autowired 
	ProjectService projectService;
	@Autowired
	private RfrMapper rfrMapper;

	public List<Rfr_New> findAllRfr_NewByProjectId(Long projectId) {
		//Retrieve Rfr list by projectId from repository
		List<Rfr_New> listRfr_New = rfr_New_Repository.findAllRfr_NewByProjectId(projectId);
		//Add rfrEffortMonth property to each rfr
		if (listRfr_New != null) {
			for (int i = 0; i < listRfr_New.size(); i++) {
				listRfr_New.get(i).setRfrEffortMonths(rfrEffortMonthService.
						findAllRfrEffortMonthByRfrId_JsonObjectType(listRfr_New.get(i).getRfrId()));
			}
		}
		return listRfr_New;
	}

	public List<RfrEffortMonth> findAllRfrEffortMonthByRfrId(Long rftId) {
		List<RfrEffortMonth> listRfrEffortMonth = rfrEffortMonthRepository.findAllRfrEffortMonthByRfrId(rftId);
		return listRfrEffortMonth;
	}

//	@Transactional(rollbackFor = Exception.class)
//	public void submitRFRQuickAdd(RequestSubmitRFRQuickAdd requestSubmitRFRQuickAdd)
//			throws DateRFROutBoundsProjectException {
//
//		ReportRFRLog reportRFR = new ReportRFRLog();
//		LocalDate pF = projectRepository.findProjectByProjectId(requestSubmitRFRQuickAdd.getProject_id())
//				.getProjectFrom();
//		LocalDate pT = projectRepository.findProjectByProjectId(requestSubmitRFRQuickAdd.getProject_id())
//				.getProjectTo();
//
//		long monthofProject = ChronoUnit.MONTHS.between(pF, pT) + 1;
//		for (int i = 0; i < requestSubmitRFRQuickAdd.getRfr().size(); i++) {
////	    	if(rfrRepository.findById(requestSubmitRFRQuickAdd.getRfr().get(i).getId()).isPresent()) {
////	    		updateRFR(requestSubmitRFRQuickAdd,reportRFR,pF,pT,monthofProject,i);
////			} else {
//
//			createRFR(requestSubmitRFRQuickAdd, reportRFR, pF, pT, monthofProject, i);
////			};
//
//		}
//	}

//	private void updateRFR(RequestSubmitRFRQuickAdd requestSubmitRFRQuickAdd, ReportRFRLog reportRFR, LocalDate pF,
//			LocalDate pT, long monthofProject, int i) {
//		// TODO Auto-generated method stub
//		Rfr rfr = rfrRepository.findById(requestSubmitRFRQuickAdd.getRfr().get(i).getId()).get();
//		LocalDate rfrFrom = requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(0);
//		LocalDate rfrTo = requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(1);
//		LocalDate monthOfRFREffort;
//		if(rfrFrom.compareTo(pF) == -1 || rfrTo.compareTo(pT) == 1 ) {
//			throw new DateRFROutBoundsProjectException(1L);
//		}
//		long	roleIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getRole_id();
//		long rankIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getRank_id();
//		long	skillIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getSkill_id();
//		long locationIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getLocation_id();
//	
//		rfr.setRfrId(requestSubmitRFRQuickAdd.getRfr().get(i).getId());
//		rfr.setProjectId(requestSubmitRFRQuickAdd.getProject_id());
//		rfr.setRoleId(requestSubmitRFRQuickAdd.getRfr().get(i).getRole_id());
//		rfr.setRankId(requestSubmitRFRQuickAdd.getRfr().get(i).getRank_id());
//		rfr.setSkillId(requestSubmitRFRQuickAdd.getRfr().get(i).getSkill_id());
//		rfr.setLocationId(requestSubmitRFRQuickAdd.getRfr().get(i).getLocation_id());
//		rfr.setUpdateDate(LocalDate.now());
//		rfr.setDelFlag(false);
//		
//		rfrRepository.save(rfr);
//		
////		 Insert ReportBetwProjectLog
//	// insert report
//	List<HashMap<String, String>> report = new LinkedList<HashMap<String, String>>();
//	HashMap<String, String> rReport = new HashMap<String, String>();
//	rReport.put("id_rfr", String.valueOf(rfr.getRfrId()));
//	rReport.put("role_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getRole_id()));
//	rReport.put("rank_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getRank_id()));
//	rReport.put("skill_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getSkill_id()));
//	rReport.put("location_id",String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getLocation_id()));
//	ArrayList<String> mm = new ArrayList<String>();
//	mm.add(String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(0)));
//	mm.add(String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(1)));
//	rReport.put("m1mn",String.valueOf(mm));
//	report.add(rReport);
//	
//	// insert role
//	List<HashMap<String, String>> role = new LinkedList<HashMap<String, String>>();
//	HashMap<String, String> rRow = new HashMap<String, String>();
//	rRow.put("ID", String.valueOf(roleIdRequestRFR));
//	rRow.put("name", String.valueOf(roleRepository.findRoleByRoleId(roleIdRequestRFR).getRoleName()));
//	role.add(rRow);
//	// insert rank
//	List<HashMap<String, String>> rank = new LinkedList<HashMap<String, String>>();
//	HashMap<String, String> rRank = new HashMap<String, String>();
//	rRank.put("ID", String.valueOf(rankIdRequestRFR));
//	rRank.put("name", String.valueOf(rankRepository.findRankByRankId(rankIdRequestRFR).getRankName()));
//	rank.add(rRank);
//	// insert skill
//	List<HashMap<String, String>> skill = new LinkedList<HashMap<String, String>>();
//	HashMap<String, String> rSkill = new HashMap<String, String>();
//	rSkill.put("ID", String.valueOf(skillIdRequestRFR));
//	rSkill.put("name", String.valueOf(skillRepository.findById(skillIdRequestRFR).get().getSkillName()));
//	skill.add(rSkill);
//	// insert location
//	List<HashMap<String, String>> location = new LinkedList<HashMap<String, String>>();
//	HashMap<String, String> rLocation = new HashMap<String, String>();
//	rLocation.put("ID", String.valueOf(locationIdRequestRFR));
//	rLocation.put("name", String.valueOf(locationRepository.findById(locationIdRequestRFR).get().getLocationName()));
//	location.add(rLocation);
//	//insert ReportRFRLog
//	reportRFR.setReport(report);
//	reportRFR.setRole(role);
//	reportRFR.setRank(rank);
//	reportRFR.setSkill(skill);
//	reportRFR.setLocation(location);
//	
//	 ObjectMapper Obj = new ObjectMapper(); 
//	 try {
//		String jsonStr = Obj.writeValueAsString(reportRFR);
//		ReportbetwprojectLog reportbetwprojectLog = new ReportbetwprojectLog();
//		reportbetwprojectLog.setProjectId(requestSubmitRFRQuickAdd.getProject_id());
//		reportbetwprojectLog.setReportData(jsonStr);
//		reportbetwprojectLog.setReportCreateDate(LocalDate.now());
//		reportbetwprojectLogRepository.save(reportbetwprojectLog);
//	} catch (JsonProcessingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 
//	 monthOfRFREffort =pF;
//	 
//	 
//	 for(int j = 0; j<monthofProject;j++) {
//		 
//	 }
//	 
//	}
//	@Transactional(rollbackFor = Exception.class)
//	public void createRFR(RequestSubmitRFRQuickAdd requestSubmitRFRQuickAdd, ReportRFRLog reportRFR, LocalDate pF,
//			LocalDate pT, long monthofProject, int i) throws DateRFROutBoundsProjectException {
//
//		Rfr rfr = new Rfr();
//		LocalDate rfrFrom = requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(0);
//		LocalDate rfrTo = requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(1);
//		LocalDate monthOfRFREffort;
//
//		if (rfrFrom.compareTo(pF)  < 0 || rfrTo.compareTo(pT) > 0) {
//			throw new DateRFROutBoundsProjectException((long) i);
//		}
//		if (rfrFrom.compareTo(rfrTo) > 0) {
//			throw new DateRFROutBoundsProjectException((long) i);
//		}
//		long roleIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getRole_id();
//		long rankIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getRank_id();
//		long skillIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getSkill_id();
//		long locationIdRequestRFR = requestSubmitRFRQuickAdd.getRfr().get(i).getLocation_id();
//
//		// RFR
//		rfr.setProjectId(requestSubmitRFRQuickAdd.getProject_id());
//		rfr.setRoleId(requestSubmitRFRQuickAdd.getRfr().get(i).getRole_id());
//		rfr.setRankId(requestSubmitRFRQuickAdd.getRfr().get(i).getRank_id());
//		rfr.setSkillId(requestSubmitRFRQuickAdd.getRfr().get(i).getSkill_id());
//		rfr.setLocationId(requestSubmitRFRQuickAdd.getRfr().get(i).getLocation_id());
//		rfr.setCreateDate(LocalDate.now());
//		rfr.setUpdateDate(LocalDate.now());
//		rfr.setDelFlag(false);
//
//		long idRFR = saveRFRDAO.insertRFR(rfr);
//
////			 Insert ReportBetwProjectLog
//		// insert report
//		List<HashMap<String, String>> report = new LinkedList<HashMap<String, String>>();
//		HashMap<String, String> rReport = new HashMap<String, String>();
//		rReport.put("id_rfr", String.valueOf(idRFR));
//		rReport.put("role_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getRole_id()));
//		rReport.put("rank_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getRank_id()));
//		rReport.put("skill_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getSkill_id()));
//		rReport.put("location_id", String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getLocation_id()));
//		ArrayList<String> mm = new ArrayList<String>();
//		mm.add(String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(0)));
//		mm.add(String.valueOf(requestSubmitRFRQuickAdd.getRfr().get(i).getFrom_to().get(1)));
//		rReport.put("m1mn", String.valueOf(mm));
//		report.add(rReport);
//
//		// insert role
//		List<HashMap<String, String>> role = new LinkedList<HashMap<String, String>>();
//		HashMap<String, String> rRow = new HashMap<String, String>();
//		rRow.put("ID", String.valueOf(roleIdRequestRFR));
//		roleRepository.findById(roleIdRequestRFR).orElseThrow(() -> new RFRRoleIdNotFound());
//		rRow.put("name", String.valueOf(roleRepository.findById(roleIdRequestRFR).get().getRoleName()));
//		role.add(rRow);
//		// insert rank
//		List<HashMap<String, String>> rank = new LinkedList<HashMap<String, String>>();
//		HashMap<String, String> rRank = new HashMap<String, String>();
//		rRank.put("ID", String.valueOf(rankIdRequestRFR));
//		rankRepository.findById(rankIdRequestRFR).orElseThrow(() -> new RFRRankIdNotFound());
//		rRank.put("name", String.valueOf(rankRepository.findById(rankIdRequestRFR).get().getRankName()));
//		rank.add(rRank);
//		// insert skill
//		List<HashMap<String, String>> skill = new LinkedList<HashMap<String, String>>();
//		HashMap<String, String> rSkill = new HashMap<String, String>();
//		rSkill.put("ID", String.valueOf(skillIdRequestRFR));
//		skillRepository.findById(skillIdRequestRFR).orElseThrow(() -> new RFRSkillIdNotFound());
//		rSkill.put("name", String.valueOf(skillRepository.findById(skillIdRequestRFR).get().getSkillName()));
//		skill.add(rSkill);
//		// insert location
//		List<HashMap<String, String>> location = new LinkedList<HashMap<String, String>>();
//		HashMap<String, String> rLocation = new HashMap<String, String>();
//		rLocation.put("ID", String.valueOf(locationIdRequestRFR));
//		locationRepository.findById(locationIdRequestRFR).orElseThrow(() -> new RFRLocationIdNotFound());
//		rLocation.put("name",
//				String.valueOf(locationRepository.findById(locationIdRequestRFR).get().getLocationName()));
//		location.add(rLocation);
//		// insert ReportRFRLog
//		reportRFR.setReport(report);
//		reportRFR.setRole(role);
//		reportRFR.setRank(rank);
//		reportRFR.setSkill(skill);
//		reportRFR.setLocation(location);
//
//		ObjectMapper Obj = new ObjectMapper();
//		try {
//			String jsonStr = Obj.writeValueAsString(reportRFR);
//			ReportbetwprojectLog reportbetwprojectLog = new ReportbetwprojectLog();
//			reportbetwprojectLog.setProjectId(requestSubmitRFRQuickAdd.getProject_id());
//			reportbetwprojectLog.setReportData(jsonStr);
//			reportbetwprojectLog.setReportCreateDate(LocalDate.now());
//			reportbetwprojectLogRepository.save(reportbetwprojectLog);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//			monthOfRFREffort = pF;
//		
//		
//		for (int j = 0; j < monthofProject; j++) {
//			// RFREfotMonth
//
//			// TH dac biet RFRfrom > PF
//			RfrEffortMonth rfrEffortMonth = new RfrEffortMonth();
//			if ((monthOfRFREffort.getYear() == rfrFrom.getYear() && monthOfRFREffort.getMonthValue() < rfrFrom.getMonthValue()) || (monthOfRFREffort.getYear() < rfrFrom.getYear())) {
//
////				rfrEffortMonth.setRfrId(requestSubmitRFRQuickAdd.getRfr().get(i).getId());
//				rfrEffortMonth.setRfrId(idRFR);
//				rfrEffortMonth.setEffortName("m"+(j+1));
//				rfrEffortMonth.setMonth(monthOfRFREffort);
//				rfrEffortMonth.setEffort(0);
//
//				rfrEffortMonthRepository.save(rfrEffortMonth);
//				monthOfRFREffort = monthOfRFREffort.plusMonths(1);
//
//				continue;
//			}
//			// TH dac biet RFRto < PT
//			if ((monthOfRFREffort.getYear() == rfrTo.getYear() && monthOfRFREffort.getMonthValue() > rfrTo.getMonthValue()) || (monthOfRFREffort.getYear() > rfrTo.getYear()) ) {
//
////				rfrEffortMonth.setRfrId(requestSubmitRFRQuickAdd.getRfr().get(i).getId());
//				rfrEffortMonth.setRfrId(idRFR);
//				rfrEffortMonth.setEffortName("m"+(j+1));
//				rfrEffortMonth.setMonth(monthOfRFREffort);
//				rfrEffortMonth.setEffort(0);
//
//				rfrEffortMonthRepository.save(rfrEffortMonth);
//				monthOfRFREffort = monthOfRFREffort.plusMonths(1);
//
//				continue;
//			}
//		
//			// thang dau
//			if (monthOfRFREffort.getYear() == rfrFrom.getYear()
//					&& monthOfRFREffort.getMonthValue() == rfrFrom.getMonthValue()) {
//				float value = (((float) rfrFrom.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth()
//						- (float) rfrFrom.getDayOfMonth())
//						/ ((float) rfrFrom.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth()))
//						* ((float) requestSubmitRFRQuickAdd.getRfr().get(i).getQuantity());
//
////				 value = (float) Math.round(value * 100) / 100;
////				rfrEffortMonth.setRfrId(requestSubmitRFRQuickAdd.getRfr().get(i).getId());
//				rfrEffortMonth.setRfrId(idRFR);
//				rfrEffortMonth.setEffortName("m"+(j+1));
//				rfrEffortMonth.setMonth(monthOfRFREffort);
//				rfrEffortMonth.setEffort(value);
//
//				rfrEffortMonthRepository.save(rfrEffortMonth);
//				monthOfRFREffort = monthOfRFREffort.plusMonths(1);
//				continue;
//			}
//			// thang cuoi
//			if (monthOfRFREffort.getYear() == rfrTo.getYear()
//					&& monthOfRFREffort.getMonthValue() == rfrTo.getMonthValue()) {
//				float value = ((float) rfrTo.getDayOfMonth()
//						/ ((float) rfrTo.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth()))
//						* ((float) requestSubmitRFRQuickAdd.getRfr().get(i).getQuantity());
////				 value = (float) Math.round(value * 100) / 100;
////				rfrEffortMonth.setRfrId(requestSubmitRFRQuickAdd.getRfr().get(i).getId());
//				rfrEffortMonth.setRfrId(idRFR);
//				rfrEffortMonth.setEffortName("m"+(j+1));
//				rfrEffortMonth.setMonth(monthOfRFREffort);
//				rfrEffortMonth.setEffort(value);
//				rfrEffortMonthRepository.save(rfrEffortMonth);
//				monthOfRFREffort = monthOfRFREffort.plusMonths(1);
//				continue;
//			}
//			
//			if(monthOfRFREffort.compareTo(rfrFrom) > 0 && monthOfRFREffort.compareTo(rfrTo) <0) {
////				rfrEffortMonth.setRfrId(requestSubmitRFRQuickAdd.getRfr().get(i).getId());
//				rfrEffortMonth.setRfrId(idRFR);
//				rfrEffortMonth.setEffortName("m"+(j+1));
//				rfrEffortMonth.setMonth(monthOfRFREffort);
//				rfrEffortMonth.setEffort(1);
//				rfrEffortMonthRepository.save(rfrEffortMonth);
//				monthOfRFREffort = monthOfRFREffort.plusMonths(1);
//				continue;
//			}
//
//		}
//	}
	@Transactional(rollbackFor = Exception.class)
	public ApiResponse<String> saveRFRbyProjectId(Map<String, Object> rfrByProjectId) throws  RequestWrongFormat,IdRequestFailsException,RFRNotFoundException,ProjectNotFound,IdProjectWrongFormatException{
		long idProject = 0;
		try {
			 idProject=Long.parseLong((String) rfrByProjectId.get("project_id"));
		} catch (Exception e) {
			// TODO: handle exception
			if(e.getMessage().contains("For input string:")) {
				throw new IdProjectWrongFormatException();
			}
			if("null".equals(e.getMessage())) {
				throw new RequestWrongFormat();
			}
		}
		
		// Kiem tra Project theo id co ton tai khong, neu khong tra ve Exception
		if( !projectService.checkProjectExist(idProject)) {
			throw new ProjectNotFound(idProject);
		}
		List<List<String>> listRFR =  ((ArrayList) rfrByProjectId.get("rfr"));
		if(listRFR == null) {
			throw  new RequestWrongFormat();
		}
		//get project by id
		Projects project= projectRepository.findById(idProject).get();
		LocalDate pF = project.getProjectFrom();
		LocalDate pT = project.getProjectTo();
		ArrayList<Long> listIdRfr = new ArrayList<Long>();
		
		long monthofProject = ChronoUnit.MONTHS.between(pF.withDayOfMonth(1), pT.withDayOfMonth(1)) + 1;
		
		//check RFR
		for( int i =0 ; i < listRFR.size();i++) {
			if(listRFR.get(i).size() != (5+monthofProject)) {
				throw new RequestWrongFormat();
			}
			String   idRequest = String.valueOf(listRFR.get(i).get(0));
			// check id so nguyen or null
			if(!( isNumber(idRequest) || idRequest.equals("null")) ) {
				throw new IdRequestFailsException();
			}
			String roleRequest = listRFR.get(i).get(1);
			String rankRequest = listRFR.get(i).get(2);
			String skillRequest =listRFR.get(i).get(3);
			String locationRequest =listRFR.get(i).get(4);
			
			if(idRequest.equals("null")) {
				// insert
				Rfr_New rfr = new Rfr_New();
				rfr.setProjectId(idProject);
				rfr.setRoleName(roleRequest);
				rfr.setRankName(rankRequest);
				rfr.setSkillName(skillRequest);
				rfr.setLocationName(locationRequest);	
				rfr.setCreateDate(LocalDate.now());
				rfr.setUpdateDate(LocalDate.now());
				rfr_New_Repository.save(rfr);
				long idRFR = saveRFRDAO.insertRFR_new(rfr);
				listIdRfr.add(idRFR);
				insertRfrEffortMonth(idRFR,monthofProject,listRFR,i);
				
				continue;
			}
			// so nguyen va ton tai
			if( checkRfrExist(Long.valueOf(idRequest))) {
				//update rfr
				Rfr_New rfrUpdate = rfr_New_Repository.findById(Long.valueOf(idRequest)).get();
				rfrUpdate.setRfrId(Long.valueOf(idRequest));
				rfrUpdate.setProjectId(idProject);
				rfrUpdate.setRoleName(roleRequest);
				rfrUpdate.setRankName(rankRequest);
				rfrUpdate.setSkillName(skillRequest);
				rfrUpdate.setLocationName(locationRequest);
				rfrUpdate.setCreateDate(LocalDate.now());
				rfrUpdate.setUpdateDate(LocalDate.now());
				rfr_New_Repository.save(rfrUpdate);
				listIdRfr.add(Long.valueOf(idRequest));
				// update RFR EfortMonth
				updateRfrEffortMonth(idRequest,monthofProject,listRFR,i);
				continue;
			}  else {
				throw new RFRNotFoundException(Long.valueOf(idRequest));
			}
			
			// chi chap nhan so nguyen hoac null
			
		}
			rfr_New_Repository.updateListRfrOfProject(idProject, listIdRfr);
		return new ApiResponse<String>(HttpStatus.OK);
	}
	public boolean checkRfrExist(Long id) {
		if ((rfr_New_Repository.existsById(id))&&(!rfr_New_Repository.checkDelFlagRfrById(id))) {
			return true;
		}
		return false;
	}



	private void updateRfrEffortMonth(String idRequest, long monthofProject, List<List<String>> listRFR, int i) {
		// TODO Auto-generated method stub
		for(int k =0;k<monthofProject;k++) {
			// if ton tai
			if(rfrEffortMonthRepository.findByRfrIdAndEffortName(Long.valueOf(idRequest), "m"+(k+1)).isPresent()) {
				RfrEffortMonth efrEffortMonth = rfrEffortMonthRepository.findByRfrIdAndEffortName(Long.valueOf(idRequest), "m"+(k+1)).get();
				try {
					if("null".equals(String.valueOf(listRFR.get(i).get(k+5)))) {
						efrEffortMonth.setEffortName("m"+(k+1));
						efrEffortMonth.setEffort(0);
						rfrEffortMonthRepository.save(efrEffortMonth);
						continue;
					}
					float m  = Float.valueOf(String.valueOf(listRFR.get(i).get(k+5)));
					if(m <0) {
						throw new RequestWrongFormat();
					}
					efrEffortMonth.setEffort(m);
				} catch (Exception e) {
					// is "null"
					throw new RequestWrongFormat();
				}
				rfrEffortMonthRepository.save(efrEffortMonth);
			} else {
				RfrEffortMonth efrEffortMonth = new RfrEffortMonth();
				
				try {
					if("null".equals(String.valueOf(listRFR.get(i).get(k+5)))) {
						efrEffortMonth.setEffortName("m"+(k+1));
						efrEffortMonth.setEffort(0);
						rfrEffortMonthRepository.save(efrEffortMonth);
						continue;
					}
					float m  = Float.valueOf(String.valueOf(listRFR.get(i).get(k+5)));
					if(m <0) {
						throw new RequestWrongFormat();
					}
					efrEffortMonth.setEffort(m);
				} catch (Exception e) {
					// TODO: handle exception
					throw new RequestWrongFormat();
				}
				efrEffortMonth.setRfrId(Long.valueOf(idRequest));
				efrEffortMonth.setEffortName("m"+(k+1));
				rfrEffortMonthRepository.save(efrEffortMonth);
			}
		
		}
	}

	private void insertRfrEffortMonth(Long rfrId, long monthofProject, List<List<String>> listRFR, int i) {
		// TODO Auto-generated method stub
		for(int j =0; j< monthofProject;j++) {
			RfrEffortMonth efrEffortMonth = new RfrEffortMonth();
			efrEffortMonth.setRfrId(rfrId);
			
			try {
				if("null".equals(String.valueOf(listRFR.get(i).get(j+5)))) {
					
					efrEffortMonth.setEffortName("m"+(j+1));
					rfrEffortMonthRepository.save(efrEffortMonth);
					efrEffortMonth.setEffort(0);
					continue;
				}
				float m  = Float.valueOf(String.valueOf(listRFR.get(i).get(j+5)));
				if(m <0) {
					throw new RequestWrongFormat();
				}
				efrEffortMonth.setEffort(m);
			} catch (Exception e) {
				// TODO: handle exception
				throw new RequestWrongFormat();
			}
		
			efrEffortMonth.setEffortName("m"+(j+1));
			rfrEffortMonthRepository.save(efrEffortMonth);
		}
	}


 
		private boolean isNumber(String idRequest) {
				try {
					Integer id = Integer.parseInt(idRequest);
					if(id<0) {
						return false;
					}
			    } catch (NumberFormatException | NullPointerException nfe) {
			        return false;
			    }
			    return true;
			}
		public ApiResponse<Map<Object,Object>> findAllRfr_ApiMapType(String projectId){
			//check valid projectId value
			Long id;
			try {
				id = Long.parseLong(projectId);
			}
			catch (NullPointerException e) {
				return new ApiResponse<>(HttpStatus.BAD_REQUEST);
			}
			catch (NumberFormatException e) {
				return new ApiResponse<>(HttpStatus.BAD_REQUEST);
			}
			if(id<=0) {
				return new ApiResponse<>(HttpStatus.BAD_REQUEST);
			}
			// Retrieve list of Rfr from database
			List<Rfr_New> listRfr_New = findAllRfr_NewByProjectId(id);
			// Check null of Rfr's list, if it's null release a 404 Response
			if (listRfr_New.isEmpty()) {
				return new ApiResponse<>(HttpStatus.NOT_FOUND);
			}
			// convert list of Rfr model to DTO
			List<Rfr_New_FE_Dto> listRfr_New_FE_Dto = listRfr_New.stream().map(rfrMapper::rfr_NewToRfr_New_FE_Dto)
					.collect(Collectors.toList());
			// Use a HashMap variable to store data and response to front-end side
			Map<Object,Object> listMapAPI=new HashMap(); 
			listMapAPI.put("rfr", listRfr_New_FE_Dto);
			return new ApiResponse<>(listMapAPI, HttpStatus.OK);
			
		}
}
