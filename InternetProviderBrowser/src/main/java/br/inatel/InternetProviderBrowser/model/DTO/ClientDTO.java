package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.inatel.InternetProviderBrowser.model.Client;
import br.inatel.InternetProviderBrowser.model.Contract;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDTO {
	private Long id;
	private String name;
	private Long cpf;
	private String birthDate;
	private String lat;
	private String lng;

	private List<ContractDTO> listContractDTO = new ArrayList<>();

	private List<Long> listContractId = new ArrayList<>();

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

	public ClientDTO(Long id, String name, Long cpf, String birthDate, String lat, String lng,
			List<Long> listContractId) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
		this.listContractId = listContractId;
	}

	public ClientDTO(Long id, String name, Long cpf, String birthDate, String lat, List<ContractDTO> listContractDTO,
			String lng) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
		this.listContractDTO = listContractDTO;
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

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	public List<Long> getListContractId() {
		return listContractId;
	}

	public List<ContractDTO> getListContractDTO() {
		return listContractDTO;
	}

	public static Client DTOtoModel(ClientDTO cDto) {
		List<Contract> listContract = new ArrayList<>();
		try {
			listContract = cDto.getListContractDTO().stream().map(ContractDTO::DTOtoModelRL)
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}
		return new Client(cDto.getId(), cDto.getName(), cDto.getCpf(),
				LocalDate.parse(cDto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(cDto.getLat()), new BigDecimal(cDto.getLng()), listContract);
	}

	public static Client DTOtoModelRL(ClientDTO cDto) {
		return new Client(cDto.getId(), cDto.getName(), cDto.getCpf(),
				LocalDate.parse(cDto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(cDto.getLat()), new BigDecimal(cDto.getLng()));
	}
}
