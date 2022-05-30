package br.inatel.InternetProviderBrowser.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.InternetProviderBrowser.model.DTO.PlanDTO;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Length(max = 128)
	@NotNull
	private String isp;
	private BigDecimal dataCapacity;
	private BigDecimal downloadSpeed;
	private BigDecimal uploadSpeed;
	@Length(max = 512)
	private String description;
	private BigDecimal price;
	private InternetType internetType;

	@ManyToMany
	List<Client> listClient = new ArrayList<>();
	@ManyToOne
	private Installer installer;

	public Plan() {
	}

	public Plan(Plan p) {
		this.isp = p.getIsp();
		this.dataCapacity = p.getDataCapacity();
		this.downloadSpeed = p.getDownloadSpeed();
		this.uploadSpeed = p.getUploadSpeed();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.internetType = p.getInternetType();
	}

	public Plan(@Length(max = 128) @NotNull String isp, BigDecimal dataCapacity, BigDecimal downloadSpeed,
			BigDecimal uploadSpeed, @Length(max = 512) String description, BigDecimal price,
			InternetType internetType) {
		super();
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
	}

	public Plan(Long id, @Length(max = 128) @NotNull String isp, BigDecimal dataCapacity, BigDecimal downloadSpeed,
			BigDecimal uploadSpeed, @Length(max = 512) String description, BigDecimal price,
			InternetType internetType) {
		super();
		this.id = id;
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
	}

	public Plan(Long id, @Length(max = 128) @NotNull String isp, BigDecimal dataCapacity, BigDecimal downloadSpeed,
			BigDecimal uploadSpeed, @Length(max = 512) String description, BigDecimal price, InternetType internetType,
			List<Client> listClient, Installer installer) {
		super();
		this.id = id;
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
		this.listClient = listClient;
		this.installer = installer;
	}

	public Long getId() {
		return id;
	}

	public String getIsp() {
		return isp;
	}

	public BigDecimal getDataCapacity() {
		return dataCapacity;
	}

	public BigDecimal getDownloadSpeed() {
		return downloadSpeed;
	}

	public BigDecimal getUploadSpeed() {
		return uploadSpeed;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public InternetType getInternetType() {
		return internetType;
	}

	public List<Client> getListClient() {
		return listClient;
	}

	public Installer getInstaller() {
		return installer;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public void setDataCapacity(BigDecimal dataCapacity) {
		this.dataCapacity = dataCapacity;
	}

	public void setDownloadSpeed(BigDecimal downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public void setUploadSpeed(BigDecimal uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setInternetType(InternetType internetType) {
		this.internetType = internetType;
	}

	public void setListClient(List<Client> listClient) {
		this.listClient = listClient;
	}

	public void setInstaller(Installer installer) {
		this.installer = installer;
	}

	public static PlanDTO modeltoDTO(Plan p) {
		return new PlanDTO(p.getId(), //
				p.getIsp(), //
				p.getDataCapacity().toPlainString(), //
				p.getDownloadSpeed().toPlainString(), //
				p.getUploadSpeed().toPlainString(), //
				p.getDescription(), //
				p.getPrice().toPlainString(), //
				p.getInternetType().toString(), //
				p.getListClient()//
						.stream()//
						.map(Client::modeltoDTO)//
						.collect(Collectors.toList()), //
				Installer.modeltoDTO(p.getInstaller()));
	}
}
