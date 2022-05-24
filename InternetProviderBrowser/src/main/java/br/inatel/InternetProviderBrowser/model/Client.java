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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(max = 255)
	@NotNull
	private String name;
	@Size(min = 10, max = 11)
	@NotNull
	private Long cpf;
	private LocalDate birthDate;
	@ManyToMany
	private List<ServiceProvided> listService = new ArrayList<>();

	public Client() {
	}

	public Client(@Length(max = 255) @NotNull String name, @Size(min = 10, max = 11) @NotNull Long cpf,
			LocalDate birthDate) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}

	public Client(Long id, @Length(max = 255) @NotNull String name, @Size(min = 10, max = 11) @NotNull Long cpf,
			LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
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
	
	public List<ServiceProvided> getListService() {
		return listService;
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

	public static ClientDTO modeltoDTO(Client c) {
		ClientDTO clientDTO = new ClientDTO(c.getId(), c.getName(), c.getCpf(),
				c.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		return clientDTO;
	}

}
