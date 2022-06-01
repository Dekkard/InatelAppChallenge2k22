package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.inatel.InternetProviderBrowser.model.Contract;
import br.inatel.InternetProviderBrowser.model.Installer;
import br.inatel.InternetProviderBrowser.model.InternetType;
import br.inatel.InternetProviderBrowser.model.Plan;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanDTO {
	private Long id;
	private String isp;
	private String dataCapacity;
	private String downloadSpeed;
	private String uploadSpeed;
	private String description;
	private String price;
	private String internetType;

	private List<ContractDTO> listContractDTO = new ArrayList<>();
	private InstallerDTO installerDTO;

	private List<Long> listContractId = new ArrayList<>();
	private Long installerId;

	public PlanDTO() {
	}

	public PlanDTO(String isp, String dataCapacity, String downloadSpeed, String uploadSpeed, String description,
			String price, String internetType) {
		super();
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
	}

	public PlanDTO(Long id, String isp, String dataCapacity, String downloadSpeed, String uploadSpeed,
			String description, String price, String internetType) {
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

	public PlanDTO(Long id, String isp, String dataCapacity, String downloadSpeed, String uploadSpeed,
			String description, String price, String internetType, Long installerId, List<Long> listContractId) {
		super();
		this.id = id;
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
		this.listContractId = listContractId;
		this.installerId = installerId;
	}

	public PlanDTO(Long id, String isp, String dataCapacity, String downloadSpeed, String uploadSpeed,
			String description, String price, String internetType, InstallerDTO installerDTO,
			List<ContractDTO> listContractDTO) {
		super();
		this.id = id;
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
		this.installerDTO = installerDTO;
		this.listContractDTO = listContractDTO;
	}

	public Long getId() {
		return id;
	}

	public String getIsp() {
		return isp;
	}

	public String getDataCapacity() {
		return dataCapacity;
	}

	public String getDownloadSpeed() {
		return downloadSpeed;
	}

	public String getUploadSpeed() {
		return uploadSpeed;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public String getInternetType() {
		return internetType;
	}

	public List<Long> getListContractId() {
		return listContractId;
	}

	public Long getInstallerId() {
		return installerId;
	}

	public InstallerDTO getInstallerDTO() {
		return installerDTO;
	}

	public List<ContractDTO> getListContractDTO() {
		return listContractDTO;
	}

	public void setInstallerDTO(InstallerDTO installerDTO) {
		this.installerDTO = installerDTO;
	}

	public static Plan DTOtoModel(PlanDTO pDto) {
		Installer i = new Installer();
		List<Contract> lc = new ArrayList<>();
		try {
			i = InstallerDTO.DTOtoModelRL(pDto.getInstallerDTO());
		} catch (NullPointerException e) {
		}
		try {
			lc = pDto.getListContractDTO().stream()
					.map(ContractDTO::DTOtoModelRL)
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}
		return new Plan(pDto.getId(), pDto.getIsp(), new BigDecimal(pDto.getDataCapacity()),
				new BigDecimal(pDto.getDownloadSpeed()), new BigDecimal(pDto.getUploadSpeed()), pDto.getDescription(),
				new BigDecimal(pDto.getPrice()), InternetType.valueOf(pDto.getInternetType()), i, lc);
	}

	public static Plan DTOtoModelRL(PlanDTO pDto) {
		return new Plan(pDto.getId(), pDto.getIsp(), new BigDecimal(pDto.getDataCapacity()),
				new BigDecimal(pDto.getDownloadSpeed()), new BigDecimal(pDto.getUploadSpeed()), pDto.getDescription(),
				new BigDecimal(pDto.getPrice()), InternetType.valueOf(pDto.getInternetType()));
	}

}
