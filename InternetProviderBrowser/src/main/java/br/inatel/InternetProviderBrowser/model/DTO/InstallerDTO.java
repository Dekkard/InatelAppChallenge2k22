package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.InternetProviderBrowser.model.Installer;

public class InstallerDTO {
	private Long id;
	private String name;
	private Integer rating;
	private String priceKm;
	private String lat;
	private String lng;
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

	public InstallerDTO(String name, Integer rating, String priceKm, String lat, String lng) {
		super();
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}

	public InstallerDTO(Long id, String name, Integer rating, String priceKm, String lat, String lng) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}

	public InstallerDTO(Long id, String name, Integer rating, String priceKm, String lat, String lng,
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

	public String getPriceKm() {
		return priceKm;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	public List<PlanDTO> getListPlanDTO() {
		return listPlanDTO;
	}

	public static Installer DTOtoModel(InstallerDTO pDto) {
		return new Installer(pDto.getId(), //
				pDto.getName(), //
				pDto.getRating(), //
				new BigDecimal(pDto.getPriceKm()), //
				new BigDecimal(pDto.getLat()), //
				new BigDecimal(pDto.getLng()), //
				pDto.getListPlanDTO() //
						.stream() //
						.map(PlanDTO::DTOtoModel) //
						.collect(Collectors.toList()));
	}

}
