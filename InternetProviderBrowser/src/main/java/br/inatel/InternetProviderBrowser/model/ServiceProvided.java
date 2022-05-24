package br.inatel.InternetProviderBrowser.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ServiceProvided {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(max = 255)
	@NotNull
	private String name;
	@Length(max = 511)
	@NotNull
	private String description;
	private BigDecimal price;

	public ServiceProvided() {
	}

	public ServiceProvided(@Length(max = 255) @NotNull String name, @Length(max = 511) @NotNull String description,
			BigDecimal price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ServiceProvided(Long id, @Length(max = 255) @NotNull String name,
			@Length(max = 511) @NotNull String description, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static ServiceProvidedDTO modeltoDTO(ServiceProvided sp) {
		return new ServiceProvidedDTO(sp.getId(), sp.getName(), sp.getDescription(), sp.getPrice().toPlainString());
	}
}
