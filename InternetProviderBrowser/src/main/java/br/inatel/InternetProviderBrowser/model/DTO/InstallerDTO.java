package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.inatel.InternetProviderBrowser.model.Installer;
import br.inatel.InternetProviderBrowser.model.Plan;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstallerDTO {
	private Long id;
	private String name;
	private Integer rating;
	private String priceKm;
	private String lat;
	private String lng;

	private List<PlanDTO> listPlanDTO = new ArrayList<>();

	private List<Long> listPlanId = new ArrayList<>();

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
			List<Long> listPlanId) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
		this.listPlanId = listPlanId;
	}

	public InstallerDTO(Long id, String name, Integer rating, String priceKm, String lat, List<PlanDTO> listPlanDTO,
			String lng) {
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

	public List<Long> getListPlanId() {
		return listPlanId;
	}

	public List<PlanDTO> getListPlanDTO() {
		return listPlanDTO;
	}

	public static Installer DTOtoModel(InstallerDTO pDto) {
		List<Plan> listPlan = new ArrayList<>();
		try {
			listPlan = pDto.getListPlanDTO().stream().map(PlanDTO::DTOtoModelRL).collect(Collectors.toList());
		} catch (NullPointerException e) {
		}
		return new Installer(pDto.getId(), pDto.getName(), pDto.getRating(), new BigDecimal(pDto.getPriceKm()),
				new BigDecimal(pDto.getLat()), new BigDecimal(pDto.getLng()), listPlan);
	}

	public static Installer DTOtoModelRL(InstallerDTO pDto) {
		return new Installer(pDto.getId(), pDto.getName(), pDto.getRating(), new BigDecimal(pDto.getPriceKm()),
				new BigDecimal(pDto.getLat()), new BigDecimal(pDto.getLng()));
	}

}
