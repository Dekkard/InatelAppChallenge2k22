package br.inatel.InternetProviderBrowser.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.inatel.InternetProviderBrowser.model.DTO.ContractDTO;

@Entity
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Plan plan;
	private BigDecimal totalPrice;
	private InstallStatus installStatus;

	public Contract() {
	}

	public Contract(BigDecimal totalPrice, InstallStatus installStatus) {
		super();
		this.totalPrice = totalPrice;
		this.installStatus = installStatus;
	}

	public Contract(Long id, BigDecimal totalPrice, InstallStatus installStatus) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.installStatus = installStatus;
	}

	public Contract(Long id, Client client, Plan plan, BigDecimal totalPrice, InstallStatus installStatus) {
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

	public Client getClient() {
		return client;
	}

	public Plan getPlan() {
		return plan;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public InstallStatus getInstallStatus() {
		return installStatus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setInstallStatus(InstallStatus installStatus) {
		this.installStatus = installStatus;
	}

	public static ContractDTO modeltoDTO(Contract c) {
		return new ContractDTO(c.getId(), Client.modeltoDTO(c.getClient()), Plan.modeltoDTO(c.getPlan()),
				c.getTotalPrice().toPlainString(), c.getInstallStatus().name());
	}
}
