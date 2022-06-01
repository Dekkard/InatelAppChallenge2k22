package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Installer;
import br.inatel.InternetProviderBrowser.model.Plan;
import br.inatel.InternetProviderBrowser.model.DTO.PlanDTO;
import br.inatel.InternetProviderBrowser.service.ClientService;
import br.inatel.InternetProviderBrowser.service.ContractService;
import br.inatel.InternetProviderBrowser.service.InstallerService;
import br.inatel.InternetProviderBrowser.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController implements ControllerModel<Plan, PlanDTO, Long> {

	@Autowired
	PlanService ps;
	@Autowired
	ClientService cs;
	@Autowired
	InstallerService is;
	@Autowired
	ContractService cos;

	@Override
	public ResponseEntity<List<PlanDTO>> getMappingMethod() {
		List<Plan> listPlan = ps.list();
//		listPlan.stream().forEach(p -> p.setInstaller(ps.findInstaller(p.getId())));
		List<PlanDTO> listPlanDTO = listPlan.stream()//
				.map(Plan::modeltoDTO)//
				.collect(Collectors.toList());
		if (!listPlanDTO.isEmpty())
			return new ResponseEntity<>(listPlanDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<PlanDTO> getByIdMappingMethod(Long id) {
		try {
			Plan plan = ps.find(id);
			plan.setInstaller(ps.findInstaller(plan.getId(), id));
			return new ResponseEntity<>(Plan.modeltoDTO(plan), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<PlanDTO> postMappingMethod(PlanDTO modelDTO) {
		Installer i = is.find(modelDTO.getInstallerId());
		Plan plan = PlanDTO.DTOtoModel(modelDTO);
		plan.setInstaller(i);
//		int ind = ps.updateInstallerId(plan.getId(), modelDTO.getInstallerId());
//		System.out.println((ind > 0 ? ind : "Nehuma") //
//				+ " entidade" + (ind > 1 ? "s" : "") //
//				+ " atualizada" + (ind > 1 ? "s" : ""));
		try {
			return new ResponseEntity<>(Plan.modeltoDTO(ps.insert(plan)), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<PlanDTO> putMappingMethod(Long id, PlanDTO modelDTO) {
		Installer i = is.find(modelDTO.getInstallerId());
		Plan plan = PlanDTO.DTOtoModel(modelDTO);
		plan.setInstaller(i);
		try {
			return new ResponseEntity<>(Plan.modeltoDTO(ps.update(id, plan)), HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<HttpStatus> deleteMappingMethod(Long id) {
		try {
			ps.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
