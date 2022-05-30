package br.inatel.InternetProviderBrowser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ControllerModel<Entity, EntityDTO, EntityId> {
	@GetMapping
	public ResponseEntity<List<EntityDTO>> getMappingMethod();

	@GetMapping("/{id}")
	public ResponseEntity<EntityDTO> getByIdMappingMethod(@PathVariable("id") EntityId id);

	@PostMapping
	public ResponseEntity<Entity> postMappingMethod(@RequestBody @Validated EntityDTO modelDTO);

	@PutMapping("/{id}")
	public ResponseEntity<Entity> putMappingMethod(@PathVariable("id") EntityId id,
			@RequestBody @Validated EntityDTO modelDTO);

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteMappingMethod(@PathVariable("id") EntityId id);
}
