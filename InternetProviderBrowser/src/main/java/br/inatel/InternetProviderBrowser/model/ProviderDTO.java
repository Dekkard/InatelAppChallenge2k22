package br.inatel.InternetProviderBrowser.model;

public class ProviderDTO {
	private Long id;
	private String name;
	private String description;
	private Long cnpj;

	public ProviderDTO() {
	}

	public ProviderDTO(Long id, String name, String description, Long cnpj) {
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

	public static Provider DTOtoModel(ProviderDTO pDto) {
		return new Provider(pDto.getId(), pDto.getName(), pDto.getDescription(), pDto.getCnpj());
	}

}
