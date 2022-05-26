package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Plan;
import br.inatel.InternetProviderBrowser.model.DTO.PlanDTO;
import br.inatel.InternetProviderBrowser.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController implements ControllerModel<Plan, PlanDTO, Long> {

	@Autowired
	PlanService ps;

	@Override
	public ResponseEntity<List<PlanDTO>> getMappingMethod() {
		List<Plan> listPlan = ps.list();
		List<PlanDTO> listPlanDTO = listPlan.stream().map(Plan::modeltoDTO)
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
			return new ResponseEntity<>(Plan.modeltoDTO(plan), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Plan> postMappingMethod(PlanDTO modelDTO) {
		try {
			Plan plan = PlanDTO.DTOtoModel(modelDTO);
			return new ResponseEntity<>(ps.insert(plan), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Plan> putMappingMethod(Long id, PlanDTO modelDTO) {
		try {
			Plan plan = ps.update(id, PlanDTO.DTOtoModel(modelDTO));
			return new ResponseEntity<>(plan, HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<HttpStatus> deleteMappingMethod(Long id) {
		try {
			ps.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
