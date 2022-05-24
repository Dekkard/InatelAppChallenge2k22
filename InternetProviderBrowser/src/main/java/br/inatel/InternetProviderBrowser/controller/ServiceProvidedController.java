package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.ServiceProvided;
import br.inatel.InternetProviderBrowser.model.ServiceProvidedDTO;
import br.inatel.InternetProviderBrowser.service.ServiceProvidedService;

@RestController
@RequestMapping("/serviceprovided")
public class ServiceProvidedController implements ControllerModel<ServiceProvided, ServiceProvidedDTO, Long> {

	@Autowired
	ServiceProvidedService sps;

	@Override
	public ResponseEntity<List<ServiceProvidedDTO>> getMappingMethod() {
		List<ServiceProvided> listServiceProvided = sps.list();
		List<ServiceProvidedDTO> listServiceProvidedDTO = listServiceProvided.stream().map(ServiceProvided::modeltoDTO)
				.collect(Collectors.toList());
		if (!listServiceProvidedDTO.isEmpty())
			return new ResponseEntity<>(listServiceProvidedDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ServiceProvidedDTO> getByIdMappingMethod(Long id) {
		try {
			ServiceProvided serviceProvided = sps.find(id);
			return new ResponseEntity<>(ServiceProvided.modeltoDTO(serviceProvided), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ServiceProvided> postMappingMethod(ServiceProvidedDTO modelDTO) {
		try {
			ServiceProvided serviceProvided = ServiceProvidedDTO.DTOtoModel(modelDTO);
			return new ResponseEntity<>(sps.insert(serviceProvided), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ServiceProvided> putMappingMethod(Long id, ServiceProvidedDTO modelDTO) {
		try {
			ServiceProvided serviceProvided = sps.update(id, ServiceProvidedDTO.DTOtoModel(modelDTO));
			return new ResponseEntity<>(serviceProvided, HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<HttpStatus> deleteMappingMethod(Long id) {
		try {
			sps.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
