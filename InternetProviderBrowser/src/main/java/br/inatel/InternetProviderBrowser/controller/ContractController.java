package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Client;
import br.inatel.InternetProviderBrowser.model.Contract;
import br.inatel.InternetProviderBrowser.model.Plan;
import br.inatel.InternetProviderBrowser.model.DTO.ClientDTO;
import br.inatel.InternetProviderBrowser.model.DTO.ContractDTO;
import br.inatel.InternetProviderBrowser.model.DTO.PlanDTO;
import br.inatel.InternetProviderBrowser.service.ClientService;
import br.inatel.InternetProviderBrowser.service.ContractService;
import br.inatel.InternetProviderBrowser.service.PlanService;

@RestController
@RequestMapping("/contract")
public class ContractController implements ControllerModel<Contract, ContractDTO, Long> {

	@Autowired
	ContractService cs;
	@Autowired
	PlanService ps;
	@Autowired
	ClientService cls;

	@Override
	public ResponseEntity<List<ContractDTO>> getMappingMethod() {
		List<Contract> listContract = cs.listClientContract();
		List<ContractDTO> listContractDTO = listContract.stream().map(Contract::modeltoDTO)
				.collect(Collectors.toList());
		if (!listContractDTO.isEmpty())
			return new ResponseEntity<>(listContractDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ContractDTO> getByIdMappingMethod(Long id) {
		Contract contract = cs.find(id);
		try {
			return new ResponseEntity<>(Contract.modeltoDTO(contract), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ContractDTO> postMappingMethod(ContractDTO modelDTO) {
		Client client = cls.find(modelDTO.getClientId());
		Plan plan = ps.find(modelDTO.getPlanId());
		Contract contract = ContractDTO.DTOtoModel(modelDTO);
		contract.setClient(client);
		contract.setPlan(plan);
//			retriveFromDTO(contract, modelDTO);
//			int indC = cs.updateClientId(contract.getId(), modelDTO.getClient().getId());
//			int indP = cs.updatePlanId(contract.getId(), modelDTO.getPlan().getId());
		try {
			return new ResponseEntity<>(Contract.modeltoDTO(cs.insert(contract)), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ContractDTO> putMappingMethod(Long id, ContractDTO modelDTO) {
		Client client = cls.find(modelDTO.getClientId());
		Plan plan = ps.find(modelDTO.getPlanId());
		Contract contract = ContractDTO.DTOtoModel(modelDTO);
		contract.setClient(client);
		contract.setPlan(plan);
//			retriveFromDTO(contract, modelDTO);
//			int indC = cs.updateClientId(contract.getId(), modelDTO.getClient().getId());
//			int indP = cs.updatePlanId(contract.getId(), modelDTO.getPlan().getId());
		try {
			return new ResponseEntity<>(Contract.modeltoDTO(cs.update(id, contract)), HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("unused")
	private void retriveFromDTO(Contract contract, ContractDTO modelDTO) {
		if (!modelDTO.getClient().equals(null))
			contract.setClient(cls.update(modelDTO.getClientId(), ClientDTO.DTOtoModel(modelDTO.getClient())));
		else if (!modelDTO.getClientId().equals(null))
			contract.setClient(cls.find(modelDTO.getId()));
		if (!modelDTO.getPlan().equals(null))
			contract.setPlan(ps.update(modelDTO.getPlanId(), PlanDTO.DTOtoModel(modelDTO.getPlan())));
		else if (!modelDTO.getPlanId().equals(null))
			contract.setPlan(ps.find(modelDTO.getId()));
	}

	@Override
	public ResponseEntity<HttpStatus> deleteMappingMethod(Long id) {
		try {
			cs.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
