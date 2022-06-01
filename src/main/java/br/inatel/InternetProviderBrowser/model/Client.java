package br.inatel.InternetProviderBrowser.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.InternetProviderBrowser.model.DTO.ClientDTO;
import br.inatel.InternetProviderBrowser.model.DTO.ContractDTO;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(max = 255)
	@NotNull
	private String name;
	@Digits(integer = 11, fraction = 0)
	@NotNull
	private Long cpf;
	private LocalDate birthDate;
	private BigDecimal lat;
	private BigDecimal lng;
	@OneToMany(mappedBy = "client")
	private List<Contract> listContract = new ArrayList<>();

	public Client() {
	}

	public Client(Client c) {
		super();
		this.id = c.getId();
		this.name = c.getName();
		this.cpf = c.getCpf();
		this.birthDate = c.getBirthDate();
		this.lat = c.getLat();
		this.lng = c.getLng();
	}

	public Client(@Length(max = 255) @NotNull String name, @Digits(integer = 11, fraction = 0) @NotNull Long cpf,
			LocalDate birthDate, BigDecimal lat, BigDecimal lng) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
	}

	public Client(Long id, @Length(max = 255) @NotNull String name,
			@Digits(integer = 11, fraction = 0) @NotNull Long cpf, LocalDate birthDate, BigDecimal lat,
			BigDecimal lng) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
	}

	public Client(Long id, @Length(max = 255) @NotNull String name,
			@Digits(integer = 11, fraction = 0) @NotNull Long cpf, LocalDate birthDate, BigDecimal lat, BigDecimal lng,
			List<Contract> listContract) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
		this.listContract = listContract;
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

	public BigDecimal getLat() {
		return lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public List<Contract> getListContract() {
		return listContract;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public void setListContract(List<Contract> listContract) {
		this.listContract = listContract;
	}

	public static ClientDTO modeltoDTO(Client c) {
		List<ContractDTO> listContractDTO = new ArrayList<>();
		try {
			listContractDTO = c.getListContract().stream().map(Contract::modeltoDTORL).collect(Collectors.toList());
		} catch (NullPointerException e) {
		}
		return new ClientDTO(c.getId(), c.getName(), c.getCpf(),
				c.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), c.getLat().toPlainString(),
				listContractDTO, c.getLng().toPlainString());
	}

	public static ClientDTO modeltoDTORL(Client c) {
		return new ClientDTO(c.getId(), c.getName(), c.getCpf(),
				c.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), c.getLat().toPlainString(),
				c.getLng().toPlainString());
	}
}
