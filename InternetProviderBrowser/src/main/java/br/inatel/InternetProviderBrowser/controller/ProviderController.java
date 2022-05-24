package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Provider;
import br.inatel.InternetProviderBrowser.model.ProviderDTO;
import br.inatel.InternetProviderBrowser.service.ProviderService;

@RestController
@RequestMapping("/provider")
public class ProviderController implements ControllerModel<Provider, ProviderDTO, Long> {

	@Autowired
	ProviderService ps;

	@Override
	public ResponseEntity<List<ProviderDTO>> getMappingMethod() {
		List<Provider> listProvider = ps.list();
		List<ProviderDTO> listProviderDTO = listProvider.stream().map(Provider::modeltoDTO)
				.collect(Collectors.toList());
		if (!listProviderDTO.isEmpty())
			return new ResponseEntity<>(listProviderDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ProviderDTO> getByIdMappingMethod(Long id) {
		try {
			Provider provider = ps.find(id);
			return new ResponseEntity<>(Provider.modeltoDTO(provider), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Provider> postMappingMethod(ProviderDTO modelDTO) {
		try {
			Provider provider = ProviderDTO.DTOtoModel(modelDTO);
			return new ResponseEntity<>(ps.insert(provider), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Provider> putMappingMethod(Long id, ProviderDTO modelDTO) {
		try {
			Provider provider = ps.update(id, ProviderDTO.DTOtoModel(modelDTO));
			return new ResponseEntity<>(provider, HttpStatus.ACCEPTED);
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
