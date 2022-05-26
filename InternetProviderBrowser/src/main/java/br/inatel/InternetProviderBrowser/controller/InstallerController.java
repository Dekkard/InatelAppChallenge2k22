package br.inatel.InternetProviderBrowser.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.model.Installer;
import br.inatel.InternetProviderBrowser.model.DTO.InstallerDTO;
import br.inatel.InternetProviderBrowser.service.InstallerService;

@RestController
@RequestMapping("/installer")
public class InstallerController implements ControllerModel<Installer, InstallerDTO, Long> {

	@Autowired
	InstallerService is;

	@Override
	public ResponseEntity<List<InstallerDTO>> getMappingMethod() {
		List<Installer> listInstaller = is.list();
		List<InstallerDTO> listInstallerDTO = listInstaller.stream().map(Installer::modeltoDTO)
				.collect(Collectors.toList());
		if (!listInstallerDTO.isEmpty())
			return new ResponseEntity<>(listInstallerDTO, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<InstallerDTO> getByIdMappingMethod(Long id) {
		try {
			Installer installer = is.find(id);
			return new ResponseEntity<>(Installer.modeltoDTO(installer), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Installer> postMappingMethod(InstallerDTO modelDTO) {
		try {
			Installer installer = InstallerDTO.DTOtoModel(modelDTO);
			return new ResponseEntity<>(is.insert(installer), HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Installer> putMappingMethod(Long id, InstallerDTO modelDTO) {
		try {
			Installer installer = is.update(id, InstallerDTO.DTOtoModel(modelDTO));
			return new ResponseEntity<>(installer, HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<HttpStatus> deleteMappingMethod(Long id) {
		try {
			is.delete(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
