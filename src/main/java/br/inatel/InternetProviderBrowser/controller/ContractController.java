package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Contract;
import br.inatel.InternetProviderBrowser.model.DTO.ContractDTO;
import br.inatel.InternetProviderBrowser.service.ContractService;

@RestController
@RequestMapping("/contract")
public class ContractController implements ControllerModel<Contract, ContractDTO, Long> {
	
	
	@Autowired
	ContractService cs;

	@Override
	public ResponseEntity<List<ContractDTO>> getMappingMethod() {
		List<Contract> listContract = cs.list();
		List<ContractDTO> listContractDTO = listContract.stream().map(Contract::modeltoDTO)
				.collect(Collectors.toList());
		if (!listContractDTO.isEmpty())
			return new ResponseEntity<>(listContractDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ContractDTO> getByIdMappingMethod(Long id) {
		try {
			Contract contract = cs.find(id);
			return new ResponseEntity<>(Contract.modeltoDTO(contract), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Contract> postMappingMethod(ContractDTO modelDTO) {
		try {
			Contract contract = ContractDTO.DTOtoModel(modelDTO);
			return new ResponseEntity<>(cs.insert(contract), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Contract> putMappingMethod(Long id, ContractDTO modelDTO) {
		try {
			Contract contract = cs.update(id, ContractDTO.DTOtoModel(modelDTO));
			return new ResponseEntity<>(contract, HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
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
