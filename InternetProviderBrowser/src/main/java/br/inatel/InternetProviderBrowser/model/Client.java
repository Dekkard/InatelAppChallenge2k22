package br.inatel.InternetProviderBrowser.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.InternetProviderBrowser.model.DTO.ClientDTO;

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
	@ManyToMany
	private List<Plan> listPlan = new ArrayList<>();

	public Client() {
	}
	public Client(Client c) {
		super();
//		this.id = c.getId();
		this.name = c.getName();
		this.cpf = c.getCpf();
		this.birthDate = getBirthDate();
	}

	public Client(@Length(max = 255) @NotNull String name, @Digits(integer = 11, fraction = 0) @NotNull Long cpf,
			LocalDate birthDate) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}

	public Client(Long id, @Length(max = 255) @NotNull String name, @Digits(integer = 11, fraction = 0) @NotNull Long cpf,
			LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}

	public Client(Long id, @Length(max = 255) @NotNull String name, @Digits(integer = 11, fraction = 0) @NotNull Long cpf,
			LocalDate birthDate, List<Plan> listPlan) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.listPlan = listPlan;
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

	public List<Plan> getPlan() {
		return listPlan;
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

	public void setListService(List<Plan> listService) {
		this.listPlan = listService;
	}

	public static ClientDTO modeltoDTO(Client c) {
		ClientDTO clientDTO = new ClientDTO(c.getId(), c.getName(), c.getCpf(),
				c.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		return clientDTO;
	}

}
