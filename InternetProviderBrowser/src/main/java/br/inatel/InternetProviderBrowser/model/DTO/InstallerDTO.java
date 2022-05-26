package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.InternetProviderBrowser.model.Installer;
import br.inatel.InternetProviderBrowser.model.Plan;

public class InstallerDTO {
	private Long id;
	private String name;
	private Integer rating;
	private BigDecimal priceKm;
	private BigDecimal lat;
	private BigDecimal lng;
	private List<PlanDTO> listPlanDTO = new ArrayList<>();
	
	public InstallerDTO() {
	}

	public InstallerDTO(InstallerDTO iDto) {
		super();
		this.name = iDto.getName();
		this.rating = iDto.getRating();
		this.priceKm = iDto.getPriceKm();
		this.lat = iDto.getLat();
		this.lng = iDto.getLng();
		this.listPlanDTO = iDto.getListPlanDTO();
	}
	
	public InstallerDTO(String name, Integer rating, BigDecimal priceKm, BigDecimal lat, BigDecimal lng) {
		super();
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}

	public InstallerDTO(Long id, String name, Integer rating, BigDecimal priceKm, BigDecimal lat, BigDecimal lng) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}

	public InstallerDTO(Long id, String name, Integer rating, BigDecimal priceKm, BigDecimal lat, BigDecimal lng,
			List<PlanDTO> listPlanDTO) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
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

	public Integer getRating() {
		return rating;
	}

	public BigDecimal getPriceKm() {
		return priceKm;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public List<PlanDTO> getListPlanDTO() {
		return listPlanDTO;
	}

	public static Installer DTOtoModel(InstallerDTO pDto) {
		List<Plan> li = pDto.getListPlanDTO().stream().map(PlanDTO::DTOtoModel).collect(Collectors.toList());
		return new Installer(pDto.getId(), pDto.getName(), pDto.getRating(), pDto.getPriceKm(), pDto.getLat(), pDto.getLng(), li);
	}

}
