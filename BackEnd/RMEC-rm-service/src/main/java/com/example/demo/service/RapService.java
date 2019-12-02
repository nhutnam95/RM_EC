package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.RequestRap;
import com.example.demo.error.FteWrongFormatException;
import com.example.demo.error.IdProjectWrongFormatException;
import com.example.demo.error.IdRfrWrongFormatExceptions;
import com.example.demo.error.ProjectNotFound;
import com.example.demo.error.RFRNotFoundException;
import com.example.demo.error.RequestWrongFormat;
import com.example.demo.model.Rap;
import com.example.demo.repository.RapRepository;
import com.example.demo.repository.SaveDAO;
import java.util.List;

@Service
public class RapService {
	@Autowired
	ProjectService projectService;
	@Autowired
	RfrService rfrService;
	@Autowired
	RapRepository rapRepository;
	@Autowired 
	SaveDAO saveRapDao;
	@Transactional(rollbackFor = Exception.class)
	public void saveRAP(RequestRap requestRap) throws RFRNotFoundException,ProjectNotFound,IdRfrWrongFormatExceptions,RequestWrongFormat,FteWrongFormatException {
		Long idProject = 0l;
	
		ArrayList<Long> listIdRap = new ArrayList<Long>();
		try {
			idProject = Long.valueOf(requestRap.getProject_id());
		} catch (Exception e) {
			throw new IdProjectWrongFormatException();
		}
		if(!projectService.checkProjectExist(idProject)) {
			throw new ProjectNotFound(idProject);
		}
		for(int i = 0 ; i<requestRap.getRap().size();i++) {
			float FTE = 0l;
			Long idRfr = 0l;
			if(requestRap.getRap().get(i).size() != 6) {
				throw new RequestWrongFormat();
			}
			try {
				 idRfr =  Long.valueOf(requestRap.getRap().get(i).get(0));
			} catch (Exception e) {
				throw new IdRfrWrongFormatExceptions(requestRap.getRap().get(i).get(0));
			}
			String account = requestRap.getRap().get(i).get(1);
			String from = requestRap.getRap().get(i).get(2);
			String to = requestRap.getRap().get(i).get(3);
			try {
				 FTE = Float.valueOf(requestRap.getRap().get(i).get(4)); 
			} catch (Exception e) {
				throw new FteWrongFormatException();
			}
		
			String status = requestRap.getRap().get(i).get(5);
			
			if(!rfrService.checkRfrExist(idRfr)) {
				throw new RFRNotFoundException(idRfr);
			}
			
			if(rapRepository.findByRfrIdAndAccountName(idRfr, account).isPresent()) {
				//update
				Rap rap =  rapRepository.findByRfrIdAndAccountName(idRfr, account).get();
				rap.setFrom(from);
				rap.setTo(to);
				rap.setFte(FTE);
				rap.setRapUpdateDate(LocalDate.now());
				rap.setStatus(status);
				Long idRap = saveRapDao.insertRAP_new(rap);
				listIdRap.add(idRap);
			} else {
				//insert
				Rap rap = new Rap();
				rap.setRfrId(idRfr);
				rap.setProjectId(idProject);
				rap.setAccountName(account);
				rap.setFrom(from);
				rap.setTo(to);
				rap.setRapCreateDate(LocalDate.now());
				rap.setRapUpdateDate(LocalDate.now());
				rap.setFte(FTE);
				rap.setStatus(status);
				rap.setRapDelFlag(false);
				Long idRap = saveRapDao.insertRAP_new(rap);
				listIdRap.add(idRap);
			}
		}
		rapRepository.updateListRapOfProject(idProject, listIdRap);
	}

	public List<Rap> findAllRapByProjectId(Long projectId) {
		List<Rap> listRap = rapRepository.findAllRapByProjectId(projectId);
		return listRap;

	}
	public List<Rap> findAllRapByProjectId_AvailableStatus(Long projectId) {
		List<Rap> rawList = rapRepository.findAllRapByProjectId(projectId);
		List<Rap> listRap = new ArrayList<Rap>();
		for (int i = 0; i < rawList.size(); i++) {
			if(rawList.get(i).getStatus().equals("Available")) {
				listRap.add(rawList.get(i));
			}
		}
		return listRap;

	}
}
