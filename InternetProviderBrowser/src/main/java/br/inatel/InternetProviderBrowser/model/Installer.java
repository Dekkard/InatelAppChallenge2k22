package br.inatel.InternetProviderBrowser.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.InternetProviderBrowser.model.DTO.InstallerDTO;
import br.inatel.InternetProviderBrowser.model.DTO.PlanDTO;

@Entity
public class Installer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(max = 255)
	@NotNull
	private String name;
	@Min(1)
	@Max(10)
	private Integer rating;
	private BigDecimal priceKm;
	private BigDecimal lat;
	private BigDecimal lng;
	@OneToMany
	private List<Plan> listPlan = new ArrayList<>();

	public Installer() {
	}
	
	public Installer(Installer i) {
		super();
//		this.name = i.getName();
		this.rating = i.getRating();
		this.priceKm = i.getPriceKm();
		this.lat = i.getLat();
		this.lng =  i.getLng();
		this.listPlan = i.getListPlan();
	}

	public Installer(@Length(max = 255) @NotNull String name, @Min(1) @Max(10) Integer rating, BigDecimal priceKm,
			BigDecimal lat, BigDecimal lng) {
		super();
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}

	public Installer(Long id, @Length(max = 255) @NotNull String name, @Min(1) @Max(10) Integer rating,
			BigDecimal priceKm, BigDecimal lat, BigDecimal lng) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}
	public Installer(Long id, @Length(max = 255) @NotNull String name, @Min(1) @Max(10) Integer rating,
			BigDecimal priceKm, BigDecimal lat, BigDecimal lng, List<Plan> listPlan) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
		this.listPlan = listPlan;
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

	public List<Plan> getListPlan() {
		return listPlan;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setPriceKm(BigDecimal priceKm) {
		this.priceKm = priceKm;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public void setListPlan(List<Plan> listPlan) {
		this.listPlan = listPlan;
	}

	public static InstallerDTO modeltoDTO(Installer p) {
		List<PlanDTO> lp = p.getListPlan().stream().map(Plan::modeltoDTO).collect(Collectors.toList());
		return new InstallerDTO(p.getId(), p.getName(), p.getRating(), p.getPriceKm(), p.getLat(), p.getLng(), lp);
	}
}
