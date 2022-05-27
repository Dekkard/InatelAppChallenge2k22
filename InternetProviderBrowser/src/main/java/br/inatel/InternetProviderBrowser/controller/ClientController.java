package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Client;
import br.inatel.InternetProviderBrowser.model.DTO.ClientDTO;
import br.inatel.InternetProviderBrowser.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController implements ControllerModel<Client, ClientDTO, Long> {

	@Autowired
	ClientService cs;

	@Override
	public ResponseEntity<List<ClientDTO>> getMappingMethod() {
		List<Client> listClient = cs.list();
		List<ClientDTO> listClientDTO = listClient.stream().map(Client::modeltoDTO).collect(Collectors.toList());
		if (!listClientDTO.isEmpty())
			return new ResponseEntity<>(listClientDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ClientDTO> getByIdMappingMethod(Long id) {
		try {
			Client client = cs.find(id);
			return new ResponseEntity<>(Client.modeltoDTO(client), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Client> postMappingMethod(ClientDTO modelDTO) {
		try {
			Client client = ClientDTO.DTOtoModel(modelDTO);
			return new ResponseEntity<>(cs.insert(client), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Client> putMappingMethod(Long id, ClientDTO modelDTO) {
		try {
			Client client = cs.update(id, ClientDTO.DTOtoModel(modelDTO));
			return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
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
