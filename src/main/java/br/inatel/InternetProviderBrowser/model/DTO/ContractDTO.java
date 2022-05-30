package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.inatel.InternetProviderBrowser.model.Contract;
import br.inatel.InternetProviderBrowser.model.InstallStatus;

@Entity
public class ContractDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private ClientDTO client;
	@ManyToOne
	private PlanDTO plan;
	private String totalPrice;
	private String installStatus;

	public ContractDTO() {
	}

	public ContractDTO(String totalPrice, String installStatus) {
		super();
		this.totalPrice = totalPrice;
		this.installStatus = installStatus;
	}

	public ContractDTO(Long id, String totalPrice, String installStatus) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.installStatus = installStatus;
	}

	public ContractDTO(Long id, ClientDTO client, PlanDTO plan, String totalPrice, String installStatus) {
		super();
		this.id = id;
		this.client = client;
		this.plan = plan;
		this.totalPrice = totalPrice;
		this.installStatus = installStatus;
	}

	public Long getId() {
		return id;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PlanDTO getPlan() {
		return plan;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public void setPlan(PlanDTO plan) {
		this.plan = plan;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setInstallStatus(String installStatus) {
		this.installStatus = installStatus;
	}

	public static Contract DTOtoModel(ContractDTO cDto) {
		return new Contract(cDto.getId(), ClientDTO.DTOtoModel(cDto.getClient()), PlanDTO.DTOtoModel(cDto.getPlan()), new BigDecimal(cDto.getTotalPrice()), InstallStatus.valueOf(cDto.getInstallStatus()));
		
	}
}
