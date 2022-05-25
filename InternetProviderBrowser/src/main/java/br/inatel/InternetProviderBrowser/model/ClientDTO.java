package br.inatel.InternetProviderBrowser.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO {
	private Long id;
	private String name;
	private Long cpf;
	private String birthDate;

	private List<ServiceProvidedDTO> listServiceProvidedDTOs = new ArrayList<>();

	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, Long cpf, String birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}

	public ClientDTO(Long id, String name, Long cpf, String birthDate,
			List<ServiceProvidedDTO> listServiceProvidedDTO) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.listServiceProvidedDTOs = listServiceProvidedDTO;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getCpf() {
		return cpf;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public List<ServiceProvidedDTO> getListServiceProvidedDTOs() {
		return listServiceProvidedDTOs;
	}

	public static Client DTOtoModel(ClientDTO cDto) {
		Client client = new Client(cDto.getId(), cDto.getName(), cDto.getCpf(),
				LocalDate.parse(cDto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		return client;
	}
}
