package br.inatel.InternetProviderBrowser.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(max = 255)
	@NotNull
	private String name;
	@Length(max = 511)
	@NotNull
	private String description;
	@Size(min = 14, max = 14)
	@NotNull
	private Long cnpj;
	@ManyToOne
	private List<ServiceProvided> listService = new ArrayList<>();

	public Provider() {
	}

	public Provider(@Length(max = 255) @NotNull String name, @Length(max = 511) @NotNull String description,
			@Size(min = 14, max = 14) @NotNull Long cnpj) {
		super();
		this.name = name;
		this.description = description;
		this.cnpj = cnpj;
	}

	public Provider(Long id, @Length(max = 255) @NotNull String name, @Length(max = 511) @NotNull String description,
			@Size(min = 14, max = 14) @NotNull Long cnpj) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public static ProviderDTO modeltoDTO(Provider p) {
		return new ProviderDTO(p.getId(), p.getName(), p.getDescription(), p.getCnpj());
	}
}
