package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.inatel.InternetProviderBrowser.model.Client;

public class ClientDTO {
	private Long id;
	private String name;
	private Long cpf;
	private String birthDate;
	private String lat;
	private String lng;

	private List<PlanDTO> listPlanDTO = new ArrayList<>();

	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, Long cpf, String birthDate, String lat, String lng) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
	}

	public ClientDTO(Long id, String name, Long cpf, String birthDate, String lat, String lng
			List<PlanDTO> listPlanDTO) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
		this.listPlanDTO = listPlanDTO;
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
public String getLat(){
	return lat;
	}
	public String getLng() {
	return lng;
	}
	public List<PlanDTO> getListPlanDTO() {
		return listPlanDTO;
	}

	public static Client DTOtoModel(ClientDTO cDto) {
		List<Plan> lp = cDto.getListPlanDTO().stream().map(PlanDTO::DTOtoModel).collect(Collectors.toList());
		Client client = new Client(cDto.getId(), cDto.getName(), cDto.getCpf(),
				LocalDate.parse(cDto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), Client.g, new BigDecimal(ClientDTO.getLat()), new BigDecimal(ClientDTO.getLng()));
		return client;
	}
}
