package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.inatel.InternetProviderBrowser.model.Client;
import br.inatel.InternetProviderBrowser.model.Contract;
import br.inatel.InternetProviderBrowser.model.InstallStatus;
import br.inatel.InternetProviderBrowser.model.Plan;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDTO {
	private Long id;
	private String totalPrice;
	private String installStatus;

	private ClientDTO client;
	private PlanDTO plan;

	private Long clientId;
	private Long planId;

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

	public ContractDTO(Long id, String totalPrice, String installStatus, Long clientId, Long planId) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.planId = planId;
		this.totalPrice = totalPrice;
		this.installStatus = installStatus;
	}

	public ContractDTO(Long id, String totalPrice, String installStatus, ClientDTO client, PlanDTO plan) {
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

	public String getTotalPrice() {
		return totalPrice;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public Long getClientId() {
		return clientId;
	}

	public Long getPlanId() {
		return planId;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PlanDTO getPlan() {
		return plan;
	}

	public static Contract DTOtoModel(ContractDTO cDto) {
		Client client = new Client();
		Plan plan = new Plan();
		try {
			client = ClientDTO.DTOtoModelRL(cDto.getClient());
		} catch (NullPointerException e) {
		}
		try {
			plan = PlanDTO.DTOtoModelRL(cDto.getPlan());
		} catch (NullPointerException e) {
		}
		return new Contract(cDto.getId(), new BigDecimal(cDto.getTotalPrice()),
				InstallStatus.valueOf(cDto.getInstallStatus()), client, plan);

	}

	public static Contract DTOtoModelRL(ContractDTO cDto) {
		return new Contract(cDto.getId(), new BigDecimal(cDto.getTotalPrice()),
				InstallStatus.valueOf(cDto.getInstallStatus()));

	}
}
