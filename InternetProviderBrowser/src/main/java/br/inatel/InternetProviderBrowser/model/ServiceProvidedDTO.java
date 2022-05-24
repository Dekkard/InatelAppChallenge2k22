package br.inatel.InternetProviderBrowser.model;

import java.math.BigDecimal;

public class ServiceProvidedDTO {
	private Long id;
	private String name;
	private String description;
	private String price;

	public ServiceProvidedDTO() {
	}

	public ServiceProvidedDTO(Long id, String name, String description, String price) {
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

	public String getPrice() {
		return price;
	}

	public static ServiceProvided DTOtoModel(ServiceProvidedDTO spDto) {
		return new ServiceProvided(spDto.getId(), spDto.getName(), spDto.getDescription(),
				new BigDecimal(spDto.getPrice()));
	}

}
