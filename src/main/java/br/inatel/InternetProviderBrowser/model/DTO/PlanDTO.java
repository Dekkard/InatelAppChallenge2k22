package br.inatel.InternetProviderBrowser.model.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.InternetProviderBrowser.model.InternetType;
import br.inatel.InternetProviderBrowser.model.Plan;

public class PlanDTO {
	private Long id;
	private String isp;
	private String dataCapacity;
	private String downloadSpeed;
	private String uploadSpeed;
	private String description;
	private String price;
	private String internetType;
	List<ClientDTO> listClientDTO = new ArrayList<>();
	private InstallerDTO installerDTO;

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
			String description, String price, String internetType, List<ClientDTO> listClientDTO,
			InstallerDTO installerDTO) {
		super();
		this.id = id;
		this.isp = isp;
		this.dataCapacity = dataCapacity;
		this.downloadSpeed = downloadSpeed;
		this.uploadSpeed = uploadSpeed;
		this.description = description;
		this.price = price;
		this.internetType = internetType;
		this.listClientDTO = listClientDTO;
		this.installerDTO = installerDTO;
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

	public List<ClientDTO> getListClientDTO() {
		return listClientDTO;
	}

	public InstallerDTO getInstallerDTO() {
		return installerDTO;
	}

	public static Plan DTOtoModel(PlanDTO pDto) {
		return new Plan(pDto.getId(), //
				pDto.getIsp(), //
				new BigDecimal(pDto.getDataCapacity()), //
				new BigDecimal(pDto.getDownloadSpeed()), //
				new BigDecimal(pDto.getUploadSpeed()), //
				pDto.getDescription(), //
				new BigDecimal(pDto.getPrice()), //
				InternetType.valueOf(pDto.getInternetType()), //
				pDto.getListClientDTO() //
						.stream() //
						.map(ClientDTO::DTOtoModel) //
						.collect(Collectors.toList()), //
				InstallerDTO.DTOtoModel(pDto.getInstallerDTO()));
	}

}
